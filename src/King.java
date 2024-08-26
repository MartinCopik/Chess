import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    public King(Color kingColor, ImageIcon kingImage) {
        super(kingColor,kingImage);
        super.pieceImageIcon = kingImage;
    }

    void kingMoveDiagonallyUpLeft(){
        impossibleMove(rowPosition-1, columPosition-1);
    }

    void kingMoveDiagonallyUpRight(){
        impossibleMove(rowPosition-1,columPosition+1);
    }
    void kingMoveDiagonallyDownLeft(){
        impossibleMove(rowPosition+1, columPosition-1);
    }
    void kingMoveDiagonallyDownRight(){
        impossibleMove(rowPosition+1, columPosition+1);
    }

    void kingMoveUp(){
        impossibleMove(rowPosition-1, columPosition);
    }

    void kingMoveDown(){
        impossibleMove(rowPosition+1, columPosition);
    }
    void kingMoveLeft(){
        impossibleMove(rowPosition, columPosition-1);
    }
    void kingMoveRight(){
        impossibleMove(rowPosition, columPosition+1);
    }

    @Override
    public void showMovePossibilities(){
        kingMoveUp();
        kingMoveDown();
        kingMoveRight();
        kingMoveLeft();
        kingMoveDiagonallyUpLeft();
        kingMoveDiagonallyUpRight();
        kingMoveDiagonallyDownLeft();
        kingMoveDiagonallyDownRight();
    }
}
