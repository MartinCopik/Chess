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
            GameManager.checkGameStatus(pieceToMakeMove.player);
//            EmptyPiece.attackedSquares.clear();
//            Piece.setAttackedSquares(Chessboard.blackPlayer);
//            pieceToMakeMove.player.king.kingIsInCheck();
            return true;
        } else if (moveCounter%2 == 1 && pieceToMakeMove.pieceColor.equals(Color.BLACK)){
            GameManager.checkGameStatus(pieceToMakeMove.player);
//            EmptyPiece.attackedSquares.clear();
//            Piece.setAttackedSquares(Chessboard.whitePlayer);
//            pieceToMakeMove.player.king.kingIsInCheck();
            return true;
        }
        return false;
    }

     static void movingThePiece(Piece newSquareSpot){
         Chessboard.arrayBoard[figureToMove.getRowPosition()][figureToMove.getColumPosition()][1] = null;
         Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1] = figureToMove;
         newSquareSpot.emptyPiecePanel.add(figureToMove.pieceLabel);

         figureToMove.setActualPositionOfPiece(figureToMove);
         figureToMove.checkIfCheckIsMade(figureToMove);

         if (figureToMove instanceof Pawn){
            ((Pawn) figureToMove).readyToBePromoted();
         }
         if (figureToMove instanceof King && figureToMove.pieceFirstMove){
             if (newSquareSpot == Chessboard.getEmptySquare(figureToMove.rowPosition, 6)){
                 figureToMove = Chessboard.getArrayBoard()[figureToMove.rowPosition][7][1];
                 movingThePiece(Chessboard.getEmptySquare(figureToMove.rowPosition, 5));
             } else if (newSquareSpot == Chessboard.getEmptySquare(figureToMove.rowPosition, 2)) {
                 figureToMove = Chessboard.getArrayBoard()[figureToMove.rowPosition][0][1];
                 movingThePiece(Chessboard.getEmptySquare(figureToMove.rowPosition, 3));
             }
         }
         figureToMove.pieceFirstMove = false;
         figureToMove.player.pieceAttackingKing = null;

         figureToMove.addMoveRecord(figureToMove, newSquareSpot);
//         System.out.println(figureToMove.moveRecordOfPiece);
//         System.out.println(figureToMove.player.stringPlayerColor + " " +figureToMove.player.movesRecord);
    }

     static void discardingThePiece(Piece newSquareSpot){
        Chessboard.arrayDiscardedPieces.add(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);
        Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].player.playerPieces.
                 remove(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);

        newSquareSpot.emptyPiecePanel.removeAll();

    }


    public static void makeCleanMove(Piece newSquareSpot){
        movingThePiece(newSquareSpot);

//        figureToMove.addMoveRecord(newSquareSpot,figureToMove);
//        System.out.println(figureToMove.moveRecordOfPiece);

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
