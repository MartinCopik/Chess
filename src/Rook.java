import javax.swing.*;
import java.awt.*;


public class Rook extends Piece{
    int value = 5;

    public Rook(Color rookColor, ImageIcon rookImage) {
        super(rookColor, rookImage);
        super.pieceImageIcon = rookImage;
    }

    void rookMoveUP(){
        for (int row = rowPosition-1; row >= 0 ; row--){
            if (impossibleMove(row,this.columPosition)){
                break;
            }
        }
    }

    void rookMoveDown(){
        for (int row = rowPosition+1; row<= 7; row++){
            if (impossibleMove(row,this.columPosition)){
                break;
            }
        }
    }
    void rookMoveLeft(){
        for (int colum = columPosition-1; colum>= 0; colum--){
            if (impossibleMove(this.rowPosition,colum)){
                break;
            }
        }
    }
    void rookMoveRight(){
        for (int colum = columPosition+1; colum<= 7; colum++){
            if (impossibleMove(this.rowPosition,colum)){
                break;
            }
        }
    }
    @Override
    public void showMovePossibilities() {

        rookMoveUP();
        rookMoveDown();
        rookMoveLeft();
        rookMoveRight();
    }
}
