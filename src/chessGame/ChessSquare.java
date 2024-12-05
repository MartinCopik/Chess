package chessGame;

import chessPieces.ChessPiece;

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


    @Override
    public void mouseClicked(MouseEvent e) {
        if (chessboard.getSelectedPieceToMove() != null){
            chessboard.getGameManager().isItClickedForMove(this);
            chessboard.setColors();
            chessboard.setSelectedPieceToMove(null);
        }else {
            if (pieceOnSquare != null && chessboard.getGameManager().alternationOfPlayers(pieceOnSquare)){
                chessboard.setSelectedPieceToMove(pieceOnSquare);
            }else chessboard.setSelectedPieceToMove(null);
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
            if (pieceOnSquare != null && chessboard.getGameManager().alternationOfPlayers(pieceOnSquare)){
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