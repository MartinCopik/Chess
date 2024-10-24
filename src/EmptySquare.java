import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EmptySquare extends Piece implements MouseListener {

    private Piece pieceOnSquare;
    private Chessboard chessboard;

    public EmptySquare(Color emptyPieceColor, int rowPosition, int columPosition, int withOfPiece, int heightOfPiece, Chessboard chessboard) {
        super(emptyPieceColor, rowPosition, columPosition, withOfPiece, heightOfPiece);
        super.setPieceColor(emptyPieceColor);
        super.getEmptyPiecePanel().setSize(getWidthOfPiece(), getHeightOfPiece());
        super.getEmptyPiecePanel().setBackground(emptyPieceColor);
        super.getEmptyPiecePanel().setOpaque(true);
        super.getEmptyPiecePanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        super.getEmptyPiecePanel().addMouseListener(this);
        setChessboard(chessboard);
    }

    public Piece getPieceOnSquare() {
        return pieceOnSquare;
    }

    public void setPieceOnSquare(Piece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
        if (pieceOnSquare != null){
            this.getEmptyPiecePanel().add(pieceOnSquare.getPieceLabel());
        }
    }

    public void discardPieceFromSquare(){
        setPieceOnSquare(null);
        this.getEmptyPiecePanel().removeAll();
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void markTheSquareForMove(){
        getEmptyPiecePanel().setBackground(Color.gray);
        if (getChessboard().getSelectedPieceToMove() != null){
            getEmptyPiecePanel().setBackground(Color.green);
        }
    }
    public void markTheSquareForAttack(){
        getEmptyPiecePanel().setBackground(Color.pink);
        if (getChessboard().getSelectedPieceToMove() != null){
            getEmptyPiecePanel().setBackground(Color.red);
        }
    }

    public void clickedForMove(){
        if (getEmptyPiecePanel().getBackground() == Color.green){
            getChessboard().getSelectedPieceToMove().getMove().makeCleanMove(this, getChessboard().getSelectedPieceToMove(), getChessboard());
        } else if (getEmptyPiecePanel().getBackground() == Color.red) {
            getPieceOnSquare().getMove().makeDiscardMovePiece(this, getPieceOnSquare(), getChessboard().getSelectedPieceToMove(), getChessboard());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickedForMove();
        if (getPieceOnSquare() != null){
            if (getChessboard().getSelectedPieceToMove() != null){
                getChessboard().setSelectedPieceToMove(null);
                getChessboard().setColors();
            }else {
                getChessboard().setSelectedPieceToMove(getPieceOnSquare());
                getPieceOnSquare().showMovePossibilities(chessboard);
            }
        }else {
            getChessboard().setSelectedPieceToMove(null);
            getChessboard().setColors();
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (getChessboard().getSelectedPieceToMove() == null){
            if (getPieceOnSquare() != null){
                getPieceOnSquare().showMovePossibilities(getChessboard());
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (getChessboard().getSelectedPieceToMove() == null){
                chessboard.setColors();
        }
    }

}