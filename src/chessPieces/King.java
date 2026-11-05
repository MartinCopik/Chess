package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;
import chessGame.PieceType;
import chessGame.PieceColor;

public class King extends ChessPiece {
    public King(PieceColor chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        if (!chessboard.getGameManager().isValidationInProcess()){
            castling(chessboard);
        }

        kingMoveUp(chessboard);
        kingMoveDown(chessboard);
        kingMoveLeft(chessboard);
        kingMoveRight(chessboard);
        kingMoveDiagonallyUpLeft(chessboard);
        kingMoveDiagonallyUpRight(chessboard);
        kingMoveDiagonallyDownLeft(chessboard);
        kingMoveDiagonallyDownRight(chessboard);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    private void kingMoveUp(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition(), chessboard);
    }

    private void kingMoveDown(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition(), chessboard);
    }
    private void kingMoveLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition(), getColumnPosition()-1, chessboard);
    }
    private void kingMoveRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition(), getColumnPosition()+1, chessboard);
    }

    private void kingMoveDiagonallyUpLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()-1, chessboard);
    }

    private void kingMoveDiagonallyUpRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()+1, chessboard);
    }
    private void kingMoveDiagonallyDownLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()-1, chessboard);
    }
    private void kingMoveDiagonallyDownRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()+1, chessboard);
    }

    /**
     * set up possible castling movement and saves it in movement map
     * @param chessboard
     */
    private void castling(Chessboard chessboard){
        int homeRow = getChessPieceColor() == PieceColor.WHITE ? 7 : 0;
        if (getPieceFirstMove() && getRowPosition() == homeRow && getColumnPosition() == 4
                && !chessboard.getGameManager().setActualKingStatus(this)) {
            if (hasCastlingRook(chessboard, 7)){
                    isSmallCastlingPossible(chessboard);
            }
            if (hasCastlingRook(chessboard, 0)){
                if (!ChessPieceMovement.positionIsTaken(this.getRowPosition(), 1, chessboard)){
                    isBigCastlingPossible(chessboard);
                }
            }
        }
    }

    private boolean hasCastlingRook(Chessboard chessboard, int column) {
        ChessPiece rook = chessboard.getArrayBoard()[getRowPosition()][column].getPieceOnSquare();
        // A promoted rook has firstMove=false; restored original rooks keep their saved flag.
        return rook instanceof Rook && rook.getChessPieceColor() == getChessPieceColor()
                && rook.getPieceFirstMove();
    }

    private void isSmallCastlingPossible(Chessboard chessboard){
        for (int column = this.getColumnPosition()+1; column < this.getColumnPosition()+3; column++){
            if (checkCastlingMovement(column, chessboard)){
                break;
            }
        }
    }
    private void isBigCastlingPossible(Chessboard chessboard){
        for (int column = this.getColumnPosition()-1; column > this.getColumnPosition()-3; column--){
            if (checkCastlingMovement(column, chessboard)){
                break;
            }
        }
    }

    /**
     * checks specified position on chessboard
     * @param column column to check
     * @param chessboard
     * @return true if game is in check or specified position is taken
     */
    private boolean checkCastlingMovement(int column, Chessboard chessboard){
        if(ChessPieceMovement.positionIsTaken(
                getRowPosition(),column, chessboard )){
            return true;
        }

        ChessPieceMovement.movePossibility(this, getRowPosition(), column, chessboard);
        return chessboard.getGameManager().isGameCheck();
    }
}
