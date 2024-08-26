import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {
    int value = 3;

    public Knight(Color knightColor, ImageIcon knightImage){
        super(knightColor,knightImage);
        super.pieceImageIcon = knightImage;
    }

    void knightMoveUpShortLeft(){
        impossibleMove(rowPosition-1, columPosition-2);
    }
    void knightMoveUpShortRight(){
        impossibleMove(rowPosition-1, columPosition+2);
    }
    void knightMoveUpLongLeft(){
        impossibleMove(rowPosition-2, columPosition-1);
    }
    void knightMoveUpLongRight(){
        impossibleMove(rowPosition-2, columPosition+1);
    }
    void knightMoveDownShortLeft(){
        impossibleMove(rowPosition+1, columPosition-2);
    }
    void knightMoveDownShortRight(){
        impossibleMove(rowPosition+1, columPosition+2);
    }
    void knightMoveDownLongLeft(){
        impossibleMove(rowPosition+2, columPosition-1);
    }
    void knightMoveDownLongRight(){
        impossibleMove(rowPosition+2, columPosition+1);
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
