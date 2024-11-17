package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;

import java.awt.*;

public class King extends ChessPiece {

    public King(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        kingMoveUp(chessboard);
        kingMoveDown(chessboard);
        kingMoveLeft(chessboard);
        kingMoveRight(chessboard);
        kingMoveDiagonallyUpLeft(chessboard);
        kingMoveDiagonallyUpRight(chessboard);
        kingMoveDiagonallyDownLeft(chessboard);
        kingMoveDiagonallyDownRight(chessboard);
    }

    private void kingMoveUp(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition()-1, getColumnPosition(), chessboard);
    }

    private void kingMoveDown(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition()+1, getColumnPosition(), chessboard);
    }
    private void kingMoveLeft(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition(), getColumnPosition()-1, chessboard);
    }
    private void kingMoveRight(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition(), getColumnPosition()+1, chessboard);
    }

    private void kingMoveDiagonallyUpLeft(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition()-1, getColumnPosition()-1, chessboard);
    }

    private void kingMoveDiagonallyUpRight(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition()-1, getColumnPosition()+1, chessboard);
    }
    private void kingMoveDiagonallyDownLeft(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition()+1, getColumnPosition()-1, chessboard);
    }
    private void kingMoveDiagonallyDownRight(Chessboard chessboard){
        ChessPieceMovement.isMoveValid(this, getRowPosition()+1, getColumnPosition()+1, chessboard);
    }
}
