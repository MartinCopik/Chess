import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private String stringPlayerColor;
    private int pawnLineUp;
    private int mainLineUP;
    private Piece pieceAttackingKing;

    private final Rook leftRook;
    private final Rook rightRook;
    private final Knight leftKnight;
    private final Knight rightKnigt;
    private final Bishop leftBishop;
    private final Bishop rightBishop;
    private final Queen queen;
    private final King king;

    private final Pawn pawn0;
    private final Pawn pawn1;
    private final Pawn pawn2;
    private final Pawn pawn3;
    private final Pawn pawn4;
    private final Pawn pawn5;
    private final Pawn pawn6;
    private final Pawn pawn7;

    private HashMap <Piece,Piece> attackedSquares = new HashMap<>();
    private HashMap <Piece,Piece> movePossibilities = new HashMap<>();
    private ArrayList<Piece> playerPieces = new ArrayList<Piece>();

    public void setAttackedSquares(){
        for (Piece piece : this.getPlayerPieces()){
            piece.showMovePossibilities();
        }
    }
    public void arrangementOfAttackedSquares(Piece attackedSquare, Piece attackingFigure){
        getAttackedSquares().put(attackedSquare, attackingFigure);
    }
    public boolean isSquareUnderAttack(int rowToCheck, int columToCheck, Color colorOfAttackingPlayer){
        if (getAttackedSquares().containsKey(Chessboard.getEmptySquare(rowToCheck,columToCheck))
                && getAttackedSquares().get(Chessboard.getEmptySquare(rowToCheck, columToCheck)).getPieceColor().equals(colorOfAttackingPlayer)){

            return true;
        }
        return false;
    }

    public Player(Color playerColor) {
        if (playerColor == Color.BLACK) {
            this.setMainLineUP(0);
            this.setPawnLineUp(1);
            this.setStringPlayerColor("black");
        } else {
            this.setMainLineUP(7);
            this.setPawnLineUp(6);
            this.setStringPlayerColor("white");
        }

        getPlayerPieces().add(leftRook = new Rook(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Rook.png"), getMainLineUP(), 0));
        getPlayerPieces().add(rightRook = new Rook(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Rook.png"), getMainLineUP(), 7));
        getPlayerPieces().add(leftKnight = new Knight(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Knight.png"), getMainLineUP(), 1));
        getPlayerPieces().add(rightKnigt = new Knight(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Knight.png"), getMainLineUP(), 6));
        getPlayerPieces().add(leftBishop = new Bishop(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Bishop.png"), getMainLineUP(), 2));
        getPlayerPieces().add(rightBishop = new Bishop(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Bishop.png"), getMainLineUP(), 5));
        getPlayerPieces().add(queen = new Queen(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Queen.png"), getMainLineUP(), 3));
        getPlayerPieces().add(king = new King(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "King.png"), getMainLineUP(), 4));

        getPlayerPieces().add(pawn0 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 0));
        getPlayerPieces().add(pawn1 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 1));
        getPlayerPieces().add(pawn2 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 2));
        getPlayerPieces().add(pawn3 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 3));
        getPlayerPieces().add(pawn4 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 4));
        getPlayerPieces().add(pawn5 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 5));
        getPlayerPieces().add(pawn6 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 6));
        getPlayerPieces().add(pawn7 = new Pawn(this, playerColor, new ImageIcon(this.getStringPlayerColor() + "Pawn.png"), getPawnLineUp(), 7));
    }

    public void setMovePossibilities(Piece square, Piece piece){
        getMovePossibilities().put(square,piece);
    }
    public HashMap<Piece, Piece> getAttackedSquares() {
        return attackedSquares;
    }

    public String getStringPlayerColor() {
        return stringPlayerColor;
    }

    public Pawn getPawn7() {
        return pawn7;
    }

    public Pawn getPawn6() {
        return pawn6;
    }

    public Pawn getPawn5() {
        return pawn5;
    }

    public Pawn getPawn4() {
        return pawn4;
    }

    public Pawn getPawn3() {
        return pawn3;
    }

    public Pawn getPawn2() {
        return pawn2;
    }

    public Pawn getPawn1() {
        return pawn1;
    }

    public Pawn getPawn0() {
        return pawn0;
    }

    public Rook getLeftRook() {
        return leftRook;
    }

    public Rook getRightRook() {
        return rightRook;
    }

    public Knight getLeftKnight() {
        return leftKnight;
    }

    public Knight getRightKnigt() {
        return rightKnigt;
    }

    public Bishop getLeftBishop() {
        return leftBishop;
    }

    public Bishop getRightBishop() {
        return rightBishop;
    }

    public Queen getQueen() {
        return queen;
    }

    public King getKing() {
        return king;
    }

    public Piece getPieceAttackingKing() {
        return pieceAttackingKing;
    }

    public void setPieceAttackingKing(Piece pieceAttackingKing) {
        this.pieceAttackingKing = pieceAttackingKing;
    }

    public int getPawnLineUp() {
        return pawnLineUp;
    }

    public void setPawnLineUp(int pawnLineUp) {
        this.pawnLineUp = pawnLineUp;
    }

    public int getMainLineUP() {
        return mainLineUP;
    }

    public void setMainLineUP(int mainLineUP) {
        this.mainLineUP = mainLineUP;
    }

    public void setStringPlayerColor(String stringPlayerColor) {
        this.stringPlayerColor = stringPlayerColor;
    }

    public ArrayList<Piece> getPlayerPieces() {
        return playerPieces;
    }

    public HashMap<Piece, Piece> getMovePossibilities() {
        return movePossibilities;
    }
}
