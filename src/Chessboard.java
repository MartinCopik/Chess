import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {
    private  JFrame chessGame;
    Toolkit tk = Toolkit.getDefaultToolkit();
    private  int widthFrame = ((int) tk.getScreenSize().getWidth());
    private  int heightFrame = ((int) tk.getScreenSize().getHeight());

    private   JPanel chessboard;
    private   EmptySquare[][] arrayBoard = new EmptySquare[8][8];
    private ArrayList<ChessPiece> listOfPieces = new ArrayList<>();
    private ChessPiece selectedPieceToMove;

    //ivo: z tohto by som spravil normalne enum, metody k tomu..ak nevies pozri ako sa to robi hint: ChessPiece(Color color, String iconPath, int row, int column){}
    private ChessPiece blackLeftRook = ChessPiece.ROOK_BLACK_LEFT;
    private ChessPiece blackRightRook = ChessPiece.ROOK_BLACK_RIGHT;
    private ChessPiece whiteLeftRook = ChessPiece.ROOK_WHITE_LEFT;
    private ChessPiece whiteRightRook = ChessPiece.ROOK_WHITE_RIGHT;
    private ChessPiece blackLeftKnight = ChessPiece.KNIGHT_BLACK_LEFT;
    private ChessPiece blackRightKnight = ChessPiece.KNIGHT_BLACK_RIGHT;
    private ChessPiece whiteLeftKnight = ChessPiece.KNIGHT_WHITE_LEFT;
    private ChessPiece whiteRightKnight = ChessPiece.KNIGHT_WHITE_LEFT;
//    private ChessPiece whiteRightKnight = ChessPiece.KNIGHT_WHITE_RIGHT;
    private ChessPiece blackLeftBishop = ChessPiece.BISHOP_BLACK_LEFT;
    private ChessPiece blackRightBishop = ChessPiece.BISHOP_BLACK_RIGHT;
    private ChessPiece whiteLeftBishop = ChessPiece.BISHOP_WHITE_LEFT;
    private ChessPiece whiteRightBishop = ChessPiece.BISHOP_WHITE_RIGHT;
    private ChessPiece blackQueen = ChessPiece.QUEEN_BLACK;
    private ChessPiece whiteQueen = ChessPiece.QUEEN_WHITE;
    private ChessPiece blackKing = ChessPiece.KING_BLACK;
    private ChessPiece whiteKing = ChessPiece.KING_WHITE;

    private ChessPiece blackPawn0 = ChessPiece.PAWN_BLACK_0;
    private ChessPiece blackPawn1 = ChessPiece.PAWN_BLACK_1;
    private ChessPiece blackPawn2 = ChessPiece.PAWN_BLACK_2;
    private ChessPiece blackPawn3 = ChessPiece.PAWN_BLACK_3;
    private ChessPiece blackPawn4 = ChessPiece.PAWN_BLACK_4;
    private ChessPiece blackPawn5 = ChessPiece.PAWN_BLACK_5;
    private ChessPiece blackPawn6 = ChessPiece.PAWN_BLACK_6;
    private ChessPiece blackPawn7 = ChessPiece.PAWN_BLACK_7;

    private ChessPiece whitePawn0 = ChessPiece.PAWN_WHITE_0;
    private ChessPiece whitePawn1 = ChessPiece.PAWN_WHITE_1;
    private ChessPiece whitePawn2 = ChessPiece.PAWN_WHITE_2;
    private ChessPiece whitePawn3 = ChessPiece.PAWN_WHITE_3;
    private ChessPiece whitePawn4 = ChessPiece.PAWN_WHITE_4;
    private ChessPiece whitePawn5 = ChessPiece.PAWN_WHITE_5;
    private ChessPiece whitePawn6 = ChessPiece.PAWN_WHITE_6;
    private ChessPiece whitePawn7 = ChessPiece.PAWN_WHITE_7;
    //ivo: my vieme spravit ale aj toto  "private Piece whitePawn7 = new Pawn", takto sa vlastne zabezpecuje dedicnost, teda vsetky figurky by mali byt Piece, porozmyslaj preco to tak moze byt a potom ako sa volaju tie overridnute metody

    public JFrame getChessGame() {
        return chessGame;
    }

    public void setChessGame(JFrame chessGame) {
        this.chessGame = chessGame;
    }

    public int getWidthFrame() {
        return widthFrame;
    }

    public int getHeightFrame() {
        return heightFrame;
    }

    public JPanel getChessboard() {
        return chessboard;
    }

    public void setChessboard(JPanel chessboard) {
        this.chessboard = chessboard;
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
                getChessboard().add(getArrayBoard()[row][column].getEmptyPiecePanel());
            }
        }
    }

    public  void setColors(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int column = 0; column < getArrayBoard().length; column++){
                getArrayBoard()[row][column].getEmptyPiecePanel().setBackground(getArrayBoard()[row][column].getEmptyPieceColor());
            }
        }
        getChessboard().repaint();
    }


    Chessboard(){


        setChessGame(new JFrame("Chess"));
        getChessGame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getChessGame().setSize(getWidthFrame(), getHeightFrame());
        getChessGame().setResizable(false);

        setChessboard(new JPanel());
        getChessboard().setLayout(new GridLayout(8,8));


        boardInitialization();
        setStartPointOfPieces();
        addEmptyPiecesToFrame();

        getChessGame().add(getChessboard());
        getChessGame().setVisible(true);
    }
}