public class Move {

    public boolean isOutOfBorder(int rowToCheck, int columToCheck, Chessboard chessboard){
        if (rowToCheck <0 || columToCheck <0 || rowToCheck > chessboard.getArrayBoard().length-1 || columToCheck > chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }
    public boolean pieceIsAttacking(Piece pieceToMakeAttack, int rowToCheck, int columToCheck, Chessboard chessboard){
        if (pieceToMakeAttack.getPieceColor() != chessboard.getArrayBoard()[rowToCheck][columToCheck].getPieceOnSquare().getPieceColor()){
            return true;
        }
        return false;
    }
    public boolean positionIsTaken(int rowToCheck, int columToCheck, Chessboard chessboard){
        if (chessboard.getArrayBoard()[rowToCheck][columToCheck].getPieceOnSquare() != null){
            return true;
        }
        return false;
    }

    public boolean isMoveValid(Piece pieceToMakeMove, int rowToCheck, int columToCheck, Chessboard chessboard){
        if (isOutOfBorder(rowToCheck, columToCheck, chessboard)){
            return true;
        }
        if (positionIsTaken(rowToCheck, columToCheck, chessboard)){
            if (pieceIsAttacking(pieceToMakeMove, rowToCheck, columToCheck, chessboard)){
                chessboard.getArrayBoard()[rowToCheck][columToCheck].markTheSquareForAttack();
            } else if (!pieceIsAttacking(pieceToMakeMove, rowToCheck, columToCheck, chessboard)) {
            }
            return true;
        }
        if (!positionIsTaken(rowToCheck, columToCheck, chessboard)){
            chessboard.getArrayBoard()[rowToCheck][columToCheck].markTheSquareForMove();
            return false;
        }
        return false;
    }

    public void movingThePiece(EmptySquare newSquareSpot, Piece pieceToMove, Chessboard chessboard){
        chessboard.getArrayBoard()[pieceToMove.getRowPosition()][pieceToMove.getColumPosition()].discardPieceFromSquare();
        newSquareSpot.setPieceOnSquare(pieceToMove);
        pieceToMove.setRowPosition(newSquareSpot.getRowPosition());
        pieceToMove.setColumPosition(newSquareSpot.getColumPosition());

        pieceToMove.setPieceFirstMove(false);
    }

    public void discardingThePiece(EmptySquare newSquareSpot, Piece pieceToBeDiscarded, Chessboard chessboard){
        newSquareSpot.discardPieceFromSquare();
        chessboard.getListOfPieces().remove(pieceToBeDiscarded);
    }


    public void makeCleanMove(EmptySquare newSquareSpot, Piece pieceToMove, Chessboard chessboard){
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }

    public void makeDiscardMovePiece(EmptySquare newSquareSpot, Piece pieceToBeDiscarded, Piece pieceToMove, Chessboard chessboard){
        discardingThePiece(newSquareSpot, pieceToBeDiscarded, chessboard);
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }
}
