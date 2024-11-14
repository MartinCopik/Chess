package chessGame;

import chessPieces.*;

import java.awt.*;
import java.util.ArrayList;

public class ChessPiecesPackage {

    private final Rook whiteLeftRook = new Rook(Color.WHITE, "whiteRook.png", 7, 0);
    private final Rook whiteRightRook = new Rook(Color.WHITE, "whiteRook.png", 7, 7);
    private final Rook blackLeftRook = new Rook(Color.BLACK, "blackRook.png", 0, 0);
    private final Rook blackRightRook = new Rook(Color.BLACK, "blackRook.png", 0, 7);
    private final Knight whiteLeftKnight = new Knight(Color.WHITE, "whiteKnight.png", 7, 1);
    private final Knight whiteRightKnight = new Knight(Color.WHITE, "whiteKnight.png", 7, 6);
    private final Knight blackLeftKnight = new Knight(Color.BLACK, "blackKnight.png", 0, 1);
    private final Knight blackRightKnight = new Knight(Color.BLACK, "blackKnight.png", 0, 6);
    private final Bishop whiteLeftBishop = new Bishop(Color.WHITE, "whiteBishop.png", 7, 2);
    private final Bishop whiteRightBishop = new Bishop(Color.WHITE, "whiteBishop.png", 7, 5);
    private final Bishop blackLeftBishop = new Bishop(Color.BLACK, "blackBishop.png", 0, 2);
    private final Bishop blackRightBishop = new Bishop(Color.BLACK, "blackBishop.png", 0, 5);
    private final Queen whiteQueen = new Queen(Color.WHITE, "whiteQueen.png", 7, 3);
    private final Queen blackQueen = new Queen(Color.BLACK, "blackQueen.png", 0, 3);
    private final King whiteKing = new King(Color.WHITE, "whiteQueen.png", 7, 4);
    private final King blackKing = new King(Color.BLACK, "blackQueen.png", 0, 4);

    private final Pawn whitePawn0 = new Pawn(Color.WHITE, "whitePawn.png", 6, 0);
    private final Pawn whitePawn1 = new Pawn(Color.WHITE, "whitePawn.png", 6, 1);
    private final Pawn whitePawn2 = new Pawn(Color.WHITE, "whitePawn.png", 6, 2);
    private final Pawn whitePawn3 = new Pawn(Color.WHITE, "whitePawn.png", 6, 3);
    private final Pawn whitePawn4 = new Pawn(Color.WHITE, "whitePawn.png", 6, 4);
    private final Pawn whitePawn5 = new Pawn(Color.WHITE, "whitePawn.png", 6, 5);
    private final Pawn whitePawn6 = new Pawn(Color.WHITE, "whitePawn.png", 6, 6);
    private final Pawn whitePawn7 = new Pawn(Color.WHITE, "whitePawn.png", 6, 7);

    private final Pawn blackPawn0 = new Pawn(Color.BLACK, "blackPawn.png", 1, 0);
    private final Pawn blackPawn1 = new Pawn(Color.BLACK, "blackPawn.png", 1, 1);
    private final Pawn blackPawn2 = new Pawn(Color.BLACK, "blackPawn.png", 1, 2);
    private final Pawn blackPawn3 = new Pawn(Color.BLACK, "blackPawn.png", 1, 3);
    private final Pawn blackPawn4 = new Pawn(Color.BLACK, "blackPawn.png", 1, 4);
    private final Pawn blackPawn5 = new Pawn(Color.BLACK, "blackPawn.png", 1, 5);
    private final Pawn blackPawn6 = new Pawn(Color.BLACK, "blackPawn.png", 1, 6);
    private final Pawn blackPawn7 = new Pawn(Color.BLACK, "blackPawn.png", 1, 7);

    public ChessPiecesPackage(ArrayList<ChessPiece> listOfPieces){
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
}
