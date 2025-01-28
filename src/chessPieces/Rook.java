package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;

import java.awt.*;

public class Rook extends ChessPiece {

    public Rook(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        EChessPiecesMovement.ROOK.moveUp(chessboard, this);
        EChessPiecesMovement.ROOK.moveDown(chessboard, this);
        EChessPiecesMovement.ROOK.moveLeft(chessboard, this);
        EChessPiecesMovement.ROOK.moveRight(chessboard, this);
    }
}