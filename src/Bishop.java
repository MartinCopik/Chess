import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece {

    public Bishop(Color bishopColor, ImageIcon bishopImage, int rowPosition, int columPosition) {
        super(bishopColor, bishopImage, rowPosition, columPosition);
        super.pieceImageIcon = bishopImage;
    }


    @Override
    public Color getPieceColor() {
        return pieceColor;
    }


    void bishopMoveDiagonallyUpLeft(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (impossibleMove(row, colum)){
                break;
            }
        }
    }

    void bishopMoveDiagonallyDownLeft(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (impossibleMove(row, colum)){
                break;
            }
        }
    }
    void bishopMoveDiagonallyUpRight(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (impossibleMove(row, colum)){
                break;
            }
        }
    }
    void bishopMoveDiagonallyDownRight(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (impossibleMove(row, colum)){
                break;
            }
        }
    }

    @Override
    public void showMovePossibilities() {
        bishopMoveDiagonallyUpLeft();
        bishopMoveDiagonallyDownLeft();
        bishopMoveDiagonallyUpRight();
        bishopMoveDiagonallyDownRight();
    }
}
