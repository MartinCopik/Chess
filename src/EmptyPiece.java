import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class EmptyPiece extends Piece{

    public EmptyPiece( Color emptyPieceColor, int rowPosition, int columPosition) {
        super(emptyPieceColor, rowPosition, columPosition);
        super.getEmptyPiecePanel().setSize(getWidth(),getHeight());
        super.getEmptyPiecePanel().setBackground(emptyPieceColor);
        super.getEmptyPiecePanel().setOpaque(true);
        super.getEmptyPiecePanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    public void markTheSquareForAttack(){
        this.getEmptyPiecePanel().setBackground(Color.pink);
        if (Move.getFigureToMove() != null){
            this.getEmptyPiecePanel().setBackground(Color.RED);
        }
    }
    public void markTheSquareForMove(){
        this.getEmptyPiecePanel().setBackground(Color.gray);
        if (Move.getFigureToMove() != null){
            this.getEmptyPiecePanel().setBackground(Color.green);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.getEmptyPiecePanel().getBackground() == Color.GREEN){
            Move.makeCleanMove(this);
        } else if (this.getEmptyPiecePanel().getBackground() == Color.RED) {
            Move.makeMoveDiscardPiece(this);
        }else {
            Chessboard.setColors();
            Move.setFigureToMove(null);
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
}
