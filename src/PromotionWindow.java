import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromotionWindow  implements ActionListener {
    JFrame promotionWindow;
    JButton rookButton;
    JButton knightButton;
    JButton bishopButton;
    JButton queenButton;
    Piece pieceToBePromoted;

    PromotionWindow(Piece pawnToBePromoted){
        ChessGame.chessGame.setVisible(false);

        promotionWindow = new JFrame();
        rookButton = new JButton("Rook");
        knightButton = new JButton("Knight");
        bishopButton = new JButton("Bishop");
        queenButton = new JButton("Queen");
        pieceToBePromoted = pawnToBePromoted;

        rookButton.addActionListener(this);
        knightButton.addActionListener(this);
        bishopButton.addActionListener(this);
        queenButton.addActionListener(this);

        promotionWindow.add(rookButton);
        promotionWindow.add(knightButton);
        promotionWindow.add(bishopButton);
        promotionWindow.add(queenButton);

        promotionWindow.setLayout(new GridLayout());
        promotionWindow.setSize(ChessGame.width/2, ChessGame.height/2);
        promotionWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        promotionWindow.setLocationRelativeTo(null);
        promotionWindow.setResizable(false);
        promotionWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rookButton) {
            promotePiece(new Rook(pieceToBePromoted.getPieceColor(), new ImageIcon(colorToBePromoted()+"Rook.png"), pieceToBePromoted.getRowPosition(), pieceToBePromoted.getColumPosition()));
        }
        if (e.getSource() == knightButton) {
            promotePiece(new Knight(pieceToBePromoted.getPieceColor(), new ImageIcon(colorToBePromoted()+"Knight.png"), pieceToBePromoted.getRowPosition(), pieceToBePromoted.getColumPosition()));
        }
        if (e.getSource() == bishopButton) {
            promotePiece(new Bishop(pieceToBePromoted.getPieceColor(), new ImageIcon(colorToBePromoted()+"Bishop.png"), pieceToBePromoted.getRowPosition(), pieceToBePromoted.getColumPosition()));
        }
        if (e.getSource() == queenButton) {
            promotePiece(new Queen(pieceToBePromoted.getPieceColor(), new ImageIcon(colorToBePromoted()+"Queen.png"), pieceToBePromoted.getRowPosition(), pieceToBePromoted.getColumPosition()));
        }
        promotionWindow.dispose();
        ChessGame.chessGame.setVisible(true);
    }

    String colorToBePromoted(){
        if (this.pieceToBePromoted.pieceColor == Color.BLACK){
            return "black";
        }else return "white";
    }

    void promotePiece(Piece newPiece){
        Chessboard.setStartPointOfPiece(newPiece);
    }
}


