import javax.swing.*;
import java.awt.*;

public class EndingScreen extends JFrame {

    private JFrame endingScreen;
    private JLabel textLabel;

    private int width;
    private int height;

    public EndingScreen(String endingText){
        ChessGame.getChessGame().setVisible(false);
        setWidth(ChessGame.getWidthFrame()/2);
        setHeight(ChessGame.getHeightFrame()/2);

        setTextLabel(new JLabel(endingText));
        getTextLabel().setFont(new Font(Font.DIALOG,5,40));
        setEndingScreen(new JFrame());
        getEndingScreen().setDefaultCloseOperation(EXIT_ON_CLOSE);
        getEndingScreen().setTitle("EndingScreen");
        getEndingScreen().setSize(getWidth(), getHeight());
        getEndingScreen().setResizable(false);
        getEndingScreen().setLocationRelativeTo(null);
        getEndingScreen().add(getTextLabel());

        getEndingScreen().setVisible(true);
    }

    public JFrame getEndingScreen() {
        return endingScreen;
    }

    public void setEndingScreen(JFrame endingScreen) {
        this.endingScreen = endingScreen;
    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel) {
        this.textLabel = textLabel;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
