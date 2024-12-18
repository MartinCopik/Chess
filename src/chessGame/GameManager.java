package chessGame;

import chessPieces.Bishop;
import chessPieces.ChessPiece;
import chessPieces.Knight;

import java.awt.*;
import java.util.ArrayList;

/**
 * class take care of rotation of players, move validation, checks game status
 */
public class GameManager {
    private int moveCounter;
    private final Chessboard chessboard;
    private boolean validationInProcess;
    private boolean gameCheck = false;

    public GameManager(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    /**
     * method is controlling the right rotation of the players
     * @param chessPiece checking its color
     * @return true if chessPiece has the right color, returns false if not
     */
    public boolean alternationOfPlayers(ChessPiece chessPiece){
        if (chessPiece.getChessPieceColor().equals(moveCounter % 2 == 0 ? Color.WHITE : Color.BLACK)){
            outcome(chessPiece);
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
        if (impossibleEnding()){
            new EndingScreen(chessboard, "END");
        }
        if (setAllPossibleMovement(piece)){
            if (getActualKingStatus(piece)){
                new EndingScreen(chessboard, "CHM");
            }else{
                new EndingScreen(chessboard, "PAT");
            }

        }
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
    public boolean getActualKingStatus(ChessPiece piece){
        chessboard.getGameManager().setValidationInProcess(true);
        chessboard.getGameManager().setAttackingPiecesMovementMap(piece.getChessPieceColor(), chessboard.getListOfPieces());
        chessboard.getGameManager().setValidationInProcess(false);
        return chessboard.getGameManager().isKingAttacked(piece, chessboard.getListOfPieces());
    }
    /**
     * calls the controlling method if the click for move was done correctly and increases the moveCounter by one
     */
    public void isItClickedForMove(ChessSquare clickedChessSquare){
        if (ChessPieceMovement.canPieceMakeThisMove(clickedChessSquare, chessboard)){
            moveCounter++;
        }
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
    private void setAttackingPiecesMovementMap(Color color, ArrayList<ChessPiece> list){
        for (ChessPiece chessPiece : list) {
            if (!color.equals(chessPiece.getChessPieceColor())){
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
        checkedKing = chessPiece.getChessPieceColor().equals(ChessPiecesPackage.getWhiteKing().getChessPieceColor())
                ? checkedKing = ChessPiecesPackage.getWhiteKing() : ChessPiecesPackage.getBlackKing();
        for (ChessPiece piece : list){
            if (!piece.getChessPieceColor().equals(checkedKing.getChessPieceColor())
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
}
