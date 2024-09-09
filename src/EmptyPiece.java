import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class EmptyPiece extends Piece{

    static int width = ChessGame.width/8;
    static int height = ChessGame.height/8;
    static HashMap <Piece,Color> attackedSquares = new HashMap<>();

    public EmptyPiece( Color emptyPieceColor, int rowPosition, int columPosition) {
        super(emptyPieceColor, rowPosition, columPosition);
        super.emptyPiecePanel.setSize(width, height);
        super.emptyPiecePanel.setBackground(emptyPieceColor);
        super.emptyPiecePanel.setOpaque(true);
        super.emptyPiecePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.emptyPiecePanel.getBackground() == Color.GREEN){
            Move.makeCleanMove(this);
        } else if (this.emptyPiecePanel.getBackground() == Color.RED) {
            Move.makeMoveDiscardPiece(this);
        }else {
            Chessboard.setColors();
            Move.figureToMove = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    static void arrangementOfAttackedSquares(Piece attackedSquare, Color colorOfAttackingPlayer){
        attackedSquares.put(attackedSquare,colorOfAttackingPlayer);
    }

}
