import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EmptySquare implements MouseListener {

    private ChessPiece pieceOnSquare;
    private Chessboard chessboard;

    private Color emptyPieceColor;
    private int rowPosition;
    private int columnPosition;
    private JPanel emptyPiecePanel;

    public EmptySquare(Color emptyPieceColor, int rowPosition, int columPosition, int withOfPiece, int heightOfPiece, Chessboard chessboard) {
        this.emptyPieceColor = emptyPieceColor;
        this.rowPosition = rowPosition;
        this.columnPosition = columPosition;

        emptyPiecePanel = new JPanel();
        emptyPiecePanel.setSize(withOfPiece, heightOfPiece);
        emptyPiecePanel.setBackground(emptyPieceColor);
        emptyPiecePanel.setOpaque(true);
        emptyPiecePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPiecePanel.addMouseListener(this);
        this.chessboard = chessboard;
    }

    public void setPieceOnSquare(ChessPiece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
        if (pieceOnSquare != null){
            this.emptyPiecePanel.add(pieceOnSquare.getPieceLabel());
        }
    }

    public void discardPieceFromSquare(){
        setPieceOnSquare(null);
        this.emptyPiecePanel.removeAll();
    }

    public void markTheSquareForMove(){
        emptyPiecePanel.setBackground(Color.gray);
        if (chessboard.getSelectedPieceToMove() != null){
            emptyPiecePanel.setBackground(Color.green);
        }
    }
    public void markTheSquareForAttack(){
        emptyPiecePanel.setBackground(Color.pink);
        if (chessboard.getSelectedPieceToMove() != null){
            emptyPiecePanel.setBackground(Color.red);
        }
    }

    public void clickedForMove(){
        if (emptyPiecePanel.getBackground() == Color.green){
            chessboard.getSelectedPieceToMove().getMove().makeCleanMove(this, chessboard.getSelectedPieceToMove(), chessboard);
        } else if (emptyPiecePanel.getBackground() == Color.red) {
            pieceOnSquare.getMove().makeDiscardMovePiece(this, pieceOnSquare, chessboard.getSelectedPieceToMove(), chessboard);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickedForMove();
        if (pieceOnSquare != null){
            if (chessboard.getSelectedPieceToMove() != null){
                chessboard.setSelectedPieceToMove(null);
                chessboard.setColors();
            }else {
                chessboard.setSelectedPieceToMove(pieceOnSquare);
                pieceOnSquare.showMovePossibilities(chessboard);
            }
        }else {
            chessboard.setSelectedPieceToMove(null);
            chessboard.setColors();
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
        if (chessboard.getSelectedPieceToMove() == null){
            if (pieceOnSquare != null){
                pieceOnSquare.showMovePossibilities(chessboard);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (chessboard.getSelectedPieceToMove() == null){
                chessboard.setColors();
        }
    }

    public ChessPiece getPieceOnSquare() {
        return pieceOnSquare;
    }

    public Color getEmptyPieceColor() {
        return emptyPieceColor;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public JPanel getEmptyPiecePanel() {
        return emptyPiecePanel;
    }
}