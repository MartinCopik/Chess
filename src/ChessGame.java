import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame{
    static JFrame chessGame;

    ChessGame(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) tk.getScreenSize().getWidth();
        int height = (int) tk.getScreenSize().getHeight();
        new Chessboard();

        chessGame = new JFrame();
        chessGame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        chessGame.add(Chessboard.panelBoard);
        chessGame.setTitle("Chess");
        chessGame.setSize(width, height);
        chessGame.setResizable(false);
        chessGame.setVisible(true);
    }
}
