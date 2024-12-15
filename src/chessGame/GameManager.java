package chessGame;

import chessPieces.ChessPiece;

import java.awt.*;

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

    private void checkPatStatus(ChessPiece chessPiece){

    }

    /**
     * method is controlling the right rotation of the players
     * @param chessPiece checking its color
     * @return true if chessPiece has the right color, returns false if not
     */
    public boolean alternationOfPlayers(ChessPiece chessPiece){
        if (moveCounter %2 == 0 && chessPiece.getChessPieceColor().equals(Color.WHITE)){
            setChessPieceMovementMap(chessPiece);
            //cize tuna mam informaciu kolko pohybov moze urobit cierny
            return true;
        }else if (moveCounter %2 == 1 && chessPiece.getChessPieceColor().equals(Color.BLACK)){
            setChessPieceMovementMap(chessPiece);
            //a tu kolko pohybov moze urobit biely 
            return true;
        }
        return false;
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
     * set up possible movement of all chess pieces which poses opposite color of specified @param
     * @param color color of defending chess piece
     */
    public void setAttackingPiecesMovementMap(Color color){
        for (ChessPiece chessPiece : chessboard.getListOfPieces()) {
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
     */
    public boolean isKingAttacked(ChessPiece chessPiece){
        ChessPiece checkedKing;
        if (chessPiece.getChessPieceColor().equals(chessboard.getChessPiecesPackage().getWhiteKing().getChessPieceColor())){
            checkedKing = chessboard.getChessPiecesPackage().getWhiteKing();
        }else {
            checkedKing = chessboard.getChessPiecesPackage().getBlackKing();
        }
        for (ChessPiece piece : chessboard.getListOfPieces()){
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
        ChessPiece pieceOnSquare = newSquareSpot.getPieceOnSquare();
        int rowPosition = chessPiece.getRowPosition();
        int columnPosition = chessPiece.getColumnPosition();
        makeFakeMove(chessPiece, newSquareSpot);
        validationInProcess = true;
        setAttackingPiecesMovementMap(chessPiece.getChessPieceColor());
        isKingAttacked(chessPiece);
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
    public void makeFakeMove(ChessPiece chessPiece, ChessSquare chessSquare){
        ChessPieceMovement.discardingThePiece(chessSquare, chessboard);
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
    public void backToOriginalArrangement(ChessPiece chessPiece, ChessSquare chessSquare, int row, int column, ChessPiece originalPiece){
        chessSquare.setPieceOnSquare(originalPiece);
        if (originalPiece != null){
            chessboard.getListOfPieces().add(originalPiece);
        }

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
