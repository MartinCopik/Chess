package chessPieces;

import chessGame.Chessboard;
import chessPieces.MobilityOfPieces.KnightMobility;

import java.awt.*;

public class Knight extends ChessPiece implements KnightMobility {


    public Knight(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        knightMoveUpShortLeft(chessboard, this);
        knightMoveUpShortRight(chessboard, this);
        knightMoveUpLongLeft(chessboard, this);
        knightMoveUpLongRight(chessboard, this);
        knightMoveDownShortLeft(chessboard, this);
        knightMoveDownShortRight(chessboard, this);
        knightMoveDownLongLeft(chessboard, this);
        knightMoveDownLongRight(chessboard, this);
    }
}
