public class GameManager {

    static boolean checkOfGameManager = false;

    public static boolean checkGameStatus(Player playerOnTurn){
        if (!playerOnTurn.king.kingIsInCheck()){
            checkForDraw(playerOnTurn);
        }
        return false;
    }

    static void checkForDraw(Player playerOnTurn){
        if (playerOnTurn.playerPieces.size() < 2 && playerOnTurn.clicked ){
            System.out.println("empty");
        }
        checkOfGameManager = true;
        System.out.println(playerOnTurn.movePossibilities.size());
        playerOnTurn.movePossibilities.clear();
        System.out.println(playerOnTurn.stringPlayerColor);
        Piece.setAttackedSquares(playerOnTurn);
        if (playerOnTurn.movePossibilities.isEmpty()){
            if (playerOnTurn.playerPieces.size() < 2 ){
                System.out.println("now");
            }
            System.out.println("draw !!! " + Move.moveCounter);
            System.out.println(playerOnTurn.stringPlayerColor);
        }
        checkOfGameManager = false;
    }


}
