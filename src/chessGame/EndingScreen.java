package chessGame;

import javax.swing.*;
import java.awt.*;

public class EndingScreen extends JFrame {
    JFrame endingScreen;
    JLabel textLabel;

    public EndingScreen(Chessboard chessboard, String endingText){
        chessboard.getChessGame().setVisible(false);

        textLabel = new JLabel(endingText);
        textLabel.setFont(new Font(Font.DIALOG,5,80));
        endingScreen = new JFrame();
        endingScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        endingScreen.setTitle("EndingScreen");
        endingScreen.setSize(chessboard.getChessGame().getWidth()/10, chessboard.getChessGame().getHeight()/8);
        endingScreen.setResizable(false);
        endingScreen.setLocationRelativeTo(null);
        endingScreen.add(textLabel);

        endingScreen.setVisible(true);
    }

}
