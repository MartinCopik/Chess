package chessGame;

import chessPieces.*;

import java.awt.*;
import java.util.ArrayList;

public class ChessPiecesPackage {

    private static Rook leftWhiteRook;
    private static Rook rightWhiteRook;
    private static Rook leftBlackRook;
    private static Rook rightBlackRook;

    private static King whiteKing;
    private static King blackKing;



    public static void initializePieces(ArrayList<ChessPiece> listOfPieces){
        initializePawns(listOfPieces);

        initializePiece(listOfPieces, leftWhiteRook = new Rook(Color.WHITE, "whiteRook.png", 7, 0));
        initializePiece(listOfPieces, rightWhiteRook = new Rook(Color.WHITE, "whiteRook.png", 7, 7));
        initializePiece(listOfPieces, leftBlackRook = new Rook(Color.BLACK, "blackRook.png", 0, 0));
        initializePiece(listOfPieces, rightBlackRook = new Rook(Color.BLACK, "blackRook.png", 0, 7));

        initializePiece(listOfPieces, new Knight(Color.WHITE, "whiteKnight.png", 7, 1));
        initializePiece(listOfPieces, new Knight(Color.WHITE, "whiteKnight.png", 7, 6));
        initializePiece(listOfPieces, new Knight(Color.BLACK, "blackKnight.png", 0, 1));
        initializePiece(listOfPieces, new Knight(Color.BLACK, "blackKnight.png", 0, 6));

        initializePiece(listOfPieces, new Bishop(Color.WHITE, "whiteBishop.png", 7, 2));
        initializePiece(listOfPieces, new Bishop(Color.WHITE, "whiteBishop.png", 7, 5));
        initializePiece(listOfPieces, new Bishop(Color.BLACK, "blackBishop.png", 0, 2));
        initializePiece(listOfPieces, new Bishop(Color.BLACK, "blackBishop.png", 0, 5));

        initializePiece(listOfPieces, whiteKing = new King(Color.WHITE, "whiteKing.png", 7, 4, rightWhiteRook, leftWhiteRook));
        initializePiece(listOfPieces, blackKing = new King(Color.BLACK, "blackKing.png", 0, 4, rightBlackRook, leftBlackRook));

        initializePiece(listOfPieces,new Queen(Color.WHITE, "whiteQueen.png", 7, 3));
        initializePiece(listOfPieces,new Queen(Color.BLACK, "BlackQueen.png", 0, 3));
    }

    private static void initializePawns(ArrayList<ChessPiece> listOfPieces){
        for (int i = 0; i < 8; i++) {
            initializePiece(listOfPieces, new Pawn(Color.WHITE, "whitePawn.png", 6, i));
            initializePiece(listOfPieces, new Pawn(Color.BLACK, "blackPawn.png", 1, i));
        }
    }

    private static void initializePiece(ArrayList<ChessPiece> list, ChessPiece chessPiece){
        list.add(chessPiece);
    }

    public static King getWhiteKing() {
        return whiteKing;
    }

    public static King getBlackKing() {
        return blackKing;
    }
}
