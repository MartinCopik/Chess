import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece {

    public Bishop(Player player, Color bishopColor, ImageIcon bishopImage, int rowPosition, int columPosition) {
        super(player, bishopColor, bishopImage, rowPosition, columPosition);
        super.setPieceImageIcon(bishopImage);
    }

    public void bishopMoveDiagonallyUpLeft(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (impossibleMove(getPlayer(),row, colum)){
                break;
            }
        }
    }

    public void bishopMoveDiagonallyDownLeft(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (impossibleMove(getPlayer(),row, colum)){
                break;
            }
        }
    }
    public void bishopMoveDiagonallyUpRight(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (impossibleMove(getPlayer(),row, colum)){
                break;
            }
        }
    }
    public void bishopMoveDiagonallyDownRight(){
        int row = this.getRowPosition();
        int colum = this.getColumPosition();
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (impossibleMove(getPlayer(),row, colum)){
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
