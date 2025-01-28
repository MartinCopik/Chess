package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;

import java.awt.*;

public class King extends ChessPiece {
    private ChessPiece smallCastlingRook;
    private ChessPiece bigCastlingRook;

    public King(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition, ChessPiece smallCastlingRook, ChessPiece bigCastlingRook){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
        this.smallCastlingRook = smallCastlingRook;
        this.bigCastlingRook = bigCastlingRook;
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        if (!chessboard.getGameManager().isValidationInProcess()){
            castling(chessboard);
        }

        EChessPiecesMovement.KING.moveUp(chessboard, this);
        EChessPiecesMovement.KING.moveDown(chessboard, this);
        EChessPiecesMovement.KING.moveLeft(chessboard, this);
        EChessPiecesMovement.KING.moveRight(chessboard, this);

        EChessPiecesMovement.KING.moveDiagonallyUpLeft(chessboard, this);
        EChessPiecesMovement.KING.moveDiagonallyUpRight(chessboard, this);
        EChessPiecesMovement.KING.moveDiagonallyDownLeft(chessboard, this);
        EChessPiecesMovement.KING.moveDiagonallyDownRight(chessboard, this);
    }
    /**
     * set up possible castling movement and saves it in movement map
     * @param chessboard
     */
    private void castling(Chessboard chessboard){
        if (this.getPieceFirstMove() && !chessboard.getGameManager().getActualKingStatus(this)) {
            if (smallCastlingRook.getPieceFirstMove()){
                    isSmallCastlingPossible(chessboard);
            }
            if (bigCastlingRook.getPieceFirstMove()){
                if (!ChessPieceMovement.positionIsTaken(this.getRowPosition(), 1, chessboard)){
                    isBigCastlingPossible(chessboard);
                }
            }
        }
    }

    private void isSmallCastlingPossible(Chessboard chessboard){
        for (int column = this.getColumnPosition()+1; column < this.getColumnPosition()+3; column++){
            if (checkCastlingMovement(column, chessboard)){
                break;
            }
        }
    }
    private void isBigCastlingPossible(Chessboard chessboard){
        for (int column = this.getColumnPosition()-1; column > this.getColumnPosition()-3; column--){
            if (checkCastlingMovement(column, chessboard)){
                break;
            }
        }
    }

    /**
     * checks specified position on chessboard
     * @param column column to check
     * @param chessboard
     * @return true if game is in check or specified position is taken
     */
    private boolean checkCastlingMovement(int column, Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition(), column, chessboard);
        if (chessboard.getGameManager().isGameCheck() || ChessPieceMovement.positionIsTaken(this.getRowPosition(),column, chessboard)){
            return true;
        }
        return false;
    }
}
