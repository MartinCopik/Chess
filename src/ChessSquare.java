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

    public void markTheSquareForMove(){
        //ivo: nie je to vobec tazke zmenit len porozmyslaj aby primarne sa kontroloval pohyb a tuto moznost vyuzivala funkcionalita navrhu tahov, vsetko uz mas spravene aj tak len to prehodit
        emptyPiecePanel.setBackground(Color.gray);
        if (chessboard.getSelectedPieceToMove() != null){
            emptyPiecePanel.setBackground(Color.green);
        }
    }

    public void markTheSquareForAttack(){
        //ivo: tu tiez to iste ako v clickedForMove(), treba si to vyratat a nie pozerat farbu policok. Ked chces pouzivat navrhy pohybu figurky tak to sa musi odvijat od moznosti tahov danej figurky, nie naopak
        emptyPiecePanel.setBackground(Color.pink);
        if (chessboard.getSelectedPieceToMove() != null){
            emptyPiecePanel.setBackground(Color.red);
        }
    }
    public void clickedForMove(){
        // ivo: Color.green toto budes musiet vymysliet inak..ak chces aby ti ukazovalo tahy, tak to musi byt oddelena logika, nie ze ty si pozries ci je zelene/cervene ale nice try :)
        if (emptyPiecePanel.getBackground() == Color.green){
            Move.makeCleanMove(this, chessboard.getSelectedPieceToMove(), chessboard);
//            chessboard.getSelectedPieceToMove().getMove().makeCleanMove(this, chessboard.getSelectedPieceToMove(), chessboard);
        } else if (emptyPiecePanel.getBackground() == Color.red) {
            Move.makeDiscardMovePiece(this, pieceOnSquare, chessboard.getSelectedPieceToMove(), chessboard);
//            pieceOnSquare.getMove().makeDiscardMovePiece(this, pieceOnSquare, chessboard.getSelectedPieceToMove(), chessboard);
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
                pieceOnSquare.chessPieceMovePossibilities(chessboard);
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
                pieceOnSquare.chessPieceMovePossibilities(chessboard);
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