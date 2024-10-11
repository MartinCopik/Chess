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
        super.getEmptyPiecePanel().setSize(width, height);
        super.getEmptyPiecePanel().setBackground(emptyPieceColor);
        super.getEmptyPiecePanel().setOpaque(true);
        super.getEmptyPiecePanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    static void markTheSquareForAttack(Piece emptyPieceToMark){
        emptyPieceToMark.getEmptyPiecePanel().setBackground(Color.pink);
        if (Move.figureToMove != null){
            emptyPieceToMark.getEmptyPiecePanel().setBackground(Color.RED);
        }
    }
    static void markTheSquareForMove(Piece emptyPieceToMark){
        emptyPieceToMark.getEmptyPiecePanel().setBackground(Color.gray);
        if (Move.figureToMove != null){
            emptyPieceToMark.getEmptyPiecePanel().setBackground(Color.green);
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
