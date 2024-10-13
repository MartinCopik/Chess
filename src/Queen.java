import javax.swing.*;
import java.awt.*;


public class Queen extends Piece {

    public Queen(Player player, Color queenColor, ImageIcon queenImage, int rowPosition, int columPosition) {
        super(player, queenColor, queenImage, rowPosition, columPosition);
        super.setPieceImageIcon(queenImage);
    }

    public void queenMoveUP(){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (impossibleMove(getPlayer(),row,this.getColumPosition())){
                break;
            }
        }
    }
    public void queenMoveDown(){
        for (int row = getRowPosition()+1; row<= 7; row++){
            if (impossibleMove(getPlayer(),row,this.getColumPosition())){
                break;
            }
        }
    }
    public void queenMoveLeft(){
        for (int colum = getColumPosition()-1; colum>= 0; colum--){
            if (impossibleMove(getPlayer(),this.getRowPosition(),colum)){
                break;
            }
        }
    }
    public void queenMoveRight(){
        for (int colum = getColumPosition()+1; colum<= 7; colum++){
            if (impossibleMove(getPlayer(),this.getRowPosition(),colum)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyUpLeft(){
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

    public void queenMoveDiagonallyDownLeft(){
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
    public void queenMoveDiagonallyUpRight(){
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
    public void queenMoveDiagonallyDownRight(){
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

        queenMoveUP();
        queenMoveDown();
        queenMoveLeft();
        queenMoveRight();
        queenMoveDiagonallyUpLeft();
        queenMoveDiagonallyUpRight();
        queenMoveDiagonallyDownLeft();
        queenMoveDiagonallyDownRight();
    }
}
