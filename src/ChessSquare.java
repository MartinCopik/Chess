import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessSquare implements MouseListener{

    private ChessPiece pieceOnSquare;
    private final Chessboard chessboard;

    private final Color emptyPieceColor;
    private final int rowPosition;
    private final int columnPosition;
    private final JPanel emptyPiecePanel;

    public ChessSquare(Color emptyPieceColor, int rowPosition, int columPosition, int withOfPiece, int heightOfPiece, Chessboard chessboard) {
        this.emptyPieceColor = emptyPieceColor;
        this.rowPosition = rowPosition;
        this.columnPosition = columPosition;

        this.emptyPiecePanel = new JPanel();
        this.emptyPiecePanel.setSize(withOfPiece, heightOfPiece);
        this.emptyPiecePanel.setBackground(emptyPieceColor);
        this.emptyPiecePanel.setOpaque(true);
        this.emptyPiecePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.emptyPiecePanel.addMouseListener(this);
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

    public void isItClickedForMove(){
        Move.canPieceMakeThisMove(this, chessboard);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (pieceOnSquare != null){ //ivo: v if aj else mas .getSelectedPieceToMove() != null...co s tym vieme spravit?
            if (chessboard.getSelectedPieceToMove() != null){
                isItClickedForMove();
                chessboard.setSelectedPieceToMove(null);
                chessboard.setColors();
            }else {
                chessboard.setSelectedPieceToMove(pieceOnSquare);
                //ivo: nie je toto duplicitne s riadkom 81? teda ked spravim mouseEntered tak uz je jedno ci kliknem alebo nie stale sa zavola showMovePossibilitiesOfPiece()
                pieceOnSquare.showMovePossibilitiesOfPiece();
            }
        }else {
            if (chessboard.getSelectedPieceToMove() != null){
                isItClickedForMove();
            }
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
                pieceOnSquare.showMovePossibilitiesOfPiece();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (chessboard.getSelectedPieceToMove() == null){
                chessboard.setColors();
        }
    }


    public int getRowPosition() {
        return rowPosition;
    }


    public int getColumnPosition() {
        return columnPosition;
    }


    public ChessPiece getPieceOnSquare() {
        return pieceOnSquare;
    }

    public Color getEmptyPieceColor() {
        return emptyPieceColor;
    }

    public JPanel getEmptyPiecePanel() {
        return emptyPiecePanel;
    }
}