package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;

import java.awt.*;

public class Bishop extends ChessPiece {

    public Bishop(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        bishopMoveDiagonallyUpLeft(chessboard);
        bishopMoveDiagonallyUpRight(chessboard);
        bishopMoveDiagonallyDownLeft(chessboard);
        bishopMoveDiagonallyDownRight(chessboard);
    }

    private void bishopMoveDiagonallyUpLeft(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (ChessPieceMovement.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }

    private void bishopMoveDiagonallyDownLeft(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (ChessPieceMovement.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }
    private void bishopMoveDiagonallyUpRight(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (ChessPieceMovement.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }
    private void bishopMoveDiagonallyDownRight(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (ChessPieceMovement.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }
}
