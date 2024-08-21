import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{
    int value = 1;

    public Pawn(Color pawnColor, ImageIcon pawnImage) {
        super(pawnColor);
        super.pieceImageIcon = pawnImage;
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
        if (this.pieceFirstMove){
            impossibleMove(this.getRowPosition()-2, this.getColumPosition());
        }
        impossibleMove(this.getRowPosition()-1, this.getColumPosition());
    }

    void pawnMoveDown(){
        if (this.pieceFirstMove){
            impossibleMove(this.getRowPosition()+2, this.getColumPosition());
        }
        impossibleMove(this.getRowPosition()+1, this.getColumPosition());
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
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)){
            Chessboard.arrayBoard[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.pink);
            if (Move.figureToMove != null){
                Chessboard.arrayBoard[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.red);
            }
        }
    }

    @Override
    boolean impossibleMove(int rowToCheck, int columToCheck) {
        if (isOutOfBorder(rowToCheck, columToCheck)){
            return true;
        }
        if (!positionIsTaken(rowToCheck, columToCheck)){
            Chessboard.arrayBoard[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.gray);
            if (Move.figureToMove != null){
                Chessboard.arrayBoard[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.green);
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
