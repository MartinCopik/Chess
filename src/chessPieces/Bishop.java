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
        EChessPiecesMovement.BISHOP.moveDiagonallyUpRight(chessboard, this);
        EChessPiecesMovement.BISHOP.moveDiagonallyUpLeft(chessboard, this);
        EChessPiecesMovement.BISHOP.moveDiagonallyDownLeft(chessboard, this);
        EChessPiecesMovement.BISHOP.moveDiagonallyDownRight(chessboard, this);
    }
}
