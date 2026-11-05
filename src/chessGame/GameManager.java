package chessGame;

import chessGame.persistence.GameRepository;
import chessGame.records.CastlingMove;
import chessGame.records.Move;
import chessGame.records.PendingMove;
import chessGame.records.PieceState;
import chessGame.records.SavedGame;
import chessGame.screens.EndingScreen;
import chessPieces.*;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class take care of rotation of players, move validation, checks game status
 */
public class GameManager {
    private final ArrayList<Move> moveHistory = new ArrayList<>();
    private PendingMove pendingMove;
    // Number of applied moves; the remaining history is available through Forward.
    private int moveCounter;
    private final Chessboard chessboard;
    private boolean validationInProcess;
    private boolean gameCheck = false;
    private boolean promotionPending = false;
    private boolean gameOver = false;
    private EndingScreen endingScreen;
    private GameRepository gameRepository;
    private long gameId;
    private PieceColor winner;
    private boolean restoringHistory;

    public GameManager(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    public void configurePersistence(GameRepository repository, long gameId) {
        if (gameId <= 0) {
            throw new IllegalArgumentException("Invalid game ID.");
        }

        this.gameRepository = Objects.requireNonNull(repository);
        this.gameId = gameId;
    }

    public String getGameStatus() {
        if (!gameOver) {
            return "IN_PROGRESS";
        }

        if (winner == null) {
            return "DRAW";
        }

        return winner == PieceColor.WHITE ? "WHITE_WON" : "BLACK_WON";
    }

    /** Restores a saved game on a newly created board without writing intermediate positions. */
    public void restoreGame(SavedGame savedGame) {
        Objects.requireNonNull(savedGame);
        if (!moveHistory.isEmpty() || moveCounter != 0 || promotionPending) {
            throw new IllegalStateException("A saved game must be restored on a fresh board.");
        }

        restoringHistory = true;
        try {
            prepareHistoryNavigation();
            moveHistory.addAll(savedGame.moves());
            // Check the future continuation too, then return to the saved cursor.
            while (moveCounter < moveHistory.size()) {
                validateRecordedMove(moveHistory.get(moveCounter));
                redoMove();
                evaluateCurrentPlayer();
            }
            while (moveCounter > savedGame.historyPosition()) {
                undoMove();
            }
            gameOver = false;
            winner = null;
            evaluateCurrentPlayer();
            if (!getGameStatus().equals(savedGame.status())) {
                throw new IllegalArgumentException("Saved result does not match the position.");
            }
        } finally {
            restoringHistory = false;
            chessboard.updateHistoryControls();
            chessboard.refreshBoard();
        }
    }

    private void validateRecordedMove(Move move) {
        if (move.fromRow() < 0 || move.fromRow() > 7 || move.fromColumn() < 0 || move.fromColumn() > 7
                || move.toRow() < 0 || move.toRow() > 7 || move.toColumn() < 0 || move.toColumn() > 7) {
            throw new IllegalArgumentException("Saved move is outside the board.");
        }
        ChessPiece piece = chessboard.getArrayBoard()[move.fromRow()][move.fromColumn()].getPieceOnSquare();
        ChessSquare target = chessboard.getArrayBoard()[move.toRow()][move.toColumn()];
        if (piece == null || !Objects.equals(snapshot(piece), move.movingBefore())
                || !Objects.equals(snapshot(target.getPieceOnSquare()), move.capturedBefore())
                || !alternationOfPlayers(piece) || !piece.getChessPieceMovementMap().containsKey(target)) {
            throw new IllegalArgumentException("Saved move does not match the board.");
        }

        boolean promotion = piece instanceof Pawn && (move.toRow() == 0 || move.toRow() == 7);
        if (promotion != move.promotion()
                || (promotion && !List.of(PieceType.QUEEN, PieceType.ROOK,
                        PieceType.BISHOP, PieceType.KNIGHT).contains(move.promotedTo()))) {
            throw new IllegalArgumentException("Invalid saved promotion.");
        }

        boolean castling = piece instanceof King && Math.abs(move.toColumn() - move.fromColumn()) == 2;
        if (castling != move.castling()) {
            throw new IllegalArgumentException("Invalid saved castling move.");
        }
        if (castling) {
            int rookFrom = move.toColumn() > move.fromColumn() ? 7 : 0;
            int rookTo = move.toColumn() > move.fromColumn() ? 5 : 3;
            CastlingMove rookMove = move.castlingMove();
            ChessPiece rook = chessboard.getArrayBoard()[move.fromRow()][rookFrom].getPieceOnSquare();
            if (rookMove.row() != move.fromRow() || rookMove.fromColumn() != rookFrom
                    || rookMove.toColumn() != rookTo || !Objects.equals(snapshot(rook), rookMove.rookBefore())) {
                throw new IllegalArgumentException("Saved rook does not match the board.");
            }
        }
    }

    public boolean saveCurrentGame() {
        if (gameRepository == null || promotionPending || restoringHistory) {
            return false;
        }

        try {
            gameRepository.saveGame(
                    gameId,
                    getMoveHistory(),
                    moveCounter,
                    getGameStatus()
            );
            return true;
        } catch (IOException | SQLException error) {
            error.printStackTrace();

            JOptionPane.showMessageDialog(
                    chessboard.getChessGame(),
                    "The game could not be saved. Your latest changes have not been saved.",
                    "Save failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    public void closeResultWindow() {
        if (endingScreen != null) {
            endingScreen.dispose();
            endingScreen = null;
        }
    }

    /**.
     *
     * method is controlling the right rotation of the players
     * @param chessPiece checking its color
     * @return true if chessPiece has the right color, returns false if not
     */
    public boolean alternationOfPlayers(ChessPiece chessPiece){
        if (gameOver || promotionPending ){ return false; }
        if (moveCounter %2 == 0 && chessPiece.getChessPieceColor() == PieceColor.WHITE){
            setChessPieceMovementMap(chessPiece);
            return true;
        }else if (moveCounter %2 == 1 && chessPiece.getChessPieceColor() == PieceColor.BLACK){
            setChessPieceMovementMap(chessPiece);
            return true;
        }
        return false;
    }

    /**
     * method is controlling the game status
     * @param piece represents white or black side
     */
    private void outcome(ChessPiece piece){
        winner = null;

        if (impossibleEnding()){
            gameOver = true;
            showResult("Draw — insufficient material.");
            return;
        }
        if (setAllPossibleMovement(piece)){
            gameOver = true;
            if (setActualKingStatus(piece)){
                winner = piece.getChessPieceColor().opposite();
                String winnerName = winner == PieceColor.WHITE ? "White" : "Black";
                showResult("Checkmate — " + winnerName + " wins.");
            }else{
                showResult("Draw — stalemate.");
            }
        }
    }

    private void showResult(String message) {
        if (restoringHistory) {
            return;
        }
        endingScreen = new EndingScreen(chessboard, message);
    }

    /**
     *set all achievable movement for all existing pieces with the same color as specified param
     * @param piece
     * @return false if some movement is still available/true if not
     */
    private boolean setAllPossibleMovement(ChessPiece piece){
        for (ChessPiece p : chessboard.getListOfPieces()){
            if (p.getChessPieceColor() == piece.getChessPieceColor()){
                setChessPieceMovementMap(p);
                if (!p.getChessPieceMovementMap().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checks if king is in check at the present time
     * @param piece chess piece defining which king need's to be checked
     * @return true if it is/false if it is not
     */
    public boolean setActualKingStatus(ChessPiece piece){
        chessboard.getGameManager().setValidationInProcess(true);
        chessboard.getGameManager().setAttackingPiecesMovementMap(piece.getChessPieceColor(), chessboard.getListOfPieces());
        chessboard.getGameManager().setValidationInProcess(false);
        return chessboard.getGameManager().isKingAttacked(piece, chessboard.getListOfPieces());
    }
    /**
     * calls the controlling method if the click for move was done correctly, increases the moveCounter by one and verifies the game status
     */
    public void isItClickedForMove(ChessSquare clickedChessSquare){
        if(gameOver || promotionPending ){ return; }

        ChessPiece piece = chessboard.getSelectedPieceToMove();
        if(piece == null){ return; }

        int fromRow = piece.getRowPosition();
        int fromColumn = piece.getColumnPosition();
        int toRow = clickedChessSquare.getRowPosition();
        int toColumn = clickedChessSquare.getColumnPosition();

        boolean castling = piece instanceof King && Math.abs(toColumn - fromColumn) == 2;
        boolean promotion = piece instanceof Pawn && (toRow == 0 || toRow == 7);

        PieceState movingBefore = snapshot(piece);
        PieceState capturedBefore =
                snapshot(clickedChessSquare.getPieceOnSquare());

        CastlingMove castlingMove = null;

        if (castling) {
            int rookFromColumn = toColumn > fromColumn ? 7 : 0;
            int rookToColumn = toColumn > fromColumn ? 5 : 3;

            ChessPiece rook = chessboard.getArrayBoard()
                    [fromRow][rookFromColumn].getPieceOnSquare();

            castlingMove = new CastlingMove(
                    fromRow,
                    rookFromColumn,
                    rookToColumn,
                    snapshot(rook)
            );
        }

        PendingMove preparedMove = new PendingMove(
                fromRow,
                fromColumn,
                toRow,
                toColumn,
                movingBefore,
                capturedBefore,
                castlingMove
        );

        if (ChessPieceMovement.canPieceMakeThisMove(clickedChessSquare, chessboard)) {
            if (promotion) {
                pendingMove = preparedMove;
            } else {
                finishMove(preparedMove.complete(null));
            }
        }
    }

    private void finishMove(Move move) {
        // A completed move after Back starts a new continuation.
        moveHistory.subList(moveCounter, moveHistory.size()).clear();
        moveHistory.add(move);
        moveCounter++;

        System.out.println("Move " + moveCounter + ": " + move);

        evaluateCurrentPlayer();
        chessboard.updateHistoryControls();
        chessboard.refreshBoard();

        saveCurrentGame();
    }

    public boolean canUndo() {
        return !promotionPending && moveCounter > 0;
    }

    public boolean canRedo() {
        return !promotionPending && moveCounter < moveHistory.size();
    }

    public int getHistoryPosition() {
        return moveCounter;
    }

    public List<Move> getMoveHistory() {
        return List.copyOf(moveHistory);
    }

    public PieceColor getCurrentPlayerColor() {
        return moveCounter % 2 == 0 ? PieceColor.WHITE : PieceColor.BLACK;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean undoMove() {
        if (!canUndo()) {
            return false;
        }
        Move move = moveHistory.get(moveCounter - 1);
        prepareHistoryNavigation();

        if (move.promotion()) {
            removePieceAt(move.toRow(), move.toColumn());
            restorePiece(move.movingBefore(), move.fromRow(), move.fromColumn());
        } else {
            ChessPiece piece = chessboard.getArrayBoard()[move.toRow()][move.toColumn()].getPieceOnSquare();
            ChessPieceMovement.movingThePiece(chessboard.getArrayBoard()[move.fromRow()][move.fromColumn()], piece, chessboard);
            piece.setPieceFirstMove(move.movingBefore().firstMove());
        }

        if (move.castling()) {
            CastlingMove castling = move.castlingMove();
            ChessPiece rook = chessboard.getArrayBoard()[castling.row()][castling.toColumn()].getPieceOnSquare();
            ChessPieceMovement.movingThePiece(chessboard.getArrayBoard()[castling.row()][castling.fromColumn()], rook, chessboard);
            rook.setPieceFirstMove(castling.rookBefore().firstMove());
        }
        if (move.capture()) {
            restorePiece(move.capturedBefore(), move.toRow(), move.toColumn());
        }

        moveCounter--;
        finishHistoryNavigation();
        return true;
    }

    public boolean redoMove() {
        if (!canRedo()) {
            return false;
        }
        Move move = moveHistory.get(moveCounter);
        prepareHistoryNavigation();
        removePieceAt(move.toRow(), move.toColumn());

        if (move.promotion()) {
            removePieceAt(move.fromRow(), move.fromColumn());
            restorePiece(new PieceState(move.promotedTo(), move.movingBefore().color(), false), move.toRow(), move.toColumn());
        } else {
            ChessPiece piece = chessboard.getArrayBoard()[move.fromRow()][move.fromColumn()].getPieceOnSquare();
            ChessPieceMovement.movingThePiece(chessboard.getArrayBoard()[move.toRow()][move.toColumn()], piece, chessboard);
            piece.setPieceFirstMove(false);
        }

        if (move.castling()) {
            CastlingMove castling = move.castlingMove();
            ChessPiece rook = chessboard.getArrayBoard()[castling.row()][castling.fromColumn()].getPieceOnSquare();
            ChessPieceMovement.movingThePiece(chessboard.getArrayBoard()[castling.row()][castling.toColumn()], rook, chessboard);
            rook.setPieceFirstMove(false);
        }

        moveCounter++;
        finishHistoryNavigation();
        return true;
    }

    private void removePieceAt(int row, int column) {
        ChessPieceMovement.discardingThePiece(
                chessboard.getArrayBoard()[row][column],
                chessboard.getListOfPieces()
        );
    }

    private void restorePiece(PieceState state, int row, int column) {
        ChessPiece piece = ChessPieceFactory.create(state, row, column);
        chessboard.scalePieceImage(piece);
        chessboard.getListOfPieces().add(piece);
        chessboard.getArrayBoard()[row][column].setPieceOnSquare(piece);
    }

    private void prepareHistoryNavigation() {
        closeResultWindow();
        gameOver = false;
        winner = null;
        gameCheck = false;
        chessboard.setSelectedPieceToMove(null);
        for (ChessPiece piece : chessboard.getListOfPieces()) {
            piece.getChessPieceMovementMap().clear();
        }
    }

    private void finishHistoryNavigation() {
        if (restoringHistory) {
            return;
        }
        evaluateCurrentPlayer();
        chessboard.updateHistoryControls();
        chessboard.refreshBoard();

        saveCurrentGame();
    }

    public void completePromotion(PieceType selectedType) {
        if (!promotionPending || pendingMove == null) {
            throw new IllegalStateException("No promotion is pending.");
        }

        if (selectedType != PieceType.QUEEN
                && selectedType != PieceType.ROOK
                && selectedType != PieceType.BISHOP
                && selectedType != PieceType.KNIGHT) {
            throw new IllegalArgumentException("Invalid promotion piece type.");
        }

        Move completedMove = pendingMove.complete(selectedType);

        pendingMove = null;
        promotionPending = false;

        finishMove(completedMove);
    }

    public void evaluateCurrentPlayer(){
        if(gameOver || promotionPending ){ return;}
        ChessPiece king = moveCounter % 2 == 0
                ? chessboard.getChessPiecesPackage().getWhiteKing()
                : chessboard.getChessPiecesPackage().getBlackKing();

        outcome(king);
    }

    private PieceState snapshot(ChessPiece piece){
        if(piece == null){ return null; }

        return new PieceState(
                piece.getPieceType(),
                piece.getChessPieceColor(),
                piece.getPieceFirstMove()
        );
    }

    /**
     * method checks if list of active pieces consists only of 2Kings or 2Kings and 1Bishop/Knight
     * @return true if any of these alternatives is true
     */
    private boolean impossibleEnding(){
        if (chessboard.getListOfPieces().size() == 2){
            return true;
        } else if (chessboard.getListOfPieces().size() == 3) {
            for (ChessPiece piece : chessboard.getListOfPieces()){
                if (piece instanceof Bishop){
                    return true;
                } else if (piece instanceof Knight) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * set up possible movement of all chess pieces which poses opposite color of specified @param
     * @param color color of defending chess piece
     */
    private void setAttackingPiecesMovementMap(PieceColor color, ArrayList<ChessPiece> list){
        for (ChessPiece chessPiece : list) {
            if (color != chessPiece.getChessPieceColor()){
                    setChessPieceMovementMap(chessPiece);
            }
        }
    }

    /**
     * set up movement of single chess piece
     * @param chessPiece chess piece setting up
     */
    private void setChessPieceMovementMap(ChessPiece chessPiece){
        chessPiece.getChessPieceMovementMap().clear();
        chessPiece.setChessPieceMovementMap(chessboard);
    }

    /**
     * look up if king of specified chess piece as param is in check
     * @param chessPiece piece which´s king is being checked
     * @param list method is controlling attacking pieces from the specified list
     */
    public boolean isKingAttacked(ChessPiece chessPiece, ArrayList<ChessPiece> list){
        ChessPiece checkedKing;
        if (chessPiece.getChessPieceColor() == PieceColor.WHITE){
            checkedKing = chessboard.getChessPiecesPackage().getWhiteKing();
        }else {
            checkedKing = chessboard.getChessPiecesPackage().getBlackKing();
        }
        for (ChessPiece piece : list){
            if (piece.getChessPieceColor() != checkedKing.getChessPieceColor()
                    && piece.getChessPieceMovementMap().containsKey(chessboard.getArrayBoard()[checkedKing.getRowPosition()][checkedKing.getColumnPosition()])){
                gameCheck = true;
                return true;
            }
        }
        gameCheck = false;
        return false;
    }

    /**
     * confirms or denies the move of chess piece on specified chess square
     * @param chessPiece piece making the move
     * @param newSquareSpot chess square
     * @return true if move is invalid cause of check / false if move is valid
     */
    public boolean moveValidation(ChessPiece chessPiece, ChessSquare newSquareSpot){
        ArrayList<ChessPiece> list = new ArrayList<>(chessboard.getListOfPieces());
        ChessPiece pieceOnSquare = newSquareSpot.getPieceOnSquare();
        int rowPosition = chessPiece.getRowPosition();
        int columnPosition = chessPiece.getColumnPosition();
        makeFakeMove(chessPiece, newSquareSpot, list);
        validationInProcess = true;
        setAttackingPiecesMovementMap(chessPiece.getChessPieceColor(), list);
        isKingAttacked(chessPiece, list);
        backToOriginalArrangement(chessPiece, newSquareSpot, rowPosition, columnPosition, pieceOnSquare);
        validationInProcess = false;
        return gameCheck;
    }

    /**
     * make the specified move
     * method is used during moveValidation process
     * @param chessPiece piece making move
     * @param chessSquare square spot
     */
    private void makeFakeMove(ChessPiece chessPiece, ChessSquare chessSquare, ArrayList<ChessPiece> list){
        ChessPieceMovement.discardingThePiece(chessSquare, list);
        ChessPieceMovement.movingThePiece(chessSquare, chessPiece, chessboard);
    }

    /**
     * rearrange chess pieces to arrangement before start of move validation process
     * @param chessPiece moving chess piece
     * @param chessSquare square spot
     * @param row original row position of chess piece
     * @param column original column position of chess piece
     * @param originalPiece chess piece which was on specified chess square
     */
    private void backToOriginalArrangement(ChessPiece chessPiece, ChessSquare chessSquare, int row, int column, ChessPiece originalPiece){
        chessSquare.setPieceOnSquare(originalPiece);

        chessboard.getArrayBoard()[row][column].setPieceOnSquare(chessPiece);
        chessPiece.setRowPosition(row);
        chessPiece.setColumnPosition(column);
    }

    /**
     * process of move validation
     * @return true if some move is already in process of validation, false if none
     */
    public boolean isValidationInProcess() {
        return validationInProcess;
    }

    public void setValidationInProcess(boolean validationInProcess) {
        this.validationInProcess = validationInProcess;
    }

    public boolean isGameCheck() {
        return gameCheck;
    }

    public boolean getPromotionPending(){ return this.promotionPending; }

    public void setPromotionPending(boolean status) {
        this.promotionPending = status;
        chessboard.updateHistoryControls();
    }
}
