import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    public King(Color kingColor, ImageIcon kingImage, int rowPosition, int columPosition) {
        super(kingColor,kingImage, rowPosition, columPosition);
        super.pieceImageIcon = kingImage;
    }
    void castling(){
        if (this.pieceColor.equals(Color.WHITE)){
            settingCastling(Color.BLACK);
        } else {
            settingCastling(Color.WHITE);
        }
    }

    void settingCastling(Color attackingColor){
        if (this.pieceFirstMove && Chessboard.getArrayBoard()[this.rowPosition][this.columPosition+3][1].pieceFirstMove){

//            squareIsUnderAttack(Chessboard.blackPlayer);
            if (!positionIsTaken(this.rowPosition, this.columPosition+1) && !positionIsTaken(this.rowPosition, this.columPosition+2)){
//                System.out.println("this " + this);
//                System.out.println("policka su prazdne");
//                System.out.println(this.rowPosition + " row a colum " + this.columPosition);
//                System.out.println("check if contains is used rightly "+EmptyPiece.attackedSquares.getOrDefault(Chessboard.getEmptySquare(2,0),Color.WHITE));
//                System.out.println("check if contains is used rightly "+EmptyPiece.attackedSquares.getOrDefault(Chessboard.getEmptySquare(2,0),Color.BLACK));
//                System.out.println("check if contains is used rightly "+EmptyPiece.attackedSquares.getOrDefault(Chessboard.getEmptySquare(2,0),null));
                if (!EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition+1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition+2, attackingColor)){
                    System.out.println("king may do a small castling");
                    System.out.println(this.pieceColor);
                    System.out.println(this.rowPosition + " " +this.columPosition);
                }


            }
//            impossibleMove(rowPosition, columPosition+2);
        }
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
        castling();

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
