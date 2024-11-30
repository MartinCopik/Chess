package chessGame;

import chessPieces.ChessPiece;
import chessPieces.Queen;

import java.awt.*;

public class GameManager {

    private int moveCounter;
    private final Chessboard chessboard;
    private boolean validationInProcess;

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
            setChessPieceMovementMap(chessPiece);
            //validacia tahu
            return true;
        }else if (moveCounter %2 == 1 && chessPiece.getChessPieceColor().equals(Color.BLACK)){
            setChessPieceMovementMap(chessPiece);
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

    public void setAttackingPiecesMovementMap(Color color){
        for (ChessPiece chessPiece : chessboard.getListOfPieces()) {
            if (color.equals(Color.WHITE)){
                if (chessPiece.getChessPieceColor().equals(Color.BLACK)){
                    setChessPieceMovementMap(chessPiece);
                }
            }else {
                if (chessPiece.getChessPieceColor().equals(Color.WHITE)){
                    setChessPieceMovementMap(chessPiece);
                }
            }
        }
    }

    private void setChessPieceMovementMap(ChessPiece chessPiece){
        chessPiece.getChessPieceMovementMap().clear();
        chessPiece.setChessPieceMovementMap(chessboard);
    }

    public boolean isKingAttacked(ChessPiece chessPiece){
        ChessPiece checkedKing;
        boolean check = false;
        if (chessPiece.getChessPieceColor().equals(chessboard.getChessPiecesPackage().getWhiteKing().getChessPieceColor())){
            checkedKing = chessboard.getChessPiecesPackage().getWhiteKing();
        }else {
            checkedKing = chessboard.getChessPiecesPackage().getBlackKing();
        }
        for (ChessPiece piece : chessboard.getListOfPieces()){
            if (!piece.getChessPieceColor().equals(checkedKing.getChessPieceColor())
                    && piece.getChessPieceMovementMap().containsKey(chessboard.getArrayBoard()[checkedKing.getRowPosition()][checkedKing.getColumnPosition()])){
//                System.out.println(chessboard.getArrayBoard()[checkedKing.getRowPosition()][chessPiece.getColumnPosition()] +" " + checkedKing.getRowPosition() + " " + checkedKing.getColumnPosition());
//                if (piece instanceof Queen){
//                    System.out.println(piece.getRowPosition() + " " + piece.getColumnPosition());
//                    System.out.println(piece.getChessPieceMovementMap().keySet());
//                }
//                System.out.println(piece + " " + piece.getChessPieceMovementMap().keySet());
//                check =  piece.getChessPieceMovementMap().containsKey(chessboard.getArrayBoard()[checkedKing.getRowPosition()][checkedKing.getColumnPosition()]);
                check = true;
                System.out.println("from inner if/else " + check);
                return true;
            }
        }
        System.out.println("from outside " + check);
//        return check;
        return false;
    }


    public boolean moveValidation(ChessPiece chessPiece, ChessSquare newSquareSpot){
        ChessPiece pieceOnSquare = newSquareSpot.getPieceOnSquare();
        int rowPosition = chessPiece.getRowPosition();
        int columnPosition = chessPiece.getColumnPosition();
        ChessPieceMovement.makeTheMove(newSquareSpot, chessPiece, chessboard);
        validationInProcess = true;
        setAttackingPiecesMovementMap(chessPiece.getChessPieceColor());
//        isKingAttacked(chessPiece);
        ChessPieceMovement.makeTheMove(chessboard.getArrayBoard()[rowPosition][columnPosition], chessPiece, chessboard);
        newSquareSpot.setPieceOnSquare(pieceOnSquare);
        validationInProcess = false;
        return isKingAttacked(chessPiece);
        //nasetuj figurky opacnej farby
        //otestuj ci v danom rozostaveni bude sach

        //vrat true/false
//        return false;
    }

    public boolean isValidationInProcess() {
        return validationInProcess;
    }
}
