import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    Toolkit tk = Toolkit.getDefaultToolkit();
    private final int widthFrame = ((int) tk.getScreenSize().getWidth());
    private final int heightFrame = ((int) tk.getScreenSize().getHeight());

    private final JPanel chessboard;
    private final EmptySquare[][] arrayBoard = new EmptySquare[8][8];
    private ArrayList<ChessPiece> listOfPieces = new ArrayList<>();
    private ChessPiece selectedPieceToMove;

    Chessboard(){

        JFrame chessGame = new JFrame("Chess");
        chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessGame.setSize(getWidthFrame(), getHeightFrame());
        chessGame.setResizable(false);

        chessboard = new JPanel();
        chessboard.setLayout(new GridLayout(8,8));

        boardInitialization();
        setStartPointOfPieces();
        addEmptyPiecesToFrame();

        chessGame.add(chessboard);
        chessGame.setVisible(true);
    }

    private void boardInitialization(){
        for (int row = 0; row  < arrayBoard.length; row ++){
            for (int column = 0; column < arrayBoard.length; column++){
                if (row%2 == 0) {
                    if (column%2 == 0){
                        arrayBoard[row][column] = new EmptySquare(Color.WHITE, row, column, widthFrame/8, heightFrame/8, this);
                    }else {
                        arrayBoard[row][column] = new EmptySquare(Color.BLACK, row, column, widthFrame/8, heightFrame/8, this);
                    }
                }else {
                    if (column%2 == 0){
                        arrayBoard[row][column] = new EmptySquare(Color.BLACK, row, column, widthFrame/8, heightFrame/8, this);
                    }else {
                        arrayBoard[row][column] = new EmptySquare(Color.WHITE, row, column, widthFrame/8, heightFrame/8, this);
                    }
                }
            }
        }
    }

    private void setStartPointOfPieces(){
        for (ChessPiece piece : ChessPiece.values()){
            listOfPieces.add(piece);
            piece.scaleImageOfPiece(widthFrame/16, heightFrame/16);
            arrayBoard[piece.getRowPosition()][piece.getColumnPosition()].setPieceOnSquare(piece);
        }
    }

    public void addEmptyPiecesToFrame(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int column = 0; column < arrayBoard.length; column++){
                chessboard.add(getArrayBoard()[row][column].getEmptyPiecePanel());
            }
        }
    }

    public  void setColors(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int column = 0; column < getArrayBoard().length; column++){
                arrayBoard[row][column].getEmptyPiecePanel().setBackground(getArrayBoard()[row][column].getEmptyPieceColor());
            }
        }
        chessboard.repaint();
    }

    public int getWidthFrame() {
        return widthFrame;
    }

    public int getHeightFrame() {
        return heightFrame;
    }

    public  EmptySquare[][] getArrayBoard() {
        return arrayBoard;
    }

    public ArrayList<ChessPiece> getListOfPieces() {
        return listOfPieces;
    }

    public ChessPiece getSelectedPieceToMove() {
        return selectedPieceToMove;
    }

    public void setSelectedPieceToMove(ChessPiece selectedPieceToMove) {
        this.selectedPieceToMove = selectedPieceToMove;
    }
}