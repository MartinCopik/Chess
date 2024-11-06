public class Move {

    //ivo: zase mi tu chyba konstruktor prazdny ale v tomto pripade by som spravil staticku triedu pretoze mame len vypocty, teda vraciame true alebo false tazke nieco to len vypocita a potom vrati jednoduchy return
    public boolean isOutOfBorder(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (rowToCheck <0 || columnToCheck <0 || rowToCheck > chessboard.getArrayBoard().length-1 || columnToCheck > chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }
    public boolean pieceIsAttacking(ChessPiece pieceToMakeAttack, int rowToCheck, int columnToCheck, Chessboard chessboard){
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

    public boolean isMoveValid(ChessPiece pieceToMakeMove, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }
        if (positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (pieceIsAttacking(pieceToMakeMove, rowToCheck, columnToCheck, chessboard)){
                chessboard.getArrayBoard()[rowToCheck][columnToCheck].markTheSquareForAttack();
            }
            return true;
        }
        if (!positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            chessboard.getArrayBoard()[rowToCheck][columnToCheck].markTheSquareForMove();
            return false;
        }
        return false;
    }

    //ivo: ak sa tato metoda nepouziva nikde ine len v Move{} tak private, tak ako variables
    private void movingThePiece(EmptySquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        chessboard.getArrayBoard()[pieceToMove.getRowPosition()][pieceToMove.getColumnPosition()].discardPieceFromSquare();
        newSquareSpot.setPieceOnSquare(pieceToMove);
        pieceToMove.setRowPosition(newSquareSpot.getRowPosition());
        pieceToMove.setColumnPosition(newSquareSpot.getColumnPosition());

        pieceToMove.setPieceFirstMove(false);
    }

    private void discardingThePiece(EmptySquare newSquareSpot, ChessPiece pieceToBeDiscarded, Chessboard chessboard){
        newSquareSpot.discardPieceFromSquare();
        chessboard.getListOfPieces().remove(pieceToBeDiscarded);
    }


    public void makeCleanMove(EmptySquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }

    public void makeDiscardMovePiece(EmptySquare newSquareSpot, ChessPiece pieceToBeDiscarded, ChessPiece pieceToMove, Chessboard chessboard){
        discardingThePiece(newSquareSpot, pieceToBeDiscarded, chessboard);
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }
}
