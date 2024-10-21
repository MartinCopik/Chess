import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    public King(Color kingColor, ImageIcon kingImage, int RowPosition, int ColumPosition, int widthOfPiece, int heightOfPiece) {
        super(kingColor, kingImage, RowPosition, ColumPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(kingImage);
    }
}