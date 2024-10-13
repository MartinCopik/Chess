import javax.swing.*;
import java.awt.*;

public class Chessboard  {

    private static JPanel panelBoard;
    private static Player whitePlayer;
    private static Player blackPlayer;
//    private static int moveCounter;

    private static final Piece[][][] arrayBoard = new Piece[8][8][2];

    public static JPanel getPanelBoard() {
        return panelBoard;
    }

    public static void setPanelBoard(JPanel panelBoard) {
        Chessboard.panelBoard = panelBoard;
    }

    public static Player getWhitePlayer() {
        return whitePlayer;
    }

    public static void setWhitePlayer(Player whitePlayer) {
        Chessboard.whitePlayer = whitePlayer;
    }

    public static Player getBlackPlayer() {
        return blackPlayer;
    }

    public static void setBlackPlayer(Player blackPlayer) {
        Chessboard.blackPlayer = blackPlayer;
    }

//    public static int getMoveCounter() {
//        return moveCounter;
//    }

//    public static void setMoveCounter(int moveCounter) {
//        Chessboard.moveCounter = moveCounter;
//    }

    public static Piece[][][] getArrayBoard() {
        return arrayBoard;
    }

    public static Piece getEmptySquare(int row, int colum){
        return getArrayBoard()[row][colum][0];
    }
    public static Player getAttackingPlayer(Player defendingPlayer){
        if (defendingPlayer == whitePlayer){
            return blackPlayer;
        }else {
            return whitePlayer;
        }
    }

    private void boardInitialization(){
        for (int row = 0; row  < getArrayBoard().length; row ++){
            for (int colum = 0; colum < getArrayBoard().length; colum++){
                if (row%2 == 0) {
                    getArrayBoard()[row][colum][0] = new EmptyPiece( Color.WHITE, row, colum);
                    getArrayBoard()[row][colum+1][0] = new EmptyPiece(Color.BLACK, row, colum+1);
                    colum++;
                }else {
                    getArrayBoard()[row][colum][0] = new EmptyPiece( Color.BLACK, row, colum);
                    getArrayBoard()[row][colum+1][0] = new EmptyPiece( Color.WHITE, row, colum+1);
                    colum++;
                }
            }
        }
    }
    static void setStartPointOfPiece(Piece piece){
        getArrayBoard()[piece.getRowPosition()][piece.getColumPosition()][1] = piece;
        getArrayBoard()[piece.getRowPosition()][piece.getColumPosition()][0].getEmptyPiecePanel().add(piece.getPieceLabel());
    }

    private void setStartPointOfPlayer(Player player){
        setStartPointOfPiece(player.getLeftRook());
        setStartPointOfPiece(player.getRightRook());
        setStartPointOfPiece(player.getLeftKnight());
        setStartPointOfPiece(player.getRightKnigt());
        setStartPointOfPiece(player.getLeftBishop());
        setStartPointOfPiece(player.getRightBishop());
        setStartPointOfPiece(player.getQueen());
        setStartPointOfPiece(player.getKing());

        setStartPointOfPiece(player.getPawn0());
        setStartPointOfPiece(player.getPawn1());
        setStartPointOfPiece(player.getPawn2());
        setStartPointOfPiece(player.getPawn3());
        setStartPointOfPiece(player.getPawn4());
        setStartPointOfPiece(player.getPawn5());
        setStartPointOfPiece(player.getPawn6());
        setStartPointOfPiece(player.getPawn7());

    }

    private void setStartPointOfPlayers(){
        setStartPointOfPlayer(getWhitePlayer());
        setStartPointOfPlayer(getBlackPlayer());
    }

    private void addEmptyPiecesToFrame(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int colum = 0; colum < getArrayBoard().length; colum++){
                    getPanelBoard().add(getArrayBoard()[row][colum][0].getEmptyPiecePanel());
            }
        }
    }
    
    public static void setColors(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int colum = 0; colum < getArrayBoard().length; colum++){
                getArrayBoard()[row][colum][0].getEmptyPiecePanel().setBackground(getArrayBoard()[row][colum][0].getPieceColor());
            }
        }
        getPanelBoard().repaint();
    }

//    public static boolean rightPlayerOnMove(Piece pieceToMakeMove){
//        if (getMoveCounter()%2 == 0 && pieceToMakeMove.getPieceColor().equals(Color.WHITE)){
//            GameManager.checkGameStatus(pieceToMakeMove.getPlayer());
//            return true;
//        } else if (getMoveCounter()%2 == 1 && pieceToMakeMove.getPieceColor().equals(Color.BLACK)){
//            GameManager.checkGameStatus(pieceToMakeMove.getPlayer());
//            return true;
//        }
//        return false;
//    }


    Chessboard(){
        setPanelBoard(new JPanel());
        getPanelBoard().setLayout(new GridLayout(8,8));


        boardInitialization();
        setWhitePlayer(new Player(Color.WHITE));
        setBlackPlayer(new Player(Color.BLACK));
        setStartPointOfPlayers();
        addEmptyPiecesToFrame();
    }
}

