import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EmptySquare implements MouseListener {

    private ChessPiece pieceOnSquare;
    private Chessboard chessboard;

    protected Color EmptyPieceColor;
    private int rowPosition;
    private int columnPosition;

    private JPanel emptyPiecePanel;

    public EmptySquare(Color emptyPieceColor, int rowPosition, int columPosition, int withOfPiece, int heightOfPiece, Chessboard chessboard) {
        setEmptyPieceColor(emptyPieceColor);
        setRowPosition(rowPosition);
        setColumnPosition(columPosition);

        emptyPiecePanel = new JPanel();
        emptyPiecePanel.setSize(withOfPiece, heightOfPiece);
        emptyPiecePanel.setBackground(emptyPieceColor);
        emptyPiecePanel.setOpaque(true);
        emptyPiecePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPiecePanel.addMouseListener(this);
        setChessboard(chessboard);
    }

    public ChessPiece getPieceOnSquare() {
        return pieceOnSquare;
    }

    public void setPieceOnSquare(ChessPiece pieceOnSquare) {
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

    public Color getEmptyPieceColor() {
        return EmptyPieceColor;
    }

    public void setEmptyPieceColor(Color emptyPieceColor) {
        EmptyPieceColor = emptyPieceColor;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    public JPanel getEmptyPiecePanel() {
        return emptyPiecePanel;
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