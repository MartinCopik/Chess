package chessGame;

import chessPieces.*;

import java.util.ArrayList;

public class ChessPiecesPackage {

    private King whiteKing;
    private King blackKing;



    public ChessPiecesPackage(ArrayList<ChessPiece> listOfPieces){
        initializePawns(listOfPieces);

        initializePiece(listOfPieces, new Rook(PieceColor.WHITE, "whiteRook.png", 7, 0));
        initializePiece(listOfPieces, new Rook(PieceColor.WHITE, "whiteRook.png", 7, 7));
        initializePiece(listOfPieces, new Rook(PieceColor.BLACK, "blackRook.png", 0, 0));
        initializePiece(listOfPieces, new Rook(PieceColor.BLACK, "blackRook.png", 0, 7));

        initializePiece(listOfPieces, new Knight(PieceColor.WHITE, "whiteKnight.png", 7, 1));
        initializePiece(listOfPieces, new Knight(PieceColor.WHITE, "whiteKnight.png", 7, 6));
        initializePiece(listOfPieces, new Knight(PieceColor.BLACK, "blackKnight.png", 0, 1));
        initializePiece(listOfPieces, new Knight(PieceColor.BLACK, "blackKnight.png", 0, 6));

        initializePiece(listOfPieces, new Bishop(PieceColor.WHITE, "whiteBishop.png", 7, 2));
        initializePiece(listOfPieces, new Bishop(PieceColor.WHITE, "whiteBishop.png", 7, 5));
        initializePiece(listOfPieces, new Bishop(PieceColor.BLACK, "blackBishop.png", 0, 2));
        initializePiece(listOfPieces, new Bishop(PieceColor.BLACK, "blackBishop.png", 0, 5));

        initializePiece(listOfPieces, whiteKing = new King(PieceColor.WHITE, "whiteKing.png", 7, 4));
        initializePiece(listOfPieces, blackKing = new King(PieceColor.BLACK, "blackKing.png", 0, 4));

        initializePiece(listOfPieces,new Queen(PieceColor.WHITE, "whiteQueen.png", 7, 3));
        initializePiece(listOfPieces,new Queen(PieceColor.BLACK, "blackQueen.png", 0, 3));
    }

    private void initializePawns(ArrayList<ChessPiece> listOfPieces){
        for (int i = 0; i < 8; i++) {
            initializePiece(listOfPieces, new Pawn(PieceColor.WHITE, "whitePawn.png", 6, i));
            initializePiece(listOfPieces, new Pawn(PieceColor.BLACK, "blackPawn.png", 1, i));
        }
    }

    private void initializePiece(ArrayList<ChessPiece> list, ChessPiece chessPiece){
        list.add(chessPiece);
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }
}
