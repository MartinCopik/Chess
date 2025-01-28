package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;
import chessPieces.MobilityOfPieces.DiagonalMobility;
import chessPieces.MobilityOfPieces.StraightMobility;

import java.awt.*;

public class King extends ChessPiece implements StraightMobility, DiagonalMobility {
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

        moveUP(chessboard, this);
        moveDown(chessboard, this);
        moveLeft(chessboard, this);
        moveRight(chessboard, this);
        moveDiagonallyUpLeft(chessboard, this);
        moveDiagonallyUpRight(chessboard, this);
        moveDiagonallyDownLeft(chessboard, this);
        moveDiagonallyDownRight(chessboard, this);
    }

    @Override
    public void moveUP(Chessboard chessboard, ChessPiece piece) {
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition(), chessboard);
    }
    @Override
    public void moveDown(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition(), chessboard);
    }
    @Override
    public void moveLeft(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition(), getColumnPosition()-1, chessboard);
    }
    @Override
    public void moveRight(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition(), getColumnPosition()+1, chessboard);
    }
    @Override
    public void moveDiagonallyUpLeft(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()-1, chessboard);
    }
    @Override
    public void moveDiagonallyUpRight(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()+1, chessboard);
    }
    @Override
    public void moveDiagonallyDownLeft(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()-1, chessboard);
    }
    @Override
    public void moveDiagonallyDownRight(Chessboard chessboard, ChessPiece piece){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()+1, chessboard);
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
