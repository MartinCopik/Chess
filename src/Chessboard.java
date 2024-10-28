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
    private ArrayList<Piece> listOfPieces = new ArrayList<>();
    private Piece selectedPieceToMove;

    //ivo: z tohto by som spravil normalne enum, metody k tomu..ak nevies pozri ako sa to robi hint: ChessPiece(Color color, String iconPath, int row, int column){}
    private Piece blackLeftRook = new Rook(Color.BLACK, new ImageIcon("blackRook.png"), 0, 0, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackRightRook = new Rook(Color.BLACK, new ImageIcon("blackRook.png"), 0, 7, getWidthFrame()/16, getHeightFrame()/16);;
    private Piece whiteLeftRook = new Rook(Color.WHITE, new ImageIcon("whiteRook.png"), 7, 0, getWidthFrame()/16, getHeightFrame()/16);;
    private Piece whiteRightRook = new Rook(Color.WHITE, new ImageIcon("whiteRook.png"), 7, 7, getWidthFrame()/16, getHeightFrame()/16);;
    private Piece blackLeftKnight = new Knight(Color.BLACK, new ImageIcon("blackKnight.png"), 0, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackRightKnight = new Knight(Color.BLACK, new ImageIcon("blackKnight.png"), 0, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whiteLeftKnight = new Knight(Color.WHITE, new ImageIcon("whiteKnight.png"), 7, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whiteRightKnight = new Knight(Color.WHITE, new ImageIcon("whiteKnight.png"), 7, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackLeftBishop = new Bishop(Color.BLACK, new ImageIcon("blackBishop.png"), 0, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackRightBishop = new Bishop(Color.BLACK, new ImageIcon("blackBishop.png"), 0, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whiteLeftBishop = new Bishop(Color.WHITE, new ImageIcon("whiteBishop.png"), 7, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whiteRightBishop = new Bishop(Color.WHITE, new ImageIcon("whiteBishop.png"), 7, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackQueen = new Queen(Color.BLACK, new ImageIcon("blackQueen.png"), 0, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whiteQueen = new Queen(Color.WHITE, new ImageIcon("whiteQueen.png"), 7, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackKing = new King(Color.BLACK, new ImageIcon("blackKing.png"), 0, 4, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whiteKing = new King(Color.WHITE, new ImageIcon("whiteKing.png"), 7, 4, getWidthFrame()/16, getHeightFrame()/16);

    private Piece blackPawn0 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 0, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn1 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn2 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn3 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn4 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 4, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn5 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn6 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Piece blackPawn7 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 7, getWidthFrame()/16, getHeightFrame()/16);
            
    private Piece whitePawn0 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 0, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn1 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn2 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn3 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn4 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 4, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn5 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn6 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Piece whitePawn7 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 7, getWidthFrame()/16, getHeightFrame()/16);
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

    public ArrayList<Piece> getListOfPieces() {
        return listOfPieces;
    }

    public Piece getSelectedPieceToMove() {
        return selectedPieceToMove;
    }

    public void setSelectedPieceToMove(Piece selectedPieceToMove) {
        this.selectedPieceToMove = selectedPieceToMove;
    }

    private void boardInitialization(){
        for (int row = 0; row  < getArrayBoard().length; row ++){
            for (int column = 0; column < getArrayBoard().length; column++){
                if (row%2 == 0) {
                    if (column%2 == 0){
                        getArrayBoard()[row][column] = new EmptySquare(Color.WHITE, row, column, getWidthFrame()/8, getHeightFrame()/8, this);
                    }else {
                        getArrayBoard()[row][column] = new EmptySquare(Color.BLACK, row, column, getWidthFrame()/8, getHeightFrame()/8, this);
                    }
                }else {
                    if (column%2 == 0){
                        getArrayBoard()[row][column] = new EmptySquare(Color.BLACK, row, column, getWidthFrame()/8, getHeightFrame()/8, this);
                    }else {
                        getArrayBoard()[row][column] = new EmptySquare(Color.WHITE, row, column, getWidthFrame()/8, getHeightFrame()/8, this);
                    }
                }
            }
        }
    }
     void setStartPointOfPiece(Piece piece){
        getArrayBoard()[piece.getRowPosition()][piece.getColumnPosition()].setPieceOnSquare(piece);
    }

    //ivo: tu sem sa asi troska nepochopili ale nevadi..nemusis volat getter na premennu ktora je priamo v classe [249] teda takto to bude vyzerat -> for (Piece piece : listOfPieces){
    private void setStartPointOfPieces(){
        for (Piece piece : listOfPieces){
            setStartPointOfPiece(piece);
        }
    }

    //ivo: tu je to podobne ako [247] + velmi jednoducho ked spravis enum vies pridat vsetky figurky potom do ArrayList
    public void addPiecesToList(){
        listOfPieces.add(blackLeftRook);
        listOfPieces.add(blackRightRook);
        listOfPieces.add(whiteLeftRook);
        listOfPieces.add(whiteRightRook);
        listOfPieces.add(blackLeftKnight);
        listOfPieces.add(blackRightKnight);
        listOfPieces.add(whiteLeftKnight);
        listOfPieces.add(whiteRightKnight);
        listOfPieces.add(blackLeftBishop);
        listOfPieces.add(blackRightBishop);
        listOfPieces.add(whiteLeftBishop);
        listOfPieces.add(whiteRightBishop);
        listOfPieces.add(blackQueen);
        listOfPieces.add(whiteQueen);
        listOfPieces.add(blackKing);
        listOfPieces.add(whiteKing);
        listOfPieces.add(blackPawn0);
        listOfPieces.add(blackPawn1);
        listOfPieces.add(blackPawn2);
        listOfPieces.add(blackPawn3);
        listOfPieces.add(blackPawn4);
        listOfPieces.add(blackPawn5);
        listOfPieces.add(blackPawn6);
        listOfPieces.add(blackPawn7);
        listOfPieces.add(whitePawn0);
        listOfPieces.add(whitePawn1);
        listOfPieces.add(whitePawn2);
        listOfPieces.add(whitePawn3);
        listOfPieces.add(whitePawn4);
        listOfPieces.add(whitePawn5);
        listOfPieces.add(whitePawn6);
        listOfPieces.add(whitePawn7);
    }

    public void addEmptyPiecesToFrame(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int column = 0; column < getArrayBoard().length; column++){
                getChessboard().add(getArrayBoard()[row][column].getEmptyPiecePanel());
            }
        }
    }

    public  void setColors(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int column = 0; column < getArrayBoard().length; column++){
                getArrayBoard()[row][column].getEmptyPiecePanel().setBackground(getArrayBoard()[row][column].getPieceColor());
            }
        }
        getChessboard().repaint();
    }


    Chessboard(){

        ChessPiece rook = ChessPiece.ROOK;
        ChessPiece knight = ChessPiece.KNIGHT;
        rook.printMove();
        knight.printMove();



        setChessGame(new JFrame("Chess"));
        getChessGame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getChessGame().setSize(getWidthFrame(), getHeightFrame());
        getChessGame().setResizable(false);

        setChessboard(new JPanel());
        getChessboard().setLayout(new GridLayout(8,8));


        boardInitialization();
        addPiecesToList();
        setStartPointOfPieces();
        addEmptyPiecesToFrame();

        getChessGame().add(getChessboard());
        getChessGame().setVisible(true);
    }
}