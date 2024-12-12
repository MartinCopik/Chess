package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;

import java.awt.*;

public class King extends ChessPiece {
    private ChessPiece smallCastlingRook;
    private ChessPiece bigCastlingRook;

    public King(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition, ChessPiece smallCastlingRook, ChessPiece bigCastlingRook ){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
        this.smallCastlingRook = smallCastlingRook;
        this.bigCastlingRook = bigCastlingRook;
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        if (!chessboard.getGameManager().isValidationInProcess()){
            castling(chessboard);
        }

        kingMoveUp(chessboard);
        kingMoveDown(chessboard);
        kingMoveLeft(chessboard);
        kingMoveRight(chessboard);
        kingMoveDiagonallyUpLeft(chessboard);
        kingMoveDiagonallyUpRight(chessboard);
        kingMoveDiagonallyDownLeft(chessboard);
        kingMoveDiagonallyDownRight(chessboard);
    }

    private void kingMoveUp(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition(), chessboard);
    }

    private void kingMoveDown(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition(), chessboard);
    }
    private void kingMoveLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition(), getColumnPosition()-1, chessboard);
    }
    private void kingMoveRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition(), getColumnPosition()+1, chessboard);
    }

    private void kingMoveDiagonallyUpLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()-1, chessboard);
    }

    private void kingMoveDiagonallyUpRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()+1, chessboard);
    }
    private void kingMoveDiagonallyDownLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()-1, chessboard);
    }
    private void kingMoveDiagonallyDownRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()+1, chessboard);
    }

    /**
     * set up possible castling movement and saves it in movement map
     * @param chessboard
     */
    private void castling(Chessboard chessboard){
        if (this.getPieceFirstMove() && !isKingCheck(chessboard)) {
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

    /**
     * checks if king is in check at the present time
     * @param chessboard
     * @return true if it is/false if it is not
     */
    private boolean isKingCheck(Chessboard chessboard){
        chessboard.getGameManager().setValidationInProcess(true);
        chessboard.getGameManager().setAttackingPiecesMovementMap(this.getChessPieceColor());
        chessboard.getGameManager().setValidationInProcess(false);
        return chessboard.getGameManager().isKingAttacked(this);
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
