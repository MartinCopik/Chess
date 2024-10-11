import java.awt.*;

public class Move {

    public static int moveCounter;

    public static Piece figureToMove;
    public static Piece newSquareSpot;


    public static Piece getFigureToMove() {
        return figureToMove;
    }

    public static void setFigureToMove(Piece figureToMove) {
        Move.figureToMove = figureToMove;
    }

    public static Piece getNewSquareSpot() {
        return newSquareSpot;
    }

    public static void setNewSquareSpot(Piece newSquareSpot) {
        Move.newSquareSpot = newSquareSpot;
    }

    public static boolean rightColorToMakeMove(Piece pieceToMakeMove){
        if (moveCounter%2 == 0 && pieceToMakeMove.pieceColor.equals(Color.WHITE)){
            GameManager.checkGameStatus(pieceToMakeMove.getPlayer());
            return true;
        } else if (moveCounter%2 == 1 && pieceToMakeMove.pieceColor.equals(Color.BLACK)){
            GameManager.checkGameStatus(pieceToMakeMove.getPlayer());
            return true;
        }
        return false;
    }

     static void movingThePiece(Piece newSquareSpot){
         Chessboard.arrayBoard[figureToMove.getRowPosition()][figureToMove.getColumPosition()][1] = null;
         Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1] = figureToMove;
         newSquareSpot.getEmptyPiecePanel().add(figureToMove.getPieceLabel());

         figureToMove.setActualPositionOfPiece(figureToMove);
         figureToMove.checkIfCheckIsMade(figureToMove);

         if (figureToMove instanceof Pawn){
            ((Pawn) figureToMove).readyToBePromoted();
         }
         if (figureToMove instanceof King && figureToMove.getPieceFirstMove()){
             if (newSquareSpot == Chessboard.getEmptySquare(figureToMove.getRowPosition(), 6)){
                 figureToMove = Chessboard.getArrayBoard()[figureToMove.getRowPosition()][7][1];
                 movingThePiece(Chessboard.getEmptySquare(figureToMove.getRowPosition(), 5));
             } else if (newSquareSpot == Chessboard.getEmptySquare(figureToMove.getRowPosition(), 2)) {
                 figureToMove = Chessboard.getArrayBoard()[figureToMove.getRowPosition()][0][1];
                 movingThePiece(Chessboard.getEmptySquare(figureToMove.getRowPosition(), 3));
             }
         }
         figureToMove.setPieceFirstMove(false);
         figureToMove.getPlayer().setPieceAttackingKing(null);

    }

     static void discardingThePiece(Piece newSquareSpot){
        Chessboard.arrayDiscardedPieces.add(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);
        Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].getPlayer().getPlayerPieces().
                 remove(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);

        newSquareSpot.getEmptyPiecePanel().removeAll();

    }


    public static void makeCleanMove(Piece newSquareSpot){
        movingThePiece(newSquareSpot);

        moveCounter++;
        figureToMove = null;

        Chessboard.setColors();
    }

    public static void makeMoveDiscardPiece(Piece newSquareSpot){
        discardingThePiece(newSquareSpot);
        movingThePiece(newSquareSpot);

        moveCounter++;
        figureToMove = null;

        Chessboard.setColors();

    }
}
