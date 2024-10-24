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

    private Rook blackLeftRook = new Rook(Color.BLACK, new ImageIcon("blackRook.png"), 0, 0, getWidthFrame()/16, getHeightFrame()/16);
    private Rook blackRightRook = new Rook(Color.BLACK, new ImageIcon("blackRook.png"), 0, 7, getWidthFrame()/16, getHeightFrame()/16);;
    private Rook whiteLeftRook = new Rook(Color.WHITE, new ImageIcon("whiteRook.png"), 7, 0, getWidthFrame()/16, getHeightFrame()/16);;
    private Rook whiteRightRook = new Rook(Color.WHITE, new ImageIcon("whiteRook.png"), 7, 7, getWidthFrame()/16, getHeightFrame()/16);;
    private Knight blackLeftKnight = new Knight(Color.BLACK, new ImageIcon("blackKnight.png"), 0, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Knight blackRightKnight = new Knight(Color.BLACK, new ImageIcon("blackKnight.png"), 0, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Knight whiteLeftKnight = new Knight(Color.WHITE, new ImageIcon("whiteKnight.png"), 7, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Knight whiteRightKnight = new Knight(Color.WHITE, new ImageIcon("whiteKnight.png"), 7, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Bishop blackLeftBishop = new Bishop(Color.BLACK, new ImageIcon("blackBishop.png"), 0, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Bishop blackRightBishop = new Bishop(Color.BLACK, new ImageIcon("blackBishop.png"), 0, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Bishop whiteLeftBishop = new Bishop(Color.WHITE, new ImageIcon("whiteBishop.png"), 7, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Bishop whiteRightBishop = new Bishop(Color.WHITE, new ImageIcon("whiteBishop.png"), 7, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Queen blackQueen = new Queen(Color.BLACK, new ImageIcon("blackQueen.png"), 0, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Queen whiteQueen = new Queen(Color.WHITE, new ImageIcon("whiteQueen.png"), 7, 3, getWidthFrame()/16, getHeightFrame()/16);
    private King blackKing = new King(Color.BLACK, new ImageIcon("blackKing.png"), 0, 4, getWidthFrame()/16, getHeightFrame()/16);
    private King whiteKing = new King(Color.WHITE, new ImageIcon("whiteKing.png"), 7, 4, getWidthFrame()/16, getHeightFrame()/16);

    private Pawn blackPawn0 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 0, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn1 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn2 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn3 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn4 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 4, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn5 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn6 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn blackPawn7 = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"), 1, 7, getWidthFrame()/16, getHeightFrame()/16);

    private Pawn whitePawn0 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 0, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn1 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 1, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn2 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 2, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn3 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 3, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn4 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 4, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn5 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 5, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn6 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 6, getWidthFrame()/16, getHeightFrame()/16);
    private Pawn whitePawn7 = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"), 6, 7, getWidthFrame()/16, getHeightFrame()/16);

    public JFrame getChessGame() {
        return chessGame;
    }

    public void setChessGame(JFrame chessGame) {
        this.chessGame = chessGame;
    }

    public int getWidthFrame() {
        return widthFrame;
    }

    public void setWidthFrame(int widthFrame) {
        this.widthFrame = widthFrame;
    }

    public int getHeightFrame() {
        return heightFrame;
    }

    public void setHeightFrame(int heightFrame) {
        this.heightFrame = heightFrame;
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

    public Rook getBlackLeftRook() {
        return blackLeftRook;
    }

    public Rook getBlackRightRook() {
        return blackRightRook;
    }

    public Rook getWhiteLeftRook() {
        return whiteLeftRook;
    }

    public Rook getWhiteRightRook() {
        return whiteRightRook;
    }

    public Knight getBlackLeftKnight() {
        return blackLeftKnight;
    }

    public Knight getBlackRightKnight() {
        return blackRightKnight;
    }

    public Knight getWhiteLeftKnight() {
        return whiteLeftKnight;
    }

    public Knight getWhiteRightKnight() {
        return whiteRightKnight;
    }

    public Bishop getBlackLeftBishop() {
        return blackLeftBishop;
    }

    public Bishop getBlackRightBishop() {
        return blackRightBishop;
    }

    public Bishop getWhiteLeftBishop() {
        return whiteLeftBishop;
    }

    public Bishop getWhiteRightBishop() {
        return whiteRightBishop;
    }

    public Queen getBlackQueen() {
        return blackQueen;
    }

    public Queen getWhiteQueen() {
        return whiteQueen;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public Pawn getBlackPawn0() {
        return blackPawn0;
    }

    public Pawn getBlackPawn1() {
        return blackPawn1;
    }

    public Pawn getBlackPawn2() {
        return blackPawn2;
    }

    public Pawn getBlackPawn3() {
        return blackPawn3;
    }

    public Pawn getBlackPawn4() {
        return blackPawn4;
    }

    public Pawn getBlackPawn5() {
        return blackPawn5;
    }

    public Pawn getBlackPawn6() {
        return blackPawn6;
    }

    public Pawn getBlackPawn7() {
        return blackPawn7;
    }

    public Pawn getWhitePawn0() {
        return whitePawn0;
    }

    public Pawn getWhitePawn1() {
        return whitePawn1;
    }

    public Pawn getWhitePawn2() {
        return whitePawn2;
    }

    public Pawn getWhitePawn3() {
        return whitePawn3;
    }

    public Pawn getWhitePawn4() {
        return whitePawn4;
    }

    public Pawn getWhitePawn5() {
        return whitePawn5;
    }

    public Pawn getWhitePawn6() {
        return whitePawn6;
    }

    public Pawn getWhitePawn7() {
        return whitePawn7;
    }

    private void boardInitialization(){
        for (int row = 0; row  < getArrayBoard().length; row ++){
            for (int colum = 0; colum < getArrayBoard().length; colum++){
                if (row%2 == 0) {
                    getArrayBoard()[row][colum] = new EmptySquare(Color.WHITE, row, colum, getWidthFrame()/8, getHeightFrame()/8, this);
                    getArrayBoard()[row][colum+1] = new EmptySquare(Color.BLACK, row, colum+1, getWidthFrame()/8, getHeightFrame()/8, this);
                    colum++;
                }else {
                    getArrayBoard()[row][colum] = new EmptySquare( Color.BLACK, row, colum, getWidthFrame()/8, getHeightFrame()/8, this);
                    getArrayBoard()[row][colum+1] = new EmptySquare( Color.WHITE, row, colum+1, getWidthFrame()/8, getHeightFrame()/8, this);
                    colum++;
                }
            }
        }
    }
     void setStartPointOfPiece(Piece piece){
        getArrayBoard()[piece.getRowPosition()][piece.getColumPosition()].setPieceOnSquare(piece);
    }

    private void setStartPointOfPieces(){
        for (Piece piece : getListOfPieces()){
            setStartPointOfPiece(piece);
        }
    }
    public void addPiecesToList(){
        getListOfPieces().add(getBlackLeftRook());
        getListOfPieces().add(getBlackRightRook());
        getListOfPieces().add(getWhiteLeftRook());
        getListOfPieces().add(getWhiteRightRook());
        getListOfPieces().add(getBlackLeftKnight());
        getListOfPieces().add(getBlackRightKnight());
        getListOfPieces().add(getWhiteLeftKnight());
        getListOfPieces().add(getWhiteRightKnight());
        getListOfPieces().add(getBlackLeftBishop());
        getListOfPieces().add(getBlackRightBishop());
        getListOfPieces().add(getWhiteLeftBishop());
        getListOfPieces().add(getWhiteRightBishop());
        getListOfPieces().add(getBlackQueen());
        getListOfPieces().add(getWhiteQueen());
        getListOfPieces().add(getBlackKing());
        getListOfPieces().add(getWhiteKing());
        getListOfPieces().add(getBlackPawn0());
        getListOfPieces().add(getBlackPawn1());
        getListOfPieces().add(getBlackPawn2());
        getListOfPieces().add(getBlackPawn3());
        getListOfPieces().add(getBlackPawn4());
        getListOfPieces().add(getBlackPawn5());
        getListOfPieces().add(getBlackPawn6());
        getListOfPieces().add(getBlackPawn7());
        getListOfPieces().add(getWhitePawn0());
        getListOfPieces().add(getWhitePawn1());
        getListOfPieces().add(getWhitePawn2());
        getListOfPieces().add(getWhitePawn3());
        getListOfPieces().add(getWhitePawn4());
        getListOfPieces().add(getWhitePawn5());
        getListOfPieces().add(getWhitePawn6());
        getListOfPieces().add(getWhitePawn7());
    }

    public void addEmptyPiecesToFrame(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int colum = 0; colum < getArrayBoard().length; colum++){
                getChessboard().add(getArrayBoard()[row][colum].getEmptyPiecePanel());
            }
        }
    }

    public  void setColors(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int colum = 0; colum < getArrayBoard().length; colum++){
                getArrayBoard()[row][colum].getEmptyPiecePanel().setBackground(getArrayBoard()[row][colum].getPieceColor());
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
        addPiecesToList();
        setStartPointOfPieces();
        addEmptyPiecesToFrame();

        getChessGame().add(getChessboard());
        getChessGame().setVisible(true);
    }
}