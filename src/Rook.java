import javax.swing.*;
import java.awt.*;


public class Rook extends Piece{

    public Rook(Color rookColor, ImageIcon rookImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(rookColor, rookImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(rookImage);
    }
}