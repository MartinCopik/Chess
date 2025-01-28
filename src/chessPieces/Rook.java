package chessPieces;

import chessGame.Chessboard;
import chessPieces.MobilityOfPieces.StraightMobility;

import java.awt.*;

public class Rook extends ChessPiece implements StraightMobility {

    public Rook(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        moveUP(chessboard, this);
        moveDown(chessboard, this);
        moveLeft(chessboard, this);
        moveRight(chessboard, this);
    }
}