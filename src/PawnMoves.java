public class PawnMoves {

    Move move = new Move();

    public void blackPawnMoves(Boolean pieceFirstMove, Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        pawnMoveDown(pieceFirstMove, chessboard, chessPiece, rowPosition, columnPosition);
        blackPawnAttacks(chessboard, chessPiece, rowPosition, columnPosition);
    }

    public void blackPawnAttacks(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        pawnMoveDiagonallyDownLeft(chessboard, chessPiece, rowPosition, columnPosition);
        pawnMoveDiagonallyDownRight(chessboard, chessPiece, rowPosition, columnPosition);
    }

    public void pawnMoveDiagonallyDownLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        possibleAttackMove(chessPiece,rowPosition+1, columnPosition-1, chessboard);
    }

    public void pawnMoveDiagonallyDownRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        possibleAttackMove(chessPiece, rowPosition+1, columnPosition+1, chessboard);
    }

    public void pawnMoveDown(boolean pieceFirstMove, Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int row = rowPosition+1; row <= rowPosition+2; row++){
            if (impossibleMove(chessboard, chessPiece, row, columnPosition)){
                break;
            }
            if (!pieceFirstMove){
                break;
            }
        }
    }


    public void whitePawnMoves(Boolean pieceFirstMove, Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        pawnMoveUp(pieceFirstMove, chessboard, chessPiece, rowPosition, columnPosition);
        whitePawnAttacks(chessboard, chessPiece, rowPosition, columnPosition);
    }

    public void whitePawnAttacks(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        pawnMoveDiagonallyUpLeft(chessboard, chessPiece, rowPosition, columnPosition);
        pawnMoveDiagonallyUpRight(chessboard, chessPiece, rowPosition, columnPosition);
    }

    public void pawnMoveUp(boolean pieceFirstMove, Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        for (int row = rowPosition-1; row >= rowPosition-2; row--){
            if (impossibleMove(chessboard, chessPiece, row, columnPosition)){
                break;
            }
            if (!pieceFirstMove){
                break;
            }
        }
    }

    public void pawnMoveDiagonallyUpLeft(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        possibleAttackMove(chessPiece, rowPosition-1,columnPosition-1, chessboard);
    }

    public void pawnMoveDiagonallyUpRight(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition){
        possibleAttackMove(chessPiece, rowPosition-1,columnPosition+1, chessboard);
    }

    public void possibleAttackMove(ChessPiece chessPiece, int rowToCheck, int columToCheck, Chessboard chessboard) {
        if (move.isOutOfBorder(rowToCheck, columToCheck, chessboard)){
            return;
        }
        if (move.positionIsTaken(rowToCheck, columToCheck, chessboard) && move.pieceIsAttacking(chessPiece, rowToCheck, columToCheck, chessboard)) {
            chessboard.getArrayBoard()[rowToCheck][columToCheck].markTheSquareForAttack();
        }
    }

    public boolean impossibleMove(Chessboard chessboard, ChessPiece chessPiece, int rowPosition, int columnPosition) {
        if (move.isOutOfBorder(rowPosition, columnPosition, chessboard)){
            return true;
        }
        if (!move.positionIsTaken(rowPosition, columnPosition, chessboard)){
            chessboard.getArrayBoard()[rowPosition][columnPosition].markTheSquareForMove();
            return false;
        }
        return true;
    }
}