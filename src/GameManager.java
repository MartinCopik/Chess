public class GameManager {

    static boolean checkOfGameManager = false;

    public static boolean checkGameStatus(Player playerOnTurn){
        if (!playerOnTurn.king.kingIsInCheck()){
            checkForDraw(playerOnTurn);
        }
        return false;
    }

    static void checkForDraw(Player playerOnTurn){
        checkOfGameManager = true;
        EmptyPiece.attackedSquares.clear();
        Piece.setAttackedSquares(playerOnTurn);
        if (EmptyPiece.attackedSquares.isEmpty()){
            System.out.println("draw !!! " + Move.moveCounter);
            System.out.println(playerOnTurn.stringPlayerColor);
        }
        checkOfGameManager = false;
    }


}
