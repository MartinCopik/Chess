package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;

import java.awt.*;

public class Pawn extends ChessPiece {


    public Pawn(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        checkColorOfPawn(chessboard);
    }

    private void checkColorOfPawn(Chessboard chessboard){
        if (getChessPieceColor().equals(Color.BLACK)){
            blackPawnMoves(chessboard);
        }else whitePawnMoves(chessboard);
    }

    private void blackPawnMoves(Chessboard chessboard){
        pawnMoveDown(chessboard);
        blackPawnAttacks(chessboard);
    }

    private void blackPawnAttacks(Chessboard chessboard){
        pawnMoveDiagonallyDownLeft(chessboard);
        pawnMoveDiagonallyDownRight(chessboard);
    }

    private void pawnMoveDiagonallyDownLeft(Chessboard chessboard){
        possibleAttackMove(chessboard,getRowPosition()+1, getColumnPosition()-1);
    }

    private void pawnMoveDiagonallyDownRight(Chessboard chessboard){
        possibleAttackMove(chessboard,getRowPosition()+1, getColumnPosition()+1);
    }

    private void pawnMoveDown(Chessboard chessboard){
        for (int row = getRowPosition()+1; row <= getRowPosition()+2; row++){
            if (possibleBasicMove(chessboard, row, getColumnPosition())){
                break;
            }
        }
    }
    private boolean possibleBasicMove(Chessboard chessboard, int row, int column){
        return isMoveValid(chessboard, row, column) || !getPieceFirstMove();
    }

    private void whitePawnMoves(Chessboard chessboard){
        pawnMoveUp(chessboard);
        whitePawnAttacks(chessboard);
    }

    private void whitePawnAttacks(Chessboard chessboard){
        pawnMoveDiagonallyUpLeft(chessboard);
        pawnMoveDiagonallyUpRight(chessboard);
    }

    private void pawnMoveUp(Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= getRowPosition()-2; row--){
            if (possibleBasicMove(chessboard, row, getColumnPosition())){
                break;
            }
        }
    }

    private void pawnMoveDiagonallyUpLeft(Chessboard chessboard){
        possibleAttackMove(chessboard,getRowPosition()-1,getColumnPosition()-1);
    }

    private void pawnMoveDiagonallyUpRight(Chessboard chessboard){
        possibleAttackMove(chessboard,getRowPosition()-1,getColumnPosition()+1);
    }

    private void possibleAttackMove( Chessboard chessboard, int rowToCheck, int columnToCheck) {
        if (ChessPieceMovement.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
        }else if (ChessPieceMovement.positionIsTaken(rowToCheck, columnToCheck, chessboard)
                && ChessPieceMovement.pieceIsAttacking(this, rowToCheck, columnToCheck, chessboard)) {
            if (!chessboard.getGameManager().isValidationInProcess()){
                if (chessboard.getGameManager().invalidMove(this, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                    return;
                }
            }
            getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], this);
        }
    }

    private boolean isMoveValid(Chessboard chessboard, int rowToCheck, int columnToCheck) {
        if (ChessPieceMovement.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }else if (!ChessPieceMovement.positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (!chessboard.getGameManager().isValidationInProcess()){
                if (chessboard.getGameManager().invalidMove(this, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                    return false;
                }
            }
            getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], this);
            return false;
        }
        return true;
    }
}
