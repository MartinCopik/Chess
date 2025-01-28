package chessPieces.MobilityOfPieces;

import chessGame.ChessPieceMovement;
import chessGame.Chessboard;
import chessPieces.ChessPiece;

public interface StraightMobility {

     default void moveUP(Chessboard chessboard, ChessPiece piece){
        for (int row = piece.getRowPosition()-1; row >= 0 ; row--){
            if (ChessPieceMovement.movePossibility(piece, row, piece.getColumnPosition(), chessboard)) {
                break;
            }
        }
    }
    default void moveDown (Chessboard chessboard, ChessPiece piece){
        for (int row = piece.getRowPosition() + 1; row < chessboard.getArrayBoard().length; row++) {
            if (ChessPieceMovement.movePossibility(piece, row, piece.getColumnPosition(), chessboard)) {
                break;
            }
        }
    }
     default void moveLeft (Chessboard chessboard, ChessPiece piece){
        for (int colum = piece.getColumnPosition() - 1; colum >= 0; colum--) {
            if (ChessPieceMovement.movePossibility(piece, piece.getRowPosition(), colum, chessboard)) {
                break;
            }
        }
    }
     default void moveRight (Chessboard chessboard, ChessPiece piece){
        for (int colum = piece.getColumnPosition() + 1; colum <= chessboard.getArrayBoard().length; colum++) {
            if (ChessPieceMovement.movePossibility(piece, piece.getRowPosition(), colum, chessboard)) {
                break;
            }
        }
    }
}
