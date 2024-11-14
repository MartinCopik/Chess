package chessGame;

import chessPieces.ChessPiece;

public class ChessPieceMovement {

    /**
     * checks if the movement is still on chessboard
     * @param rowToCheck
     * @param columnToCheck
     * @param chessboard
     * @return true if checking row or column is out of chessboard border
     */
    public static boolean isOutOfBorder(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (rowToCheck <0 || columnToCheck <0 || rowToCheck > chessboard.getArrayBoard().length-1 || columnToCheck > chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }

    /**
     * checks if chess piece to make the move is different color as chess piece on checking chess square
     * @param pieceToMakeAttack chess piece to make move
     * @param rowToCheck row position of chess square
     * @param columnToCheck column position of chess square
     * @param chessboard
     * @return true if chess piece on chess square is different color as chess piece to be moved
     */
    public static boolean pieceIsAttacking(ChessPiece pieceToMakeAttack, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (pieceToMakeAttack.getChessPieceColor() != chessboard.getArrayBoard()[rowToCheck][columnToCheck].getPieceOnSquare().getChessPieceColor()){
            return true;
        }
        return false;
    }

    /**
     * checks if the chess square is empty
     * @param rowToCheck
     * @param columnToCheck
     * @param chessboard
     * @return true if the checking chess square is taken by any other chess piece
     */
    public static boolean positionIsTaken(int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (chessboard.getArrayBoard()[rowToCheck][columnToCheck].getPieceOnSquare() != null){
            return true;
        }
        return false;
    }

    /**
     * checks if specified chess piece as Parameter can make the move
     * if the specified chess piece is Rook, Bishop, Queen this method also returns if they may move further
     * @param pieceToMakeMove checks the move of this piece
     * @param rowToCheck specified row of arrayBoard
     * @param columnToCheck specified column of arrayBoard
     * @param chessboard
     * @return returns if the chess piece may move further
     */
    public static boolean isMoveValid(ChessPiece pieceToMakeMove, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }
        if (positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (pieceIsAttacking(pieceToMakeMove, rowToCheck, columnToCheck, chessboard)){
                pieceToMakeMove.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pieceToMakeMove);
            }
            return true;
        }
        if (!positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            pieceToMakeMove.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pieceToMakeMove);
            return false;
        }
        return false;
    }

    /**
     * move the chess piece from one chessSquare to another
     * @param newSquareSpot new square spot
     * @param pieceToMove chess piece to move
     * @param chessboard
     */
    private static void movingThePiece(ChessSquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        chessboard.getArrayBoard()[pieceToMove.getRowPosition()][pieceToMove.getColumnPosition()].discardPieceFromSquare();
        newSquareSpot.setPieceOnSquare(pieceToMove);
        pieceToMove.setRowPosition(newSquareSpot.getRowPosition());
        pieceToMove.setColumnPosition(newSquareSpot.getColumnPosition());

        pieceToMove.setPieceFirstMove(false);
        chessboard.setAllChessPiecesMovementMap();
    }

    /**
     * discards the chess piece from new square spot and from listOfPieces (the game)
     * @param newSquareSpot new square spot
     * @param pieceToBeDiscarded chess piece to be discarded
     * @param chessboard
     */
    private static void discardingThePiece(ChessSquare newSquareSpot, ChessPiece pieceToBeDiscarded, Chessboard chessboard){
        newSquareSpot.discardPieceFromSquare();
        chessboard.getListOfPieces().remove(pieceToBeDiscarded);
    }

    /**
     * move the chess piece without any other piece to be discarded from the game
     * @param newSquareSpot new square spot
     * @param pieceToMove piece to be moved
     * @param chessboard
     */
    private static void makeCleanMove(ChessSquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }

    /**
     * move the chess piece with discard process of the original one
     * @param newSquareSpot new square spot where is original one
     * @param pieceToBeDiscarded the original chess piece to be discarded
     * @param pieceToMove piece to be moved on new square spot
     * @param chessboard
     */
    private static void makeDiscardMovePiece(ChessSquare newSquareSpot, ChessPiece pieceToBeDiscarded, ChessPiece pieceToMove, Chessboard chessboard){
        discardingThePiece(newSquareSpot, pieceToBeDiscarded, chessboard);
        movingThePiece(newSquareSpot, pieceToMove, chessboard);
    }

    /**
     * checks if the selected "picked" chess piece can move on new square spot and make clean or discard movement
     * depending on current status of the new square spot
     * @param newSquareSpot new square spot
     * @param chessboard
     */
    public static void canPieceMakeThisMove(ChessSquare newSquareSpot, Chessboard chessboard){
        if (chessboard.getSelectedPieceToMove().getChessPieceMovementMap().containsKey(newSquareSpot)){
            if (newSquareSpot.getPieceOnSquare() != null){
                makeDiscardMovePiece(newSquareSpot, newSquareSpot.getPieceOnSquare(), chessboard.getSelectedPieceToMove(), chessboard);
            }else {
                makeCleanMove(newSquareSpot, chessboard.getSelectedPieceToMove(), chessboard);
            }
        }
    }
}
