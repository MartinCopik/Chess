import javax.swing.*;
import java.awt.*;


public class Rook extends Piece{

    public Rook(Player player, Color rookColor, ImageIcon rookImage, int rowPosition, int columPosition) {
        super(player, rookColor, rookImage, rowPosition, columPosition);
        super.setPieceImageIcon(rookImage);
    }

    public void rookMoveUP(){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (impossibleMove(getPlayer(),row,this.getColumPosition())){
                break;
            }
        }
    }

    public void rookMoveDown(){
        for (int row = getRowPosition()+1; row<= 7; row++){
            if (impossibleMove(getPlayer(),row,this.getColumPosition())){
                break;
            }
        }
    }
    public void rookMoveLeft(){
        for (int colum = getColumPosition()-1; colum>= 0; colum--){
            if (impossibleMove(getPlayer(),this.getRowPosition(),colum)){
                break;
            }
        }
    }
    public void rookMoveRight(){
        for (int colum = getColumPosition()+1; colum<= 7; colum++){
            if (impossibleMove(getPlayer(),this.getRowPosition(),colum)){
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
