import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    Piece castledKing;

    public King(Color kingColor, ImageIcon kingImage, int rowPosition, int columPosition) {
        super(kingColor,kingImage, rowPosition, columPosition);
        super.pieceImageIcon = kingImage;
    }
//    boolean kingIsInCheck(){
//
//    }

    Color checkAttackingColor(Piece king){
        if (king.pieceColor.equals(Color.WHITE)){
            return Color.BLACK;
        }else {
            return Color.WHITE;
        }
    }
    void setCastling(){
        if (this.pieceColor.equals(Color.WHITE)){
            possibleSmallCastling(Color.BLACK);
            possibleBigCastling(Color.BLACK);
        } else {
            possibleSmallCastling(Color.WHITE);
            possibleBigCastling(Color.WHITE);
        }
    }

    boolean possibleSmallCastling(Color attackingColor){
        if (this.pieceFirstMove && Chessboard.getArrayBoard()[this.rowPosition][this.columPosition+3][1].pieceFirstMove){

            if (!positionIsTaken(this.rowPosition, this.columPosition+1)
                    && !positionIsTaken(this.rowPosition, this.columPosition+2)){

                if (!EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition+1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition+2, attackingColor)){
                    Chessboard.getArrayBoard()[this.rowPosition][this.columPosition+2][0].emptyPiecePanel.setBackground(Color.gray);
                    if (Move.figureToMove != null){
                        Chessboard.getArrayBoard()[this.rowPosition][this.columPosition+2][0].emptyPiecePanel.setBackground(Color.green);
                    }
                    return true;
                }
            }
        }return false;
    }
    boolean possibleBigCastling(Color attackingColor){
        if (this.pieceFirstMove && Chessboard.getArrayBoard()[this.rowPosition][this.columPosition-4][1].pieceFirstMove){

            if (!positionIsTaken(this.rowPosition, this.columPosition-1)
                    && !positionIsTaken(this.rowPosition, this.columPosition-2)
                    && !positionIsTaken(this.rowPosition, this.columPosition-3)){

                if (!EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition-1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition-2, attackingColor)){
                    Chessboard.getArrayBoard()[this.rowPosition][this.columPosition-2][0].emptyPiecePanel.setBackground(Color.gray);
                    if (Move.figureToMove != null){
                        Chessboard.getArrayBoard()[this.rowPosition][this.columPosition-2][0].emptyPiecePanel.setBackground(Color.green);
                    }
                    return true;
                }
            }
        }return false;
    }

    void kingMoveDiagonallyUpLeft(){
        impossibleMove(rowPosition-1, columPosition-1);
    }

    void kingMoveDiagonallyUpRight(){
        impossibleMove(rowPosition-1,columPosition+1);
    }
    void kingMoveDiagonallyDownLeft(){
        impossibleMove(rowPosition+1, columPosition-1);
    }
    void kingMoveDiagonallyDownRight(){
        impossibleMove(rowPosition+1, columPosition+1);
    }

    void kingMoveUp(){
        impossibleMove(rowPosition-1, columPosition);
    }

    void kingMoveDown(){
        impossibleMove(rowPosition+1, columPosition);
    }
    void kingMoveLeft(){
        impossibleMove(rowPosition, columPosition-1);
    }
    void kingMoveRight(){
        impossibleMove(rowPosition, columPosition+1);
    }

    @Override
    public void showMovePossibilities(){
        if (this.pieceMove){
            setCastling();
        }

        kingMoveUp();
        kingMoveDown();
        kingMoveRight();
        kingMoveLeft();
        kingMoveDiagonallyUpLeft();
        kingMoveDiagonallyUpRight();
        kingMoveDiagonallyDownLeft();
        kingMoveDiagonallyDownRight();
    }
}
