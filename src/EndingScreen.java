import javax.swing.*;
import java.awt.*;

public class EndingScreen extends JFrame {

    JFrame endingScreen;
    JLabel textLabel;

    int width;
    int height;

    public EndingScreen(String endingText){
        ChessGame.chessGame.setVisible(false);

        textLabel = new JLabel(endingText);
        textLabel.setFont(new Font(Font.DIALOG,5,40));
        endingScreen = new JFrame();
        endingScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        endingScreen.setTitle("EndingScreen");
        endingScreen.setSize(ChessGame.width/2, ChessGame.height/2);
        endingScreen.setResizable(false);
        endingScreen.setLocationRelativeTo(null);
        endingScreen.add(textLabel);

        endingScreen.setVisible(true);
    }


}
