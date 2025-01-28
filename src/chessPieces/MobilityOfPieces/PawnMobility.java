package chessPieces.MobilityOfPieces;

import chessGame.ChessPieceMovement;
import chessGame.Chessboard;
import chessPieces.Pawn;

import java.awt.*;

public interface PawnMobility {

    default void checkColorOfPawn(Pawn pawn, Chessboard chessboard){
        if (pawn.getChessPieceColor().equals(Color.BLACK)){
            blackPawnMoves(pawn, chessboard);
        }else whitePawnMoves(pawn, chessboard);
    }

     default void blackPawnMoves(Pawn pawn, Chessboard chessboard){
        pawnMoveDown(pawn, chessboard);
        blackPawnAttacks(pawn, chessboard);
    }

     default void blackPawnAttacks(Pawn pawn, Chessboard chessboard){
        pawnMoveDiagonallyDownLeft(pawn, chessboard);
        pawnMoveDiagonallyDownRight(pawn, chessboard);
    }

     default void pawnMoveDiagonallyDownLeft(Pawn pawn, Chessboard chessboard){
        possibleAttackMove(pawn, chessboard,pawn.getRowPosition()+1, pawn.getColumnPosition()-1);
    }

     default void pawnMoveDiagonallyDownRight(Pawn pawn, Chessboard chessboard){
        possibleAttackMove(pawn, chessboard,pawn.getRowPosition()+1, pawn.getColumnPosition()+1);
    }

     default void pawnMoveDown(Pawn pawn, Chessboard chessboard){
        for (int row = pawn.getRowPosition()+1; row <= pawn.getRowPosition()+2; row++){
            if (possibleBasicMove(pawn, chessboard, row, pawn.getColumnPosition())){
                break;
            }
        }
    }
     default boolean possibleBasicMove(Pawn pawn, Chessboard chessboard, int row, int column){
        return isMoveValid(pawn, chessboard, row, column) || !pawn.getPieceFirstMove();
    }

     default void whitePawnMoves(Pawn pawn, Chessboard chessboard){
        pawnMoveUp(pawn, chessboard);
        whitePawnAttacks(pawn, chessboard);
    }

     default void whitePawnAttacks(Pawn pawn, Chessboard chessboard){
        pawnMoveDiagonallyUpLeft(pawn, chessboard);
        pawnMoveDiagonallyUpRight(pawn, chessboard);
    }

     default void pawnMoveUp(Pawn pawn, Chessboard chessboard){
        for (int row = pawn.getRowPosition()-1; row >= pawn.getRowPosition()-2; row--){
            if (possibleBasicMove(pawn, chessboard, row, pawn.getColumnPosition())){
                break;
            }
        }
    }

     default void pawnMoveDiagonallyUpLeft(Pawn pawn, Chessboard chessboard){
        possibleAttackMove(pawn, chessboard,pawn.getRowPosition()-1,pawn.getColumnPosition()-1);
    }

     default void pawnMoveDiagonallyUpRight(Pawn pawn, Chessboard chessboard){
        possibleAttackMove(pawn, chessboard,pawn.getRowPosition()-1,pawn.getColumnPosition()+1);
    }

     default void possibleAttackMove( Pawn pawn, Chessboard chessboard, int rowToCheck, int columnToCheck) {
        if (ChessPieceMovement.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
        }else if (ChessPieceMovement.positionIsTaken(rowToCheck, columnToCheck, chessboard)
                && ChessPieceMovement.pieceIsAttacking(pawn, rowToCheck, columnToCheck, chessboard)) {
            if (!chessboard.getGameManager().isValidationInProcess()){
                if (chessboard.getGameManager().invalidMove(pawn, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                    return;
                }
            }
            pawn.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pawn);
        }
    }

     default boolean isMoveValid(Pawn pawn, Chessboard chessboard, int rowToCheck, int columnToCheck) {
        if (ChessPieceMovement.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }else if (!ChessPieceMovement.positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            if (!chessboard.getGameManager().isValidationInProcess()){
                if (chessboard.getGameManager().invalidMove(pawn, chessboard.getArrayBoard()[rowToCheck][columnToCheck])){
                    return false;
                }
            }
            pawn.getChessPieceMovementMap().put(chessboard.getArrayBoard()[rowToCheck][columnToCheck], pawn);
            return false;
        }
        return true;
    }
}
