import javax.swing.*;
import java.awt.*;


public class Queen extends Piece {

    public Queen(Player player, Color queenColor, ImageIcon queenImage, int rowPosition, int columPosition) {
        super(player, queenColor, queenImage, rowPosition, columPosition);
        super.pieceImageIcon = queenImage;
    }

    @Override
    public Color getPieceColor() {
        return pieceColor;
    }

    void queenMoveUP(){
        for (int row = rowPosition-1; row >= 0 ; row--){
            if (impossibleMove(player,row,this.columPosition)){
                break;
            }
        }
    }
    void queenMoveDown(){
        for (int row = rowPosition+1; row<= 7; row++){
            if (impossibleMove(player,row,this.columPosition)){
                break;
            }
        }
    }
    void queenMoveLeft(){
        for (int colum = columPosition-1; colum>= 0; colum--){
            if (impossibleMove(player,this.rowPosition,colum)){
                break;
            }
        }
    }
    void queenMoveRight(){
        for (int colum = columPosition+1; colum<= 7; colum++){
            if (impossibleMove(player,this.rowPosition,colum)){
                break;
            }
        }
    }
    void queenMoveDiagonallyUpLeft(){
        int row = this.rowPosition;
        int colum = this.columPosition;
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (impossibleMove(player,row, colum)){
                break;
            }
        }
    }

    void queenMoveDiagonallyDownLeft(){
        int row = this.rowPosition;
        int colum = this.columPosition;
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (impossibleMove(player,row, colum)){
                break;
            }
        }
    }
    void queenMoveDiagonallyUpRight(){
        int row = this.rowPosition;
        int colum = this.columPosition;
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (impossibleMove(player,row, colum)){
                break;
            }
        }
    }
    void queenMoveDiagonallyDownRight(){
        int row = this.rowPosition;
        int colum = this.columPosition;
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (impossibleMove(player,row, colum)){
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
