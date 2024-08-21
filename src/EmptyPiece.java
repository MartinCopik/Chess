import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class EmptyPiece extends Piece{

    public EmptyPiece( Color emptyPieceColor, int rowPosition, int columPosition) {
        super(emptyPieceColor, rowPosition, columPosition);
        super.emptyPiecePanel.setBackground(emptyPieceColor);
        super.emptyPiecePanel.setOpaque(true);
        super.emptyPiecePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked from emptyPiece");
        if (this.emptyPiecePanel.getBackground() == Color.GREEN){
            Move.makeCleanMove(this);
        } else if (this.emptyPiecePanel.getBackground() == Color.RED) {
            Move.makeMoveDiscardPiece(this);
        }else {
            Chessboard.setColors();
            Move.figureToMove = null;
        }
    }
}
