public class Move {

    public boolean isOutOfBorder(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (rowToCheck <0 || columnToCheck <0 || rowToCheck > chessboard.getArrayBoard().length-1 || columnToCheck > chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }
    public boolean pieceIsAttacking(Piece pieceToMakeAttack, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (pieceToMakeAttack.getPieceColor() != chessboard.getArrayBoard()[rowToCheck][columnToCheck].getPieceOnSquare().getPieceColor()){
            return true;
        }
        return false;
    }
    public boolean positionIsTaken(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (chessboard.getArrayBoard()[rowToCheck][columnToCheck].getPieceOnSquare() != null){
            return true;
        }
        return false;
    }

    public boolean isMoveValid(Piece pieceToMakeMove, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }
        if (positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (pieceIsAttacking(pieceToMakeMove, rowToCheck, columnToCheck, chessboard)){
                chessboard.getArrayBoard()[rowToCheck][columnToCheck].markTheSquareForAttack();
            } else if (!pieceIsAttacking(pieceToMakeMove, rowToCheck, columnToCheck, chessboard)) {
            }
            return true;
        }
        if (!positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            chessboard.getArrayBoard()[rowToCheck][columnToCheck].markTheSquareForMove();
            return false;
        }
        return false;
    }

    public void movingThePiece(EmptySquare newSquareSpot, Piece pieceToMove, Chessboard chessboard){
        chessboard.getArrayBoard()[pieceToMove.getRowPosition()][pieceToMove.getColumnPosition()].discardPieceFromSquare();
        newSquareSpot.setPieceOnSquare(pieceToMove);
        pieceToMove.setRowPosition(newSquareSpot.getRowPosition());
        pieceToMove.setColumnPosition(newSquareSpot.getColumnPosition());

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
