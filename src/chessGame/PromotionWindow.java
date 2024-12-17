package chessGame;

import chessPieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class handles the promotion process of the Pawn
 */
public class PromotionWindow implements ActionListener {

    private JFrame promotionWindow;
    private JButton rookButton;
    private JButton knightButton;
    private JButton bishopButton;
    private JButton queenButton;
    private ChessPiece pawnToBePromoted;
    private Chessboard chessboard;

    public PromotionWindow(ChessPiece pawnToBePromoted, Chessboard chessboard){
        this.pawnToBePromoted = pawnToBePromoted;
        this.chessboard = chessboard;
        this.chessboard.getChessGame().setVisible(false);

        promotionWindow = new JFrame();
        rookButton = new JButton(new ImageIcon(colorToBePromoted()+"Rook.png"));
        knightButton = new JButton(new ImageIcon(colorToBePromoted()+"Knight.png"));
        bishopButton = new JButton(new ImageIcon(colorToBePromoted()+"Bishop.png"));
        queenButton = new JButton(new ImageIcon(colorToBePromoted()+"Queen.png"));

        rookButton.setFocusable(false);
        knightButton.setFocusable(false);
        bishopButton.setFocusable(false);
        queenButton.setFocusable(false);

        rookButton.addActionListener(this);
        knightButton.addActionListener(this);
        bishopButton.addActionListener(this);
        queenButton.addActionListener(this);

        promotionWindow.add(rookButton);
        promotionWindow.add(knightButton);
        promotionWindow.add(bishopButton);
        promotionWindow.add(queenButton);

        promotionWindow.setLayout(new GridLayout());
        promotionWindow.setSize(queenButton.getIcon().getIconWidth()*5, queenButton.getIcon().getIconHeight());
        promotionWindow.setLocationRelativeTo(null);
        promotionWindow.setUndecorated(true);
        promotionWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rookButton) {
            promotePiece(new Rook(pawnToBePromoted.getChessPieceColor(), colorToBePromoted()+"Rook.png", pawnToBePromoted.getRowPosition(), pawnToBePromoted.getColumnPosition()));
        } else if (e.getSource() == knightButton) {
            promotePiece(new Knight(pawnToBePromoted.getChessPieceColor(), colorToBePromoted()+"Knight.png", pawnToBePromoted.getRowPosition(), pawnToBePromoted.getColumnPosition()));
        } else if (e.getSource() == bishopButton) {
            promotePiece(new Bishop(pawnToBePromoted.getChessPieceColor(), colorToBePromoted()+"Bishop.png", pawnToBePromoted.getRowPosition(), pawnToBePromoted.getColumnPosition()));
        } else if (e.getSource() == queenButton) {
            promotePiece(new Queen(pawnToBePromoted.getChessPieceColor(), colorToBePromoted()+"Queen.png", pawnToBePromoted.getRowPosition(), pawnToBePromoted.getColumnPosition()));
        }
        
        promotionWindow.dispose();
        chessboard.getChessGame().setVisible(true);
    }

    /**
     * checks the promoting color
     * @return the promoting color
     */
    private String colorToBePromoted(){
        if (this.pawnToBePromoted.getChessPieceColor() == Color.BLACK){
            return "black";
        }else return "white";
    }

    /**
     * promote the pawn, set up the new chessPiece and discards the pawn from the game
     * @param newPiece
     */
    private void promotePiece(ChessPiece newPiece){
        newPiece.scaleImageOfPiece(pawnToBePromoted.getPieceLabel().getWidth(), pawnToBePromoted.getPieceLabel().getHeight());
        chessboard.getListOfPieces().add(newPiece);
        ChessPieceMovement.makeTheMove(chessboard.getArrayBoard()[pawnToBePromoted.getRowPosition()][pawnToBePromoted.getColumnPosition()], newPiece, chessboard);
    }
}
