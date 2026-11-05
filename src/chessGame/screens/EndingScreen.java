package chessGame.screens;

import chessGame.Chessboard;

import javax.swing.*;
import java.awt.*;

public class EndingScreen extends JFrame {

    public EndingScreen(Chessboard chessboard, String endingText){

        setTitle("Game result");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JLabel textLabel = new JLabel(
                endingText,
                SwingConstants.CENTER
        );

        textLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
        textLabel.setBorder(
                BorderFactory.createEmptyBorder(24, 32, 24, 32)
        );

        add(textLabel);
        pack();
        setLocationRelativeTo(chessboard.getChessGame());

        setVisible(true);
    }

}
