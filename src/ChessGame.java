import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame{
    private static JFrame chessGame;
    private static int widthFrame;
    private static int heightFrame;

    ChessGame(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        setWidthFrame((int) tk.getScreenSize().getWidth());
        setHeightFrame((int) tk.getScreenSize().getHeight());

        new Chessboard();
        setChessGame(new JFrame());
        getChessGame().setDefaultCloseOperation(EXIT_ON_CLOSE);

        getChessGame().add(Chessboard.getPanelBoard());
        getChessGame().setTitle("Chess");
        getChessGame().setSize(getWidthFrame(), getHeightFrame());
        getChessGame().setResizable(false);
        getChessGame().setVisible(true);
    }

    public static JFrame getChessGame() {
        return chessGame;
    }

    public static void setChessGame(JFrame chessGame) {
        ChessGame.chessGame = chessGame;
    }
    public static int getWidthFrame() {
        return widthFrame;
    }

    public static void setWidthFrame(int widthFrame) {
        ChessGame.widthFrame = widthFrame;
    }

    public static int getHeightFrame() {
        return heightFrame;
    }

    public static void setHeightFrame(int heightFrame) {
        ChessGame.heightFrame = heightFrame;
    }
}
