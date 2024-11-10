public class Move {

    public static boolean isOutOfBorder(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (rowToCheck <0 || columnToCheck <0 || rowToCheck > chessboard.getArrayBoard().length-1 || columnToCheck > chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }
    public static boolean pieceIsAttacking(ChessPiece pieceToMakeAttack, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (pieceToMakeAttack.getChessPieceColor() != chessboard.getArrayBoard()[rowToCheck][columnToCheck].getPieceOnSquare().getChessPieceColor()){
            return true;
        }
        return false;
    }
    public static boolean positionIsTaken(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (chessboard.getArrayBoard()[rowToCheck][columnToCheck].getPieceOnSquare() != null){
            return true;
        }
        return false;
    }

    public static boolean isMoveValid(ChessPiece pieceToMakeMove, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }
        if (positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (pieceIsAttacking(pieceToMakeMove, rowToCheck, columnToCheck, chessboard)){
                pieceToMakeMove.getChessPieceMovesMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pieceToMakeMove);
            }
            return true;
        }
        if (!positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            pieceToMakeMove.getChessPieceMovesMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pieceToMakeMove);
            return false;
        }
        return false;
    }

    private static void movingThePiece(ChessSquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        chessboard.getArrayBoard()[pieceToMove.getRowPosition()][pieceToMove.getColumnPosition()].discardPieceFromSquare();
        newSquareSpot.setPieceOnSquare(pieceToMove);
        pieceToMove.setRowPosition(newSquareSpot.getRowPosition());
        pieceToMove.setColumnPosition(newSquareSpot.getColumnPosition());

        pieceToMove.setPieceFirstMove(false);
        chessboard.setAllChessPiecesMoveMap();
    }

    private static void discardingThePiece(ChessSquare newSquareSpot, ChessPiece pieceToBeDiscarded, Chessboard chessboard){
        newSquareSpot.discardPieceFromSquare();
        chessboard.getListOfPieces().remove(pieceToBeDiscarded);
    }


    private static void makeCleanMove(ChessSquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }

    private static void makeDiscardMovePiece(ChessSquare newSquareSpot, ChessPiece pieceToBeDiscarded, ChessPiece pieceToMove, Chessboard chessboard){
        discardingThePiece(newSquareSpot, pieceToBeDiscarded, chessboard);
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }

    public static void canPieceMakeThisMove(ChessSquare newSquareSpot, Chessboard chessboard){
        if (chessboard.getSelectedPieceToMove().getChessPieceMovesMap().containsKey(newSquareSpot)){
            if (newSquareSpot.getPieceOnSquare() != null){
                makeDiscardMovePiece(newSquareSpot, newSquareSpot.getPieceOnSquare(), chessboard.getSelectedPieceToMove(), chessboard);
            }else {
                makeCleanMove(newSquareSpot, chessboard.getSelectedPieceToMove(), chessboard);
            }
        }
    }
}
