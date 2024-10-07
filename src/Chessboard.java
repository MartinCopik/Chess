import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    public static JPanel panelBoard;
    static JPanel discardedPieces;

    static Player whitePlayer;
    static Player blackPlayer;

    public static Piece[][][] arrayBoard = new Piece[8][8][2];
    public static ArrayList<Piece> arrayDiscardedPieces = new ArrayList<>();


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
        arrayBoard[piece.rowPosition][piece.columPosition][1] = piece;
        arrayBoard[piece.rowPosition][piece.columPosition][0].emptyPiecePanel.add(piece.pieceLabel);
    }

    private void setStartPointOfPlayer(Player player){
        setStartPointOfPiece(player.leftRook);
        setStartPointOfPiece(player.rightRook);
//        setStartPointOfPiece(player.leftKnight);
//        setStartPointOfPiece(player.rightKnigt);
//        setStartPointOfPiece(player.leftBishop);
//        setStartPointOfPiece(player.rightBishop);
        setStartPointOfPiece(player.queen);
        setStartPointOfPiece(player.king);

//        setStartPointOfPiece(player.pawn0);
//        setStartPointOfPiece(player.pawn1);
//        setStartPointOfPiece(player.pawn2);
//        setStartPointOfPiece(player.pawn3);
//        setStartPointOfPiece(player.pawn4);
//        setStartPointOfPiece(player.pawn5);
//        setStartPointOfPiece(player.pawn6);
//        setStartPointOfPiece(player.pawn7);

//        setStartPointOfPiece(player.specialQueen);
//        setStartPointOfPiece(player.specialQueen2);
    }

    private void setStartPointOfPlayers(){
        setStartPointOfPlayer(whitePlayer);
        setStartPointOfPlayer(blackPlayer);
    }

    private void addEmptyPiecesToFrame(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                    panelBoard.add(arrayBoard[row][colum][0].emptyPiecePanel);
            }
        }
    }
    
    public static void setColors(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                arrayBoard[row][colum][0].emptyPiecePanel.setBackground(arrayBoard[row][colum][0].pieceColor);
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

