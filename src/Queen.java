import javax.swing.*;
import java.awt.*;


public class Queen extends Piece {

    public Queen(Color queenColor, ImageIcon queenImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(queenColor, queenImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(queenImage);
    }

}