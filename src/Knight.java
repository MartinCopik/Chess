import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Player player, Color knightColor, ImageIcon knightImage, int rowPosition, int columPosition){
        super(player, knightColor,knightImage, rowPosition, columPosition);
        super.setPieceImageIcon(knightImage);
    }

    public void knightMoveUpShortLeft(){
        impossibleMove(getPlayer(),getRowPosition()-1, getColumPosition()-2);
    }
    public void knightMoveUpShortRight(){
        impossibleMove(getPlayer(),getRowPosition()-1, getColumPosition()+2);
    }
    public void knightMoveUpLongLeft(){
        impossibleMove(getPlayer(),getRowPosition()-2, getColumPosition()-1);
    }
    public void knightMoveUpLongRight(){
        impossibleMove(getPlayer(),getRowPosition()-2, getColumPosition()+1);
    }
    public void knightMoveDownShortLeft(){
        impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition()-2);
    }
    public void knightMoveDownShortRight(){
        impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition()+2);
    }
    public void knightMoveDownLongLeft(){
        impossibleMove(getPlayer(),getRowPosition()+2, getColumPosition()-1);
    }
    public void knightMoveDownLongRight(){
        impossibleMove(getPlayer(),getRowPosition()+2, getColumPosition()+1);
    }


    @Override
    public void showMovePossibilities() {
        knightMoveUpShortLeft();
        knightMoveUpShortRight();
        knightMoveUpLongLeft();
        knightMoveUpLongRight();

        knightMoveDownShortLeft();
        knightMoveDownShortRight();
        knightMoveDownLongLeft();
        knightMoveDownLongRight();
    }
}
