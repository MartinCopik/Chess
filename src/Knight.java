import java.awt.*;

public class Knight extends ChessPiece{

    public Knight(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void chessPieceMovesPossibilities(Chessboard chessboard) {
        knightMoveUpShortLeft(chessboard, rowPosition, columnPosition);
        knightMoveUpShortRight(chessboard,  rowPosition, columnPosition);
        knightMoveUpLongLeft(chessboard,  rowPosition, columnPosition);
        knightMoveUpLongRight(chessboard,  rowPosition, columnPosition);

        knightMoveDownShortLeft(chessboard,  rowPosition, columnPosition);
        knightMoveDownShortRight(chessboard,  rowPosition, columnPosition);
        knightMoveDownLongLeft(chessboard,  rowPosition, columnPosition);
        knightMoveDownLongRight(chessboard,  rowPosition, columnPosition);
    }

    public void knightMoveUpShortLeft(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition-1, columnPosition-2, chessboard, this);
    }
    public void knightMoveUpShortRight(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition - 1, columnPosition + 2, chessboard, this);
    }
    public void knightMoveUpLongLeft(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition-2, columnPosition-1, chessboard, this);
    }
    public void knightMoveUpLongRight(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition - 2, columnPosition + 1, chessboard, this);
    }
    public void knightMoveDownShortLeft(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition+1, columnPosition-2, chessboard, this);
    }
    public void knightMoveDownShortRight(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition+1, columnPosition+2, chessboard, this);
    }
    public void knightMoveDownLongLeft(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition+2, columnPosition-1, chessboard, this);
    }
    public void knightMoveDownLongRight(Chessboard chessboard, int rowPosition, int columnPosition){
        Move.isMoveValid(rowPosition+2, columnPosition+1, chessboard, this);
    }

    public void knightMoves(Chessboard chessboard, EChessPiece EChessPiece, int rowPosition, int columnPosition){
        knightMoveUpShortLeft(chessboard, EChessPiece, rowPosition, columnPosition);
        knightMoveUpShortRight(chessboard, EChessPiece, rowPosition, columnPosition);
        knightMoveUpLongLeft(chessboard, EChessPiece, rowPosition, columnPosition);
        knightMoveUpLongRight(chessboard, EChessPiece, rowPosition, columnPosition);

        knightMoveDownShortLeft(chessboard, EChessPiece, rowPosition, columnPosition);
        knightMoveDownShortRight(chessboard, EChessPiece, rowPosition, columnPosition);
        knightMoveDownLongLeft(chessboard, EChessPiece, rowPosition, columnPosition);
        knightMoveDownLongRight(chessboard, EChessPiece, rowPosition, columnPosition);
    }
}
