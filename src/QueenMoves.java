import javax.swing.*;
import java.awt.*;


public class QueenMoves {

    Move move = new Move();

    public void queenMoveUP(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int row = rowPosition-1; row >= 0 ; row--){
            if (move.isMoveValid(chessPiece, row, columnPosition, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDown(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int row = rowPosition+1; row<= 7; row++){
            if (move.isMoveValid(chessPiece, row, columnPosition, chessboard)){
                break;
            }
        }
    }
    public void queenMoveLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int colum = columnPosition-1; colum>= 0; colum--){
            if (move.isMoveValid(chessPiece, rowPosition, colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int colum = columnPosition+1; colum<= 7; colum++){
            if (move.isMoveValid(chessPiece, rowPosition, colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyUpLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }

    public void queenMoveDiagonallyDownLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyUpRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyDownRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }

    public void queenMoves(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition) {

        queenMoveUP(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveDown(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveLeft(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveRight(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveDiagonallyUpLeft(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveDiagonallyUpRight(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveDiagonallyDownLeft(chessboard, chessPiece, rowPosition, columnPosition);
        queenMoveDiagonallyDownRight(chessboard, chessPiece, rowPosition, columnPosition);
    }

}