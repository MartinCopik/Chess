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
            return true;
        } else if (moveCounter%2 == 1 && pieceToMakeMove.pieceColor.equals(Color.BLACK)){
            return true;
        }
        return false;
    }

    private static void movingThePiece(Piece newSquareSpot){
        Chessboard.arrayBoard[figureToMove.getRowPosition()][figureToMove.getColumPosition()][1] = null;
        Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1] = figureToMove;
        newSquareSpot.emptyPiecePanel.add(figureToMove.pieceLabel);

        figureToMove.pieceFirstMove = false;
    }

    private static void discardingThePiece(Piece newSquareSpot){
        Chessboard.arrayDiscardedPieces.add(Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1]);
        Chessboard.arrayBoard[newSquareSpot.getRowPosition()][newSquareSpot.getColumPosition()][1] = null;
        newSquareSpot.emptyPiecePanel.removeAll();

    }


    public static void makeCleanMove(Piece newSquareSpot){
        movingThePiece(newSquareSpot);

        moveCounter++;
//        figureToMove.pieceFirstMove = false;
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
