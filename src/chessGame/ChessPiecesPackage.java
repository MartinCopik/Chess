package chessGame;

import chessPieces.*;

import java.awt.*;
import java.util.ArrayList;

public class ChessPiecesPackage {

    private King whiteKing;
    private King blackKing;
    private Rook whiteLeftRook;
    private Rook whiteRightRook;
    private Rook blackLeftRook;
    private Rook blackRightRook;

    public ChessPiecesPackage(ArrayList<ChessPiece> listOfPieces){
        initializePieces(listOfPieces);
    }

    private void initializePieces(ArrayList<ChessPiece> listOfPieces){
        for (int i = 0; i < 8; i++){
            listOfPieces.add(new Pawn(Color.WHITE, "whitePawn.png", 6, i));
            listOfPieces.add(new Pawn(Color.BLACK, "blackPawn.png", 1, i));

            if (i == 0) {
                listOfPieces.add(whiteLeftRook = new Rook(Color.WHITE, "whiteRook.png", 7, i));
                listOfPieces.add(blackLeftRook = new Rook(Color.BLACK, "blackRook.png", 0, i));

                listOfPieces.add(whiteRightRook = new Rook(Color.WHITE, "whiteRook.png", 7, i+7));
                listOfPieces.add(blackRightRook = new Rook(Color.BLACK, "blackRook.png", 0, i+7));
            }else if (i == 1){
                listOfPieces.add(new Knight(Color.WHITE, "whiteKnight.png", 7, i));
                listOfPieces.add(new Knight(Color.BLACK, "blackKnight.png", 0, i));
            } else if (i == 2) {
                listOfPieces.add(new Bishop(Color.WHITE, "whiteBishop.png", 7, i));
                listOfPieces.add(new Bishop(Color.BLACK, "blackBishop.png", 0, i));
            }else if (i == 3){
                listOfPieces.add(new Queen(Color.WHITE, "whiteQueen.png", 7, i));
                listOfPieces.add(new Queen(Color.BLACK, "blackQueen.png", 0, i));
            } else if (i == 4) {
                listOfPieces.add(whiteKing = new King(Color.WHITE, "whiteKing.png", 7, i, this.whiteRightRook, whiteLeftRook));
                listOfPieces.add(blackKing = new King(Color.BLACK, "blackKing.png", 0, i, this.blackRightRook, blackLeftRook));
            } else if (i == 5) {
                listOfPieces.add(new Bishop(Color.WHITE, "whiteBishop.png", 7, i));
                listOfPieces.add(new Bishop(Color.BLACK, "blackBishop.png", 0, i));
            } else if (i == 6) {
                listOfPieces.add(new Knight(Color.WHITE, "whiteKnight.png", 7, i));
                listOfPieces.add(new Knight(Color.BLACK, "blackKnight.png", 0, i));
            }
        }
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }
}
