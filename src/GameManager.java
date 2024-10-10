public class GameManager {

    static boolean checkOfGameManager = false;

    public static boolean checkGameStatus(Player playerOnTurn){
        if (!playerOnTurn.king.kingIsInCheck()){
            gameOver(playerOnTurn, "DRAW");
        }else {
            gameOver(playerOnTurn, "CHECKMATE");
        }
        return false;
    }

    static void gameOver(Player playerOnTurn, String outcome){
        checkOfGameManager = true;
        playerOnTurn.movePossibilities.clear();
        Piece.setAttackedSquares(playerOnTurn);
        if (playerOnTurn.movePossibilities.isEmpty()){
            new EndingScreen(playerOnTurn.stringPlayerColor + outcome);
        }else {
            checkOfGameManager = false;
            EmptyPiece.attackedSquares.clear();
            Piece.setAttackedSquares(Chessboard.getAttackingPlayer(playerOnTurn));
        }
    }
}
