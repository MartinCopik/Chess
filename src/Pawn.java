import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{

    public Pawn(Player player, Color pawnColor, ImageIcon pawnImage, int rowPosition, int columPosition) {
        super(player, pawnColor,pawnImage, rowPosition, columPosition);
        super.setPieceImageIcon(pawnImage);
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
        if (this.getPieceFirstMove() && !positionIsTaken(this.getRowPosition()-1, this.getColumPosition())){
            impossibleMove(getPlayer(),this.getRowPosition()-2, this.getColumPosition());
        }
        impossibleMove(getPlayer(),this.getRowPosition()-1, this.getColumPosition());
    }

    void pawnMoveDown(){
        if (this.getPieceFirstMove() && !positionIsTaken(this.getRowPosition()+1, this.getColumPosition())){
            impossibleMove(getPlayer(),this.getRowPosition()+2, this.getColumPosition());
        }
        impossibleMove(getPlayer(),this.getRowPosition()+1, this.getColumPosition());
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
//        if (getPlayer().king.kingIsInCheck() && this.getPieceMove()){
            if (getPlayer().getPieceAttackingKing() != null && Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] == getPlayer().getPieceAttackingKing()){
                if (GameManager.checkOfGameManager){
                    getPlayer().setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
                if (getPlayer().getKing().kingIsInCheck() && this.getPieceMove()){
                    EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                }
                return;
            }
//        }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (this.getPieceMove() && !getPlayer().getKing().kingIsInCheck()) {
                EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                return;
            }
            if (GameManager.checkOfGameManager && !getPlayer().getKing().kingIsInCheck){
                getPlayer().setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
        }
//        } else if (!positionIsTaken(rowToCheck, columToCheck)) {
        if (!this.getPieceMove() && !GameManager.checkOfGameManager){
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
        }
    }

    @Override
    boolean impossibleMove(Player player, int rowToCheck, int columToCheck) {
        if (isOutOfBorder(rowToCheck, columToCheck)){
            return true;
        }
        if (this.getPieceMove() && !player.getKing().kingIsInCheck()){
            if (!positionIsTaken(rowToCheck, columToCheck)){
                if (selfCheckOnEmptySquare(this, rowToCheck, columToCheck)){
                    return true;
                }
            }
        }
        if (!positionIsTaken(rowToCheck, columToCheck)){
            if (this.getPieceMove()){
                if (!player.getKing().kingIsInCheck() || stepIntoAttack(rowToCheck, columToCheck, this)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                }
            }
            if (GameManager.checkOfGameManager){
                if (!player.getKing().kingIsInCheck){
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
