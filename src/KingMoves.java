import javax.swing.*;
import java.awt.*;

public class KingMoves {

    Move move = new Move();

    void kingMoveUp(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition-1, columnPosition, chessboard);
    }

    void kingMoveDown(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition+1, columnPosition, chessboard);
    }
    void kingMoveLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition, columnPosition-1, chessboard);
    }
    void kingMoveRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition, columnPosition+1, chessboard);
    }

    void kingMoveDiagonallyUpLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition-1, columnPosition-1, chessboard);
    }

    void kingMoveDiagonallyUpRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition-1, columnPosition+1, chessboard);
    }
    void kingMoveDiagonallyDownLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition+1, columnPosition-1, chessboard);
    }
    void kingMoveDiagonallyDownRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition+1, columnPosition+1, chessboard);
    }

    public void kingMoves(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition) {
        kingMoveUp(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveDown(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveRight(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveLeft(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveDiagonallyUpLeft(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveDiagonallyDownLeft(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveDiagonallyUpRight(chessboard, chessPiece, rowPosition, columnPosition);
        kingMoveDiagonallyDownRight(chessboard, chessPiece, rowPosition, columnPosition);
    }
}