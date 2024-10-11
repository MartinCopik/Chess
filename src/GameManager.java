public class GameManager {

    static boolean checkOfGameManager = false;

    public static boolean checkGameStatus(Player playerOnTurn){
        if (!playerOnTurn.getKing().kingIsInCheck()){
            gameOver(playerOnTurn, "DRAW");
        }else {
            gameOver(playerOnTurn, "CHECKMATE");
        }
        return false;
    }

    static void gameOver(Player playerOnTurn, String outcome){
        checkOfGameManager = true;
        playerOnTurn.getMovePossibilities().clear();
        playerOnTurn.setAttackedSquares(playerOnTurn);
//        Piece.setAttackedSquares(playerOnTurn);
        if (playerOnTurn.getMovePossibilities().isEmpty()){
            new EndingScreen(playerOnTurn.getStringPlayerColor() + outcome);
        }else {
            checkOfGameManager = false;
            EmptyPiece.attackedSquares.clear();
            playerOnTurn.setAttackedSquares(Chessboard.getAttackingPlayer(playerOnTurn));
//            Piece.setAttackedSquares(Chessboard.getAttackingPlayer(playerOnTurn));
        }
    }
}
