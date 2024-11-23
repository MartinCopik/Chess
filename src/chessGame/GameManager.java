package chessGame;

import chessPieces.ChessPiece;

import java.awt.*;

public class GameManager {

    private int moveCounter;
    private final Chessboard chessboard;

    public GameManager(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    /**
     * method is controlling the right rotation of the players
     * @param chessPiece checking its color
     * @return true if chessPiece has the right color, returns false if not
     */
    public boolean alternationOfPlayers(ChessPiece chessPiece){
        if (moveCounter %2 == 0 && chessPiece.getChessPieceColor().equals(Color.WHITE)){
            return true;
        }else if (moveCounter %2 == 1 && chessPiece.getChessPieceColor().equals(Color.BLACK)){
            return true;
        }
        return false;
    }
    /**
     * calls the controlling method if the click for move was done correctly and increases the moveCounter by one
     */
    public void isItClickedForMove(ChessSquare clickedChessSquare){
        if (ChessPieceMovement.canPieceMakeThisMove(clickedChessSquare, chessboard)){
            moveCounter++;
        }
    }
}
