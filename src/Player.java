import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    boolean clicked = false;
    String stringPlayerColor;
    int pawnLineUp;
    int mainLineUP;
    Piece pieceAttackingKing;

    Rook leftRook;
    Rook rightRook;
    Knight leftKnight;
    Knight rightKnigt;
    Bishop leftBishop;
    Bishop rightBishop;
    Queen queen;
    King king;

    Pawn pawn0;
    Pawn pawn1;
    Pawn pawn2;
    Pawn pawn3;
    Pawn pawn4;
    Pawn pawn5;
    Pawn pawn6;
    Pawn pawn7;

    HashMap <Piece,Piece> movePossibilities = new HashMap<>();

    Queen specialQueen;
    Queen specialQueen2;

    ArrayList<Piece> playerPieces = new ArrayList<Piece>();

    Player(Color playerColor){
        if (playerColor == Color.BLACK){
            this.mainLineUP = 0;
            this.pawnLineUp = 1;
            this.stringPlayerColor = "black";
        }else {
            this.mainLineUP = 7;
            this.pawnLineUp = 6;
            this.stringPlayerColor = "white";
        }

        playerPieces.add(leftRook = new Rook(this, playerColor, new ImageIcon(this.stringPlayerColor + "Rook.png"), mainLineUP, 0));
        playerPieces.add(rightRook = new Rook(this, playerColor, new ImageIcon(this.stringPlayerColor + "Rook.png"), mainLineUP, 7));
//        playerPieces.add(leftKnight = new Knight(this, playerColor, new ImageIcon(this.stringPlayerColor + "Knight.png"), mainLineUP, 1));
//        playerPieces.add(rightKnigt = new Knight(this, playerColor, new ImageIcon(this.stringPlayerColor + "Knight.png"), mainLineUP, 6));
//        playerPieces.add(leftBishop = new Bishop(this, playerColor, new ImageIcon(this.stringPlayerColor + "Bishop.png"), mainLineUP, 2));
//        playerPieces.add(rightBishop = new Bishop(this, playerColor, new ImageIcon(this.stringPlayerColor + "Bishop.png"), mainLineUP, 5));
        playerPieces.add(queen = new Queen(this, playerColor, new ImageIcon(this.stringPlayerColor + "Queen.png"), mainLineUP, 3));
        playerPieces.add(king = new King(this, playerColor, new ImageIcon(this.stringPlayerColor + "King.png"), mainLineUP, 4));

//        playerPieces.add(pawn0 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 0));
//        playerPieces.add(pawn1 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 1));
//        playerPieces.add(pawn2 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 2));
//        playerPieces.add(pawn3 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 3));
//        playerPieces.add(pawn4 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 4));
//        playerPieces.add(pawn5 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 5));
//        playerPieces.add(pawn6 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 6));
//        playerPieces.add(pawn7 = new Pawn(this, playerColor, new ImageIcon(this.stringPlayerColor + "Pawn.png"), pawnLineUp, 7));

        if (playerColor == Color.BLACK){
//            playerPieces.add(specialQueen = new Queen(this, playerColor, new ImageIcon(this.stringPlayerColor + "Queen.png"), mainLineUP+3, 3));
        }else {
//            playerPieces.add(specialQueen = new Queen(this, playerColor, new ImageIcon(this.stringPlayerColor + "Queen.png"), mainLineUP-4, 5));

        }
//        if (playerColor == Color.BLACK){
//            playerPieces.add(specialQueen2 = new Queen(playerColor, new ImageIcon(this.stringPlayerColor + "Queen.png"), mainLineUP+1, 3));
//        }else {
//            playerPieces.add(specialQueen2 = new Queen(playerColor, new ImageIcon(this.stringPlayerColor + "Queen.png"), mainLineUP-1, 3));
//
//        }
    }
    void setMovePossibilities(Piece square, Piece piece){
        movePossibilities.put(square,piece);
    }

}
