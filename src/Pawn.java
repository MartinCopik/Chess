import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{

    public Pawn(Color pawnColor, ImageIcon pawnImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(pawnColor,pawnImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(pawnImage);
    }
//
//    public void checkColourOfPawn(Piece pawnToCheck, Chessboard chessboard){
//        if (pawnToCheck.getPieceColor() == Color.black){
//            blackPawnMoves(chessboard);
//        } else {
//            whitePawnMoves(chessboard);
//        }
//    }
//    public void blackPawnMoves(Chessboard chessboard){
//        pawnMoveDown(chessboard);
//        blackPawnAttacks(chessboard);
//    }
//    public void whitePawnMoves(Chessboard chessboard){
//        pawnMoveUp(chessboard);
//        whitePawnAttacks(chessboard);
//    }
//    public void blackPawnAttacks(Chessboard chessboard){
//        pawnMoveDiagonallyDownLeft(chessboard);
//        pawnMoveDiagonallyDownRight(chessboard);
//    }
//    public void whitePawnAttacks(Chessboard chessboard){
//        pawnMoveDiagonallyUpLeft(chessboard);
//        pawnMoveDiagonallyUpRight(chessboard);
//    }
//
//    public void pawnMoveUp(Chessboard chessboard){
//        for (int row = this.getRowPosition()-1; row >= this.getRowPosition()-2; row--){
//            if (impossibleMove(row, this.getColumnPosition(), chessboard)){
//                break;
//            }
//            if (!this.getPieceFirstMove()){
//                break;
//            }
//        }
//    }
//
//    public void pawnMoveDown(Chessboard chessboard){
//        for (int row = this.getRowPosition()+1; row <= this.getRowPosition()+2; row++){
//            if (impossibleMove(row, this.getColumnPosition(), chessboard)){
//                break;
//            }
//            if (!this.getPieceFirstMove()){
//                break;
//            }
//        }
//    }
//
//    public void pawnMoveDiagonallyUpLeft(Chessboard chessboard){
//        possibleAttackMove(this.getRowPosition()-1,this.getColumnPosition()-1, chessboard);
//    }
//    public void pawnMoveDiagonallyUpRight(Chessboard chessboard){
//        possibleAttackMove(this.getRowPosition()-1,this.getColumnPosition()+1, chessboard);
//    }
//    public void pawnMoveDiagonallyDownLeft(Chessboard chessboard){
//        possibleAttackMove(this.getRowPosition()+1, this.getColumnPosition()-1, chessboard);
//    }
//    public void pawnMoveDiagonallyDownRight(Chessboard chessboard){
//        possibleAttackMove(this.getRowPosition()+1, this.getColumnPosition()+1, chessboard);
//    }
//    public void possibleAttackMove(int rowToCheck, int columToCheck, Chessboard chessboard) {
//        if (getMove().isOutOfBorder(rowToCheck, columToCheck, chessboard)){
//            return;
//        }
//        if (getMove().positionIsTaken(rowToCheck, columToCheck, chessboard) && getMove().pieceIsAttacking(this, rowToCheck, columToCheck, chessboard)) {
//            chessboard.getArrayBoard()[rowToCheck][columToCheck].markTheSquareForAttack();
//        }
//    }
//    @Override
//    public boolean impossibleMove(int rowToCheck, int columToCheck, Chessboard chessboard) {
//        if (getMove().isOutOfBorder(rowToCheck, columToCheck, chessboard)){
//            return true;
//        }
//        if (!getMove().positionIsTaken(rowToCheck, columToCheck, chessboard)){
//            chessboard.getArrayBoard()[rowToCheck][columToCheck].markTheSquareForMove();
//            return false;
//        }
//        return true;
//    }

    @Override
    public void showMovePossibilities(Chessboard chessboard) {
//        checkColourOfPawn(this, chessboard);
    }
}