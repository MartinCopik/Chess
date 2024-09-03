import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Color knightColor, ImageIcon knightImage, int rowPosition, int columPosition){
        super(knightColor,knightImage, rowPosition, columPosition);
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
