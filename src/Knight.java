import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Player player, Color knightColor, ImageIcon knightImage, int rowPosition, int columPosition){
        super(player, knightColor,knightImage, rowPosition, columPosition);
        super.pieceImageIcon = knightImage;
    }

    void knightMoveUpShortLeft(){
        impossibleMove(player,rowPosition-1, columPosition-2);
    }
    void knightMoveUpShortRight(){
        impossibleMove(player,rowPosition-1, columPosition+2);
    }
    void knightMoveUpLongLeft(){
        impossibleMove(player,rowPosition-2, columPosition-1);
    }
    void knightMoveUpLongRight(){
        impossibleMove(player,rowPosition-2, columPosition+1);
    }
    void knightMoveDownShortLeft(){
        impossibleMove(player,rowPosition+1, columPosition-2);
    }
    void knightMoveDownShortRight(){
        impossibleMove(player,rowPosition+1, columPosition+2);
    }
    void knightMoveDownLongLeft(){
        impossibleMove(player,rowPosition+2, columPosition-1);
    }
    void knightMoveDownLongRight(){
        impossibleMove(player,rowPosition+2, columPosition+1);
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
