import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Color knightColor, ImageIcon knightImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece){
        super(knightColor,knightImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(knightImage);
    }
}