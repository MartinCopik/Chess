package chessPieces;

import chessGame.Chessboard;
import chessPieces.MobilityOfPieces.DiagonalMobility;
import chessPieces.MobilityOfPieces.StraightMobility;

import java.awt.*;

public class Queen extends ChessPiece implements StraightMobility, DiagonalMobility {

    public Queen(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        moveUP(chessboard, this);
        moveDown(chessboard, this);
        moveLeft(chessboard, this);
        moveRight(chessboard, this);
        moveDiagonallyUpLeft(chessboard, this);
        moveDiagonallyUpRight(chessboard, this);
        moveDiagonallyDownLeft(chessboard, this);
        moveDiagonallyDownRight(chessboard, this);
    }
}
