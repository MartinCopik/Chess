import java.awt.*;

public class Pawn extends ChessPiece{


    public Pawn(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void chessPieceMovePossibilities(Chessboard chessboard) {
        checkColorOfPawn(chessboard);
    }

    private void checkColorOfPawn(Chessboard chessboard){
        if (getChessPieceColor().equals(Color.BLACK)){
            blackPawnMoves(getPieceFirstMove(), chessboard);
        }else whitePawnMoves(getPieceFirstMove(), chessboard);
    }

    private void blackPawnMoves(Boolean pieceFirstMove, Chessboard chessboard){
        pawnMoveDown(pieceFirstMove, chessboard);
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

    private void pawnMoveDown(boolean pieceFirstMove, Chessboard chessboard){
        for (int row = getRowPosition()+1; row <= getRowPosition()+2; row++){
            if (impossibleMove(chessboard, row, getColumnPosition())){
                break;
            }
            if (!pieceFirstMove){
                break;
            }
        }
    }


    private void whitePawnMoves(Boolean pieceFirstMove, Chessboard chessboard){
        pawnMoveUp(pieceFirstMove, chessboard);
        whitePawnAttacks(chessboard);
    }

    private void whitePawnAttacks(Chessboard chessboard){
        pawnMoveDiagonallyUpLeft(chessboard);
        pawnMoveDiagonallyUpRight(chessboard);
    }

    private void pawnMoveUp(boolean pieceFirstMove, Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= getRowPosition()-2; row--){
            if (impossibleMove(chessboard, row, getColumnPosition())){
                break;
            }
            if (!pieceFirstMove){
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

    private void possibleAttackMove( Chessboard chessboard, int rowToCheck, int columToCheck) {
        if (Move.isOutOfBorder(rowToCheck, columToCheck, chessboard)){
            return;
        }
        if (Move.positionIsTaken(rowToCheck, columToCheck, chessboard) && Move.pieceIsAttacking(this, rowToCheck, columToCheck, chessboard)) {
            chessboard.getArrayBoard()[rowToCheck][columToCheck].markTheSquareForAttack();
        }
    }

    private boolean impossibleMove(Chessboard chessboard, int rowToCheck, int columnToCheck) {
        if (Move.isOutOfBorder(rowToCheck, columnToCheck, chessboard)){
            return true;
        }
        if (!Move.positionIsTaken(rowToCheck, columnToCheck, chessboard)){
            chessboard.getArrayBoard()[rowToCheck][columnToCheck].markTheSquareForMove();
            return false;
        }
        return true;
    }
}
