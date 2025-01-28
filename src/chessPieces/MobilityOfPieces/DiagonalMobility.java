package chessPieces.MobilityOfPieces;

import chessGame.ChessPieceMovement;
import chessGame.Chessboard;
import chessPieces.ChessPiece;

public interface DiagonalMobility {

    default void moveDiagonallyUpLeft(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    default void moveDiagonallyDownLeft(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row > chessboard.getArrayBoard().length || colum >= 0){
            row++;
            colum--;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    default void moveDiagonallyUpRight(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row >= 0 || colum < chessboard.getArrayBoard().length){
            row--;
            colum++;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
    default void moveDiagonallyDownRight(Chessboard chessboard, ChessPiece piece){
        int row = piece.getRowPosition();
        int colum = piece.getColumnPosition();
        while (row < chessboard.getArrayBoard().length || colum < chessboard.getArrayBoard().length){
            row++;
            colum++;
            if (ChessPieceMovement.movePossibility(piece, row, colum, chessboard)){
                break;
            }
        }
    }
}
