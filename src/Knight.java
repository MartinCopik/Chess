import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Player player, Color knightColor, ImageIcon knightImage, int rowPosition, int columPosition){
        super(player, knightColor,knightImage, rowPosition, columPosition);
        super.setPieceImageIcon(knightImage);
    }

    void knightMoveUpShortLeft(){
        impossibleMove(getPlayer(),getRowPosition()-1, getColumPosition()-2);
    }
    void knightMoveUpShortRight(){
        impossibleMove(getPlayer(),getRowPosition()-1, getColumPosition()+2);
    }
    void knightMoveUpLongLeft(){
        impossibleMove(getPlayer(),getRowPosition()-2, getColumPosition()-1);
    }
    void knightMoveUpLongRight(){
        impossibleMove(getPlayer(),getRowPosition()-2, getColumPosition()+1);
    }
    void knightMoveDownShortLeft(){
        impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition()-2);
    }
    void knightMoveDownShortRight(){
        impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition()+2);
    }
    void knightMoveDownLongLeft(){
        impossibleMove(getPlayer(),getRowPosition()+2, getColumPosition()-1);
    }
    void knightMoveDownLongRight(){
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
