package chessPieces;

import chessGame.Chessboard;
import chessPieces.MobilityOfPieces.PawnMobility;

import java.awt.*;

public class Pawn extends ChessPiece implements PawnMobility {


    public Pawn(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        checkColorOfPawn(this, chessboard);
    }
}
