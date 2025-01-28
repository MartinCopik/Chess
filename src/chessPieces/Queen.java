package chessPieces;

import chessGame.Chessboard;
import java.awt.*;

public class Queen extends ChessPiece {

    public Queen(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        EChessPiecesMovement.QUEEN.moveUp(chessboard, this);
        EChessPiecesMovement.QUEEN.moveDown(chessboard, this);
        EChessPiecesMovement.QUEEN.moveLeft(chessboard, this);
        EChessPiecesMovement.QUEEN.moveRight(chessboard, this);

        EChessPiecesMovement.QUEEN.moveDiagonallyUpLeft(chessboard, this);
        EChessPiecesMovement.QUEEN.moveDiagonallyUpRight(chessboard, this);
        EChessPiecesMovement.QUEEN.moveDiagonallyDownLeft(chessboard, this);
        EChessPiecesMovement.QUEEN.moveDiagonallyDownRight(chessboard, this);
    }
}
