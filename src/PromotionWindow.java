import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromotionWindow implements ActionListener {
    JFrame promotionWindow;
    JButton rookButton;
    JButton knightButton;
    JButton bishopButton;
    JButton queenButton;
    Color promotionColor;

    PromotionWindow(Piece pawnToBePromoted){
        ChessGame.chessGame.setVisible(false);

        promotionWindow = new JFrame();
        rookButton = new JButton("Rook");
        knightButton = new JButton("Knight");
        bishopButton = new JButton("Bishop");
        queenButton = new JButton("Queen");
        promotionColor = pawnToBePromoted.getPieceColor();

        rookButton.addActionListener(this);
        knightButton.addActionListener(this);
        bishopButton.addActionListener(this);
        queenButton.addActionListener(this);

        promotionWindow.add(rookButton);
        promotionWindow.add(knightButton);
        promotionWindow.add(bishopButton);
        promotionWindow.add(queenButton);

        promotionWindow.setLayout(new GridLayout());
        promotionWindow.setSize(800, 200);
        promotionWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        promotionWindow.setLocationRelativeTo(null);
        promotionWindow.setVisible(true);

        System.out.println(promotionColor);
        System.out.println(pawnToBePromoted.pieceColor);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        promotionWindow.dispose();
        ChessGame.chessGame.setVisible(true);
    }
}


