import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class EmptyPiece extends Piece{

    static int width = ChessGame.width/8;
    static int height = ChessGame.height/8;
    static HashMap <Piece,Piece> attackedSquares = new HashMap<>();

    public EmptyPiece( Color emptyPieceColor, int rowPosition, int columPosition) {
        super(emptyPieceColor, rowPosition, columPosition);
        super.emptyPiecePanel.setSize(width, height);
        super.emptyPiecePanel.setBackground(emptyPieceColor);
        super.emptyPiecePanel.setOpaque(true);
        super.emptyPiecePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    static void markTheSquareForAttack(Piece emptyPieceToMark){
        emptyPieceToMark.emptyPiecePanel.setBackground(Color.pink);
        if (Move.figureToMove != null){
            emptyPieceToMark.emptyPiecePanel.setBackground(Color.RED);
        }
    }
    static void markTheSquareForMove(Piece emptyPieceToMark){
        emptyPieceToMark.emptyPiecePanel.setBackground(Color.gray);
        if (Move.figureToMove != null){
            emptyPieceToMark.emptyPiecePanel.setBackground(Color.green);
        }
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

    static void arrangementOfAttackedSquares(Piece attackedSquare, Piece attackingFigure){
        attackedSquares.put(attackedSquare, attackingFigure);
    }

    static boolean isSquareUnderAttack(int rowToCheck, int columToCheck, Color colorOfAttackingPlayer){
        if (attackedSquares.containsKey(Chessboard.getEmptySquare(rowToCheck,columToCheck))
                && attackedSquares.get(Chessboard.getEmptySquare(rowToCheck, columToCheck)).getPieceColor().equals(colorOfAttackingPlayer)){

            return true;
        }
        return false;
    }

}
