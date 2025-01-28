package chessPieces;

import chessGame.Chessboard;
import chessPieces.MobilityOfPieces.DiagonalMobility;

import java.awt.*;

public class Bishop extends ChessPiece implements DiagonalMobility {

    public Bishop(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        moveDiagonallyUpLeft(chessboard, this);
        moveDiagonallyUpRight(chessboard, this);
        moveDiagonallyDownLeft(chessboard, this);
        moveDiagonallyDownRight(chessboard, this);
    }
}
