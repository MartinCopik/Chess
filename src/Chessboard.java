import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    Toolkit tk = Toolkit.getDefaultToolkit();
    private final int widthFrame = ((int) tk.getScreenSize().getWidth());
    private final int heightFrame = ((int) tk.getScreenSize().getHeight());

    private final JPanel chessboard;
    private final ISquare[][] arrayBoard = new EmptySquare[8][8];
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

    //ivo: rozdelime logiku..najskor spravime vsetky policka EmptySquare, vysereme sa na figurky zatial
    // aby som ti troska pomohol tak si vieme spravit
    //    public interface Square {
    //       int getRow();
    //      int getCol();
    //}
    // a to bude miesto EmptySquare teda private final Square[][] board;
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

    //ivo: metoda ktora nam nastavi zaciatocne figurky na ich zakladne pozicie napr. placePiece(new BishopMoves(0, 2), 0, 2);

    //ivo: tu vieme spravit metodu napr. placePiece() ktora uz definuje figurku napr. ChessPiece s velkou zmenou (najdes v ChessPiece).
    //{
    //  }


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

    public  ISquare[][] getArrayBoard() {
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