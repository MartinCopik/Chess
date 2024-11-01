public class KnightMoves {

    Move move = new Move();

    public void knightMoveUpShortLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition-1, columnPosition-2, chessboard);
    }
    public void knightMoveUpShortRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition - 1, columnPosition + 2, chessboard);
    }
    public void knightMoveUpLongLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece, rowPosition-2, columnPosition-1, chessboard);
    }
    public void knightMoveUpLongRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece,rowPosition - 2, columnPosition + 1, chessboard);
    }
    public void knightMoveDownShortLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece,rowPosition+1, columnPosition-2, chessboard);
    }
    public void knightMoveDownShortRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece,rowPosition+1, columnPosition+2, chessboard);
    }
    public void knightMoveDownLongLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece,rowPosition+2, columnPosition-1, chessboard);
    }
    public void knightMoveDownLongRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        move.isMoveValid(chessPiece,rowPosition+2, columnPosition+1, chessboard);
    }

    public void knightMoves(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        knightMoveUpShortLeft(chessboard, chessPiece, rowPosition, columnPosition);
        knightMoveUpShortRight(chessboard, chessPiece, rowPosition, columnPosition);
        knightMoveUpLongLeft(chessboard, chessPiece, rowPosition, columnPosition);
        knightMoveUpLongRight(chessboard, chessPiece, rowPosition, columnPosition);

        knightMoveDownShortLeft(chessboard, chessPiece, rowPosition, columnPosition);
        knightMoveDownShortRight(chessboard, chessPiece, rowPosition, columnPosition);
        knightMoveDownLongLeft(chessboard, chessPiece, rowPosition, columnPosition);
        knightMoveDownLongRight(chessboard, chessPiece, rowPosition, columnPosition);
    }
}