import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    private final Toolkit tk = Toolkit.getDefaultToolkit();
    private final int widthFrame = ((int) tk.getScreenSize().getWidth());
    private final int heightFrame = ((int) tk.getScreenSize().getHeight());

    private final JPanel chessboard;
    private final ChessSquare[][] arrayBoard = new ChessSquare[8][8];
    private ArrayList<ChessPiece> listOfPieces = new ArrayList<>();
    private ChessPiece selectedPieceToMove;


    //ivo: toto by sme mohli niekde preniest a potom v metode addChessPiecesToList() to budeme davkovat nejakym for cyklom napr. (pouzi google a inspiruj sa :) )
    private Rook whiteLeftRook = new Rook(Color.WHITE, "whiteRook.png", 7, 0);
    private Rook whiteRightRook = new Rook(Color.WHITE, "whiteRook.png", 7, 7);
    private Rook blackLeftRook = new Rook(Color.BLACK, "blackRook.png", 0, 0);
    private Rook blackRightRook = new Rook(Color.BLACK, "blackRook.png", 0, 7);
    private Knight whiteLeftKnight = new Knight(Color.WHITE, "whiteKnight.png", 7, 1);
    private Knight whiteRightKnight = new Knight(Color.WHITE, "whiteKnight.png", 7, 6);
    private Knight blackLeftKnight = new Knight(Color.BLACK, "blackKnight.png", 0, 1);
    private Knight blackRightKnight = new Knight(Color.BLACK, "blackKnight.png", 0, 6);
    private Bishop whiteLeftBishop = new Bishop(Color.WHITE, "whiteBishop.png", 7, 2);
    private Bishop whiteRightBishop = new Bishop(Color.WHITE, "whiteBishop.png", 7, 5);
    private Bishop blackLeftBishop = new Bishop(Color.BLACK, "blackBishop.png", 0, 2);
    private Bishop blackRightBishop = new Bishop(Color.BLACK, "blackBishop.png", 0, 5);
    private Queen whiteQueen = new Queen(Color.WHITE, "whiteQueen.png", 7, 3);
    private Queen blackQueen = new Queen(Color.BLACK, "blackQueen.png", 0, 3);
    private King whiteKing = new King(Color.WHITE, "whiteQueen.png", 7, 4);
    private King blackKing = new King(Color.BLACK, "blackQueen.png", 0, 4);

    private Pawn whitePawn0 = new Pawn(Color.WHITE, "whitePawn.png", 6, 0);
    private Pawn whitePawn1 = new Pawn(Color.WHITE, "whitePawn.png", 6, 1);
    private Pawn whitePawn2 = new Pawn(Color.WHITE, "whitePawn.png", 6, 2);
    private Pawn whitePawn3 = new Pawn(Color.WHITE, "whitePawn.png", 6, 3);
    private Pawn whitePawn4 = new Pawn(Color.WHITE, "whitePawn.png", 6, 4);
    private Pawn whitePawn5 = new Pawn(Color.WHITE, "whitePawn.png", 6, 5);
    private Pawn whitePawn6 = new Pawn(Color.WHITE, "whitePawn.png", 6, 6);
    private Pawn whitePawn7 = new Pawn(Color.WHITE, "whitePawn.png", 6, 7);

    private Pawn blackPawn0 = new Pawn(Color.BLACK, "blackPawn.png", 1, 0);
    private Pawn blackPawn1 = new Pawn(Color.BLACK, "blackPawn.png", 1, 1);
    private Pawn blackPawn2 = new Pawn(Color.BLACK, "blackPawn.png", 1, 2);
    private Pawn blackPawn3 = new Pawn(Color.BLACK, "blackPawn.png", 1, 3);
    private Pawn blackPawn4 = new Pawn(Color.BLACK, "blackPawn.png", 1, 4);
    private Pawn blackPawn5 = new Pawn(Color.BLACK, "blackPawn.png", 1, 5);
    private Pawn blackPawn6 = new Pawn(Color.BLACK, "blackPawn.png", 1, 6);
    private Pawn blackPawn7 = new Pawn(Color.BLACK, "blackPawn.png", 1, 7);


    public Chessboard(){

        JFrame chessGame = new JFrame("Chess");
        chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessGame.setSize(getWidthFrame(), getHeightFrame());
        chessGame.setResizable(false);

        chessboard = new JPanel();
        chessboard.setLayout(new GridLayout(8,8));

        boardInitialization();
        addChessPiecesToList();
        setAllChessPiecesOnBoard();
        addEmptyPiecesToFrame();

        chessGame.add(chessboard);
        chessGame.setVisible(true);
    }

    private void boardInitialization(){
        for (int row = 0; row  < arrayBoard.length; row ++){
            for (int column = 0; column < arrayBoard.length; column++){
                if (row%2 == 0) {
                    if (column%2 == 0){
                        arrayBoard[row][column] = new ChessSquare(Color.WHITE, row, column, widthFrame/8, heightFrame/8, this);
                    }else {
                        arrayBoard[row][column] = new ChessSquare(Color.BLACK, row, column, widthFrame/8, heightFrame/8, this);
                    }
                }else {
                    if (column%2 == 0){
                        arrayBoard[row][column] = new ChessSquare(Color.BLACK, row, column, widthFrame/8, heightFrame/8, this);
                    }else {
                        arrayBoard[row][column] = new ChessSquare(Color.WHITE, row, column, widthFrame/8, heightFrame/8, this);
                    }
                }
            }
        }
    }

    private void addChessPiecesToList(){
        listOfPieces.add(whiteLeftRook);
        listOfPieces.add(whiteRightRook);
        listOfPieces.add(blackLeftRook);
        listOfPieces.add(blackRightRook);
        listOfPieces.add(whiteLeftKnight);
        listOfPieces.add(whiteRightKnight);
        listOfPieces.add(blackLeftKnight);
        listOfPieces.add(blackRightKnight);
        listOfPieces.add(whiteLeftBishop);
        listOfPieces.add(whiteRightBishop);
        listOfPieces.add(blackLeftBishop);
        listOfPieces.add(blackRightBishop);
        listOfPieces.add(whiteQueen);
        listOfPieces.add(blackQueen);
        listOfPieces.add(whiteKing);
        listOfPieces.add(blackKing);

        listOfPieces.add(whitePawn0);
        listOfPieces.add(whitePawn1);
        listOfPieces.add(whitePawn2);
        listOfPieces.add(whitePawn3);
        listOfPieces.add(whitePawn4);
        listOfPieces.add(whitePawn5);
        listOfPieces.add(whitePawn6);
        listOfPieces.add(whitePawn7);

        listOfPieces.add(blackPawn0);
        listOfPieces.add(blackPawn1);
        listOfPieces.add(blackPawn2);
        listOfPieces.add(blackPawn3);
        listOfPieces.add(blackPawn4);
        listOfPieces.add(blackPawn5);
        listOfPieces.add(blackPawn6);
        listOfPieces.add(blackPawn7);
    }

    private void setAllChessPiecesOnBoard(){
        listOfPieces.forEach(chessPiece -> chessPiece.scaleImageOfPiece(widthFrame/16, heightFrame/16));
        listOfPieces.forEach(chessPiece -> arrayBoard[chessPiece.getRowPosition()][chessPiece.getColumnPosition()].setPieceOnSquare(chessPiece));
        setAllChessPiecesMoveMap();
    }
    public void setAllChessPiecesMoveMap(){
        for (ChessPiece chessPiece : listOfPieces) {
            chessPiece.getChessPieceMovesMap().clear();
            chessPiece.setChessPieceMovesMap(this);
        }
    }

    private void addEmptyPiecesToFrame(){
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

    public  ChessSquare[][] getArrayBoard() {
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