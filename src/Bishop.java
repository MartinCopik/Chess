import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece {

    public Bishop(Color bishopColor, ImageIcon bishopImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(bishopColor, bishopImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(bishopImage);
    }
}