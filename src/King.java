import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    Piece castledKing;
    Player castlingPlayer;
    Player attackingPlayer;

    public King(Player player, Color kingColor, ImageIcon kingImage, int rowPosition, int columPosition) {
        super(player, kingColor, kingImage, rowPosition, columPosition);
        super.pieceImageIcon = kingImage;
    }
    boolean kingIsInCheck;

    boolean kingIsInCheck(){
        if (EmptyPiece.isSquareUnderAttack(this.rowPosition, this.columPosition, setAttackingColor())){
            return kingIsInCheck = true;
        }
        return kingIsInCheck = false;
    }
    boolean cleanMoveForKing(int rowToCheck, int columToCheck){
        setAttackingColor();
        if (!isOutOfBorder(rowToCheck, columToCheck)){
            Chessboard.arrayBoard[this.rowPosition][this.columPosition][1] = null;
            if (this.pieceMove){
                setAttackedSquares(attackingPlayer);
            }
            if (!EmptyPiece.isSquareUnderAttack(rowToCheck, columToCheck, setAttackingColor())){
                Chessboard.arrayBoard[this.rowPosition][this.columPosition][1] = this;
                return true;
            }
            Chessboard.arrayBoard[this.rowPosition][this.columPosition][1] = this;
        }
        return false;
    }

    Color setAttackingColor(){
        if (this.pieceColor.equals(Color.WHITE)){
            castlingPlayer = Chessboard.whitePlayer;
            attackingPlayer = Chessboard.blackPlayer;
            return Color.BLACK;
        }else {
            castlingPlayer = Chessboard.blackPlayer;
            attackingPlayer = Chessboard.whitePlayer;
            return Color.WHITE;
        }
    }
    void setCastling(){
        possibleSmallCastling(setAttackingColor());
        possibleBigCastling(setAttackingColor());
    }

    boolean possibleSmallCastling(Color attackingColor){
        if (this.pieceFirstMove && castlingPlayer.rightRook.pieceFirstMove){

            if (!positionIsTaken(this.rowPosition, this.columPosition+1)
                    && !positionIsTaken(this.rowPosition, this.columPosition+2)){

                if (!EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition+1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition+2, attackingColor)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(this.rowPosition, this.columPosition+2));
                    return true;
                }
            }
        }return false;
    }
    boolean possibleBigCastling(Color attackingColor){
        if (this.pieceFirstMove && castlingPlayer.leftRook.pieceFirstMove){

            if (!positionIsTaken(this.rowPosition, this.columPosition-1)
                    && !positionIsTaken(this.rowPosition, this.columPosition-2)
                    && !positionIsTaken(this.rowPosition, this.columPosition-3)){

                if (!EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition-1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.rowPosition,this.columPosition-2, attackingColor)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(this.rowPosition, this.columPosition-2));
                    return true;
                }
            }
        }return false;
    }

    void kingMoveDiagonallyUpLeft(){
        if (cleanMoveForKing(rowPosition-1, columPosition-1)){
            impossibleMove(player,rowPosition-1, columPosition-1);
        }
    }

    void kingMoveDiagonallyUpRight(){
        if (cleanMoveForKing(rowPosition-1,columPosition+1)){
            impossibleMove(player,rowPosition-1,columPosition+1);
        }
    }
    void kingMoveDiagonallyDownLeft(){
        if (cleanMoveForKing(rowPosition+1,columPosition-1)){
            impossibleMove(player,rowPosition+1, columPosition-1);
        }
    }
    void kingMoveDiagonallyDownRight(){
        if (cleanMoveForKing(rowPosition+1,columPosition+1)){
            impossibleMove(player,rowPosition+1, columPosition+1);
        }
    }

    void kingMoveUp(){
        if (cleanMoveForKing(rowPosition-1,columPosition)){
            impossibleMove(player,rowPosition-1, columPosition);
        }
    }

    void kingMoveDown(){
        if (cleanMoveForKing(rowPosition+1, columPosition)){
            impossibleMove(player,rowPosition+1, columPosition);
        }
    }
    void kingMoveLeft(){
        if (cleanMoveForKing(rowPosition,columPosition-1)){
            impossibleMove(player,rowPosition, columPosition-1);
        }
    }
    void kingMoveRight(){
        if (cleanMoveForKing(rowPosition, columPosition+1)){
            impossibleMove(player,rowPosition, columPosition+1);
        }
    }

    @Override
    public void showMovePossibilities(){

        kingMoveUp();
        kingMoveDown();
        kingMoveRight();
        kingMoveLeft();
        kingMoveDiagonallyUpLeft();
        kingMoveDiagonallyUpRight();
        kingMoveDiagonallyDownLeft();
        kingMoveDiagonallyDownRight();

        if (this.pieceMove && !this.kingIsInCheck ){
            setCastling();
        }
    }
}
