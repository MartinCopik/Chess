import java.awt.*;

public class King extends ChessPiece{

    public King(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void chessPieceMovePossibilities(Chessboard chessboard) {
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
        Move.isMoveValid(this, getRowPosition()-1, getColumnPosition(), chessboard);
    }

    private void kingMoveDown(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+1, getColumnPosition(), chessboard);
    }
    private void kingMoveLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition(), getColumnPosition()-1, chessboard);
    }
    private void kingMoveRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition(), getColumnPosition()+1, chessboard);
    }

    private void kingMoveDiagonallyUpLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()-1, getColumnPosition()-1, chessboard);
    }

    private void kingMoveDiagonallyUpRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()-1, getColumnPosition()+1, chessboard);
    }
    private void kingMoveDiagonallyDownLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+1, getColumnPosition()-1, chessboard);
    }
    private void kingMoveDiagonallyDownRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+1, getColumnPosition()+1, chessboard);
    }
}
