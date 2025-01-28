package chessPieces;

import chessGame.Chessboard;
import java.awt.*;

public class Knight extends ChessPiece {


    public Knight(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        EChessPiecesMovement.KNIGHT.knightMoveUpShortLeft(chessboard, this);
        EChessPiecesMovement.KNIGHT.knightMoveUpShortRight(chessboard, this);
        EChessPiecesMovement.KNIGHT.knightMoveUpLongLeft(chessboard, this);
        EChessPiecesMovement.KNIGHT.knightMoveUpLongRight(chessboard, this);

        EChessPiecesMovement.KNIGHT.knightMoveDownShortLeft(chessboard, this);
        EChessPiecesMovement.KNIGHT.knightMoveDownShortRight(chessboard, this);
        EChessPiecesMovement.KNIGHT.knightMoveDownLongLeft(chessboard, this);
        EChessPiecesMovement.KNIGHT.knightMoveDownLongRight(chessboard, this);
    }
}
