import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{

    public Pawn(Player player, Color pawnColor, ImageIcon pawnImage, int rowPosition, int columPosition) {
        super(player, pawnColor,pawnImage, rowPosition, columPosition);
        super.pieceImageIcon = pawnImage;
    }

    void readyToBePromoted(){
        setActualPositionOfPiece(this);
        if (getRowPosition() == 0 || getRowPosition() == 7){
            new PromotionWindow(this);
            Move.discardingThePiece(Chessboard.getEmptySquare(this.getRowPosition(), this.getColumPosition()));
            Chessboard.panelBoard.repaint();
        }
    }

    void checkColourOfPawn(Piece pawnToCheck){
        if (pawnToCheck.pieceColor == Color.black){
            blackPawnMoves();
        } else if (pawnToCheck.pieceColor == Color.white) {
            whitePawnMoves();
        }
    }
    void blackPawnMoves(){
        pawnMoveDown();
        blackPawnAttacks();
    }
    void whitePawnMoves(){
        pawnMoveUp();
        whitePawnAttacks();
    }
    void blackPawnAttacks(){
        pawnMoveDiagonallyDownLeft();
        pawnMoveDiagonallyDownRight();
    }
    void whitePawnAttacks(){
        pawnMoveDiagonallyUpLeft();
        pawnMoveDiagonallyUpRight();
    }

    void pawnMoveUp(){
        if (this.pieceFirstMove && !positionIsTaken(this.getRowPosition()-1, this.getColumPosition())){
            impossibleMove(player,this.getRowPosition()-2, this.getColumPosition());
        }
        impossibleMove(player,this.getRowPosition()-1, this.getColumPosition());
    }

    void pawnMoveDown(){
        if (this.pieceFirstMove && !positionIsTaken(this.getRowPosition()+1, this.getColumPosition())){
            impossibleMove(player,this.getRowPosition()+2, this.getColumPosition());
        }
        impossibleMove(player,this.getRowPosition()+1, this.getColumPosition());
    }

    void pawnMoveDiagonallyUpLeft(){
        possibleAttackMove(this.getRowPosition()-1,this.getColumPosition()-1);
    }
    void pawnMoveDiagonallyUpRight(){
        possibleAttackMove(this.getRowPosition()-1,this.getColumPosition()+1);
    }
    void pawnMoveDiagonallyDownLeft(){
        possibleAttackMove(this.getRowPosition()+1, this.getColumPosition()-1);
    }
    void pawnMoveDiagonallyDownRight(){
        possibleAttackMove(this.getRowPosition()+1, this.getColumPosition()+1);
    }

    void possibleAttackMove(int rowToCheck, int columToCheck) {
        if (isOutOfBorder(rowToCheck, columToCheck)){
            return;
        }
//        if (player.king.kingIsInCheck() && this.pieceMove){
            if (player.pieceAttackingKing != null && Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] == player.pieceAttackingKing){
                if (GameManager.checkOfGameManager){
                    player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
                if (player.king.kingIsInCheck() && this.pieceMove){
                    EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                }
                return;
            }
//        }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (this.pieceMove && !player.king.kingIsInCheck()) {
                EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                return;
            }
            if (GameManager.checkOfGameManager && !player.king.kingIsInCheck){
                player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
        }
//        } else if (!positionIsTaken(rowToCheck, columToCheck)) {
        if (!this.pieceMove && !GameManager.checkOfGameManager){
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
        }
    }

    @Override
    boolean impossibleMove(Player player, int rowToCheck, int columToCheck) {
        if (isOutOfBorder(rowToCheck, columToCheck)){
            return true;
        }
        if (this.pieceMove && !player.king.kingIsInCheck()){
            if (!positionIsTaken(rowToCheck, columToCheck)){
                if (selfCheckOnEmptySquare(this, rowToCheck, columToCheck)){
                    return true;
                }
            }
        }
        if (!positionIsTaken(rowToCheck, columToCheck)){
            if (this.pieceMove){
                if (!player.king.kingIsInCheck() || stepIntoAttack(rowToCheck, columToCheck, this)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                }
            }
            if (GameManager.checkOfGameManager){
                if (!player.king.kingIsInCheck){
                    player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void showMovePossibilities() {
        checkColourOfPawn(this);
    }
}
