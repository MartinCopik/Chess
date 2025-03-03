package chessGame;

import chessPieces.ChessPiece;
import chessPieces.King;
import chessPieces.Pawn;

import java.util.ArrayList;

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
    public static boolean movePossibility(ChessPiece pieceToMakeMove, int rowToCheck, int columnToCheck, Chessboard chessboard){
        if (isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }else if (positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (pieceIsAttacking(pieceToMakeMove, rowToCheck, columnToCheck, chessboard)){
                if (!chessboard.getGameManager().isValidationInProcess()){
                    if (chessboard.getGameManager().invalidMove(pieceToMakeMove, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                        return true;
                    }
                }
                pieceToMakeMove.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pieceToMakeMove);
            }
            return true;
        }else if (!positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (!chessboard.getGameManager().isValidationInProcess()){
                if (chessboard.getGameManager().invalidMove(pieceToMakeMove, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                    return false;
                }
            }
            pieceToMakeMove.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pieceToMakeMove);
            return false;
        }
        return false;
    }

    /**
     * move the chess piece from one chessSquare on another
     * @param newSquareSpot new square spot
     * @param pieceToMove chess piece to move
     * @param chessboard chessboard
     */
    public static void movingThePiece(ChessSquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        chessboard.getArrayBoard()[pieceToMove.getRowPosition()][pieceToMove.getColumnPosition()].discardPieceFromSquare();
        newSquareSpot.setPieceOnSquare(pieceToMove);
        pieceToMove.setRowPosition(newSquareSpot.getRowPosition());
        pieceToMove.setColumnPosition(newSquareSpot.getColumnPosition());
    }

    /**
     * discards the chess piece from new square spot and from listOfPieces (the game)
     * @param newSquareSpot new square spot
     * @param list discards piece from specified list
     */
    public static void discardingThePiece(ChessSquare newSquareSpot, ArrayList<ChessPiece> list){
        if (newSquareSpot.getPieceOnSquare() instanceof King){
            return;
        }
        list.remove(newSquareSpot.getPieceOnSquare());
        newSquareSpot.discardPieceFromSquare();
    }

    /**
     * move the chess piece, if necessary with discard process of the original one
     * @param newSquareSpot new square spot
     * @param pieceToMove piece to be moved on new square spot
     * @param chessboard
     */
    public static void makeTheMove(ChessSquare newSquareSpot, ChessPiece pieceToMove, Chessboard chessboard){
        discardingThePiece(newSquareSpot, chessboard.getListOfPieces());
        movingThePiece(newSquareSpot, pieceToMove, chessboard);

        if (pieceToMove instanceof Pawn){
            pawnMoves(pieceToMove, chessboard);
        } else if (pieceToMove instanceof King) {
            castlingMovement(newSquareSpot,pieceToMove, chessboard);
        }
    }

    /**
     * checks if the selected "picked" chess piece can move on new square spot and make clean or discard movement
     * depending on current status of the new square spot
     * @param newSquareSpot new square spot
     * @param chessboard
     */
    public static boolean canPieceMakeThisMove(ChessSquare newSquareSpot, Chessboard chessboard){
        if (chessboard.getSelectedPieceToMove().getChessPieceMovementMap().containsKey(newSquareSpot)){
                makeTheMove(newSquareSpot, chessboard.getSelectedPieceToMove(), chessboard);
                chessboard.getSelectedPieceToMove().setPieceFirstMove(false);
            return true;
        }
        return false;
    }

    /**
     * starts process of the promotion of pawn
     * @param chessPiece promoting pawn
     * @param chessboard
     */
    private static void pawnMoves(ChessPiece chessPiece, Chessboard chessboard){
        if (chessPiece.getRowPosition() == 0 || chessPiece.getRowPosition() == 7){
            new PromotionWindow(chessPiece, chessboard);
        }
    }

    /**
     * make castling movement
     * @param newSquareSpot
     * @param chessPiece
     * @param chessboard
     */
    private static void castlingMovement(ChessSquare newSquareSpot, ChessPiece chessPiece, Chessboard chessboard){
        if (chessPiece.getPieceFirstMove()){
            makeCastlingMovement(newSquareSpot, chessPiece, chessboard, 6, 5, 7);
            makeCastlingMovement(newSquareSpot, chessPiece, chessboard, 2, 3, 0);
        }
    }

    /**
     * checks if player clicked on right square to make castling, if so, makes the movement of right or left rook
     * @param newSquareSpot square which was clicked on
     * @param king specified king making castling
     * @param chessboard chessboard
     * @param columnClicked column needed to be clicked on
     * @param columnRookNewPosition new column position of specified rook after done castling
     * @param columnRookPosition present column position of specified rook
     */
    private static void makeCastlingMovement(ChessSquare newSquareSpot, ChessPiece king, Chessboard chessboard, int columnClicked, int columnRookNewPosition, int columnRookPosition){
        if (newSquareSpot == chessboard.getArrayBoard()[king.getRowPosition()][columnClicked]){
            movingThePiece(chessboard.getArrayBoard()[king.getRowPosition()][columnRookNewPosition],
                    chessboard.getArrayBoard()[king.getRowPosition()][columnRookPosition].getPieceOnSquare(), chessboard);
        }
    }
}
