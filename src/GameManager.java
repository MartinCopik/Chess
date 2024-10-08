public class GameManager {

    static boolean checkOfGameManager = false;

    public static boolean checkGameStatus(Player playerOnTurn){
        if (!playerOnTurn.king.kingIsInCheck()){
            checkForPat(playerOnTurn);
        }
        return false;
    }

    static void checkForPat(Player playerOnTurn){
        checkOfGameManager = true;
        playerOnTurn.movePossibilities.clear();
        Piece.setAttackedSquares(playerOnTurn);
        if (playerOnTurn.movePossibilities.isEmpty()){
            new EndingScreen(playerOnTurn.stringPlayerColor + " Player has been given a draw by Pat");
        }
        checkOfGameManager = false;
    }

    void checkRepititionOfMoves(Player playerOnTurn){
        if (playerOnTurn.movesRecord.size() > 5 && Chessboard.getAttackingPlayer(playerOnTurn).movesRecord.size() > 5){
            
        }
    }


}
