import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    public static JPanel panelBoard;
    static JPanel discardedPieces;

    private static Player whitePlayer;
    private static Player blackPlayer;

    public static Piece[][][] arrayBoard = new Piece[8][8][2];
    public static ArrayList<Piece> arrayDiscardedPieces = new ArrayList<>();

    public static Player getWhitePlayer() {
        return whitePlayer;
    }

    public static Player getBlackPlayer() {
        return blackPlayer;
    }

    public static Piece[][][] getArrayBoard() {
        return arrayBoard;
    }

    public static Piece getEmptySquare(int row, int colum){
        return arrayBoard[row][colum][0];
    }
    public static Player getAttackingPlayer(Player defendingPlayer){
        if (defendingPlayer == whitePlayer){
            return blackPlayer;
        }else {
            return whitePlayer;
        }
    }

    private void boardInitialization(){
        for (int row = 0; row  < arrayBoard.length; row ++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                if (row%2 == 0) {
                    arrayBoard[row][colum][0] = new EmptyPiece( Color.WHITE, row, colum);
                    arrayBoard[row][colum+1][0] = new EmptyPiece(Color.BLACK, row, colum+1);
                    colum++;
                }else {
                    arrayBoard[row][colum][0] = new EmptyPiece( Color.BLACK, row, colum);
                    arrayBoard[row][colum+1][0] = new EmptyPiece( Color.WHITE, row, colum+1);
                    colum++;
                }
            }
        }
    }
    static void setStartPointOfPiece(Piece piece){
        arrayBoard[piece.getRowPosition()][piece.getColumPosition()][1] = piece;
        arrayBoard[piece.getRowPosition()][piece.getColumPosition()][0].getEmptyPiecePanel().add(piece.getPieceLabel());
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
        setStartPointOfPlayer(whitePlayer);
        setStartPointOfPlayer(blackPlayer);
    }

    private void addEmptyPiecesToFrame(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                    panelBoard.add(arrayBoard[row][colum][0].getEmptyPiecePanel());
            }
        }
    }
    
    public static void setColors(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                arrayBoard[row][colum][0].getEmptyPiecePanel().setBackground(arrayBoard[row][colum][0].pieceColor);
            }
        }
        panelBoard.repaint();
    }


    Chessboard(){
        panelBoard = new JPanel();
        discardedPieces = new JPanel();

        panelBoard.setLayout(new GridLayout(8,8));
        discardedPieces.setLayout(new GridLayout());

        boardInitialization();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        setStartPointOfPlayers();
        addEmptyPiecesToFrame();
    }
}

