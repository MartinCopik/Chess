import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame{
    static JFrame chessGame;
    static int width;
    static int height;

    ChessGame(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        width = (int) tk.getScreenSize().getWidth();
        height = (int) tk.getScreenSize().getHeight();

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
