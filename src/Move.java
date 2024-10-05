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
            EmptyPiece.attackedSquares.clear();
            Piece.setAttackedSquares(Chessboard.blackPlayer);
            pieceToMakeMove.player.king.kingIsInCheck();
            return true;
        } else if (moveCounter%2 == 1 && pieceToMakeMove.pieceColor.equals(Color.BLACK)){
            if (Pawn.promoted){
                System.out.println("now");
            }
            EmptyPiece.attackedSquares.clear();
            Piece.setAttackedSquares(Chessboard.whitePlayer);
            if (pieceToMakeMove.player.king.kingIsInCheck()){
                System.out.println("black king is in check at the start of his move !!!!!!");
            }
            return true;
        }
        return false;
    }

     static void movingThePiece(Piece newSquareSpot){
//        if (figureToMove instanceof Pawn ){
//            System.out.println("spravne ukonci pohyb");
//            return;
//        }
         Chessboard.arrayBoard[figureToMove.getRowPosition()][figureToMove.getColumPosition()][1] = null;
         Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1] = figureToMove;
         newSquareSpot.emptyPiecePanel.add(figureToMove.pieceLabel);

         figureToMove.setActualPositionOfPiece(figureToMove);
         if (figureToMove.checkIfCheckIsMade(figureToMove)){
             //toto sa zavola prakticky vzdy, cize vzdy sa ukazu figurky hraca ktory prave urobil tah

         }

         if (figureToMove instanceof Pawn){
            ((Pawn) figureToMove).readyToBePromoted();
         }
         if (figureToMove instanceof King && figureToMove.pieceFirstMove){
             if (newSquareSpot == Chessboard.getEmptySquare(figureToMove.rowPosition, 6)){
                 //bolo zakliknute policko na malu rosadu
                 //cize treba zavolat presun figurky na vezu
                 figureToMove = Chessboard.getArrayBoard()[figureToMove.rowPosition][7][1];
                 movingThePiece(Chessboard.getEmptySquare(figureToMove.rowPosition, 5));
             } else if (newSquareSpot == Chessboard.getEmptySquare(figureToMove.rowPosition, 2)) {
                 //the square for big castling was clicked
                 //make move for the left rook
                 figureToMove = Chessboard.getArrayBoard()[figureToMove.rowPosition][0][1];
                 movingThePiece(Chessboard.getEmptySquare(figureToMove.rowPosition, 3));
             }
         }
         figureToMove.pieceFirstMove = false;
    }

     static void discardingThePiece(Piece newSquareSpot){

        if (figureToMove.player.king.kingIsInCheck){
            System.out.println("from discarding the piece king is in check");
        }
        Chessboard.arrayDiscardedPieces.add(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);

//         System.out.println("before change to null " + Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);
//         System.out.println("before change to null " + Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].player.playerPieces);
//         System.out.println("before change to null " + Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].player.specialQueen);

         Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].player.playerPieces.
                 remove(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);


//         System.out.println("after change to null " + Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);
////         System.out.println("after change to null " + Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].player.playerPieces);
//         System.out.println("after change to null " + Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1].player.specialQueen);

         newSquareSpot.emptyPiecePanel.removeAll();

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
