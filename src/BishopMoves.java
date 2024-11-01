public class BishopMoves {

    Move move = new Move();

    public void bishopMoveDiagonallyUpLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }

    public void bishopMoveDiagonallyDownLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void bishopMoveDiagonallyUpRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }
    public void bishopMoveDiagonallyDownRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        int row = rowPosition;
        int colum = columnPosition;
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (move.isMoveValid(chessPiece, row, colum, chessboard)){
                break;
            }
        }
    }

    public void bishopMoves(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition) {
        bishopMoveDiagonallyUpLeft(chessboard, chessPiece, rowPosition, columnPosition);
        bishopMoveDiagonallyDownLeft(chessboard, chessPiece, rowPosition, columnPosition);
        bishopMoveDiagonallyUpRight(chessboard, chessPiece, rowPosition, columnPosition);
        bishopMoveDiagonallyDownRight(chessboard, chessPiece, rowPosition, columnPosition);
    }
}