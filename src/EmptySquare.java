import javax.swing.*;
import java.awt.*;

public class EmptySquare extends Piece{

    private Piece pieceOnSquare;

    public EmptySquare(Color emptyPieceColor, int rowPosition, int columPosition, int withOfPiece, int heightOfPiece) {
        super(emptyPieceColor, rowPosition, columPosition, withOfPiece, heightOfPiece);
        super.getEmptyPiecePanel().setSize(getWidthOfPiece(), getHeightOfPiece());
        super.getEmptyPiecePanel().setBackground(emptyPieceColor);
        super.getEmptyPiecePanel().setOpaque(true);
        super.getEmptyPiecePanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Piece getPieceOnSquare() {
        return pieceOnSquare;
    }

    public void setPieceOnSquare(Piece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
        this.getEmptyPiecePanel().add(pieceOnSquare.getPieceLabel());
    }

    public void discardPieceFromSquare(){
        setPieceOnSquare(null);
        this.getEmptyPiecePanel().removeAll();
    }

}