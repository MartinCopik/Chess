public class GameManager {

    private static boolean checkOfGameManager = false;

    public static boolean checkGameStatus(Player playerOnTurn){
        if (!playerOnTurn.getKing().kingIsInCheck()){
            gameOver(playerOnTurn, "DRAW");
        }else {
            gameOver(playerOnTurn, "CHECKMATE");
        }
        return false;
    }

    static void gameOver(Player playerOnTurn, String outcome){
        setCheckOfGameManager(true);
        playerOnTurn.getMovePossibilities().clear();
        playerOnTurn.setAttackedSquares();
        if (playerOnTurn.getMovePossibilities().isEmpty()){
            new EndingScreen(playerOnTurn.getStringPlayerColor() + outcome);
        }else {
            setCheckOfGameManager(false);
            playerOnTurn.getAttackedSquares().clear();
            Chessboard.getAttackingPlayer(playerOnTurn).setAttackedSquares();
        }
    }

    public static boolean getCheckOfGameManager() {
        return checkOfGameManager;
    }

    public static void setCheckOfGameManager(boolean checkOfGameManager) {
        GameManager.checkOfGameManager = checkOfGameManager;
    }
}
