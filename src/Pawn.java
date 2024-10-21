import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{

    public Pawn(Color pawnColor, ImageIcon pawnImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(pawnColor,pawnImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(pawnImage);
    }
}