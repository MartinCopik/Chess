public class RookMoves {

    Move move = new Move();

    public void rookMoveUP(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int row = rowPosition-1; row >= 0 ; row--){
            if (move.isMoveValid(chessPiece, row, columnPosition, chessboard)){
                break;
            }
        }
    }

    public void rookMoveDown(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int row = rowPosition+1; row<= 7; row++){
            if (move.isMoveValid(chessPiece, row, columnPosition, chessboard)){
                break;
            }
        }
    }
    public void rookMoveLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int colum = columnPosition-1; colum>= 0; colum--){
            if (move.isMoveValid(chessPiece, rowPosition, colum, chessboard)){
                break;
            }
        }
    }
    public void rookMoveRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int colum = columnPosition+1; colum<= 7; colum++){
            if (move.isMoveValid(chessPiece, rowPosition, colum, chessboard)){
                break;
            }
        }
    }

    public void rookMoves(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        rookMoveUP(chessboard, chessPiece, rowPosition, columnPosition);
        rookMoveDown(chessboard, chessPiece, rowPosition, columnPosition);
        rookMoveLeft(chessboard, chessPiece, rowPosition, columnPosition);
        rookMoveRight(chessboard, chessPiece, rowPosition, columnPosition);
    }
}