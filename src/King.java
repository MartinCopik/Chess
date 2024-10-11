import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    Piece castledKing;
    Player castlingPlayer;
    Player attackingPlayer;

    public King(Player player, Color kingColor, ImageIcon kingImage, int getRowPosition, int getColumPosition) {
        super(player, kingColor, kingImage, getRowPosition, getColumPosition);
        super.setPieceImageIcon(kingImage);
    }
    boolean kingIsInCheck;

    boolean kingIsInCheck(){
        if (EmptyPiece.isSquareUnderAttack(this.getRowPosition(), this.getColumPosition(), setAttackingColor())){
            return kingIsInCheck = true;
        }
        return kingIsInCheck = false;
    }
    boolean cleanMoveForKing(int rowToCheck, int columToCheck){
        setAttackingColor();
        if (!isOutOfBorder(rowToCheck, columToCheck)){
            Chessboard.arrayBoard[this.getRowPosition()][this.getColumPosition()][1] = null;
            if (this.getPieceMove()){
                getPlayer().setAttackedSquares(attackingPlayer);
            }
            if (!EmptyPiece.isSquareUnderAttack(rowToCheck, columToCheck, setAttackingColor())){
                Chessboard.arrayBoard[this.getRowPosition()][this.getColumPosition()][1] = this;
                return true;
            }
            Chessboard.arrayBoard[this.getRowPosition()][this.getColumPosition()][1] = this;
        }
        return false;
    }

    Color setAttackingColor(){
        if (this.pieceColor.equals(Color.WHITE)){
            castlingPlayer = Chessboard.getWhitePlayer();
            attackingPlayer = Chessboard.getBlackPlayer();
            return Color.BLACK;
        }else {
            castlingPlayer = Chessboard.getBlackPlayer();
            attackingPlayer = Chessboard.getWhitePlayer();
            return Color.WHITE;
        }
    }
    void setCastling(){
        possibleSmallCastling(setAttackingColor());
        possibleBigCastling(setAttackingColor());
    }

    boolean possibleSmallCastling(Color attackingColor){
        if (this.getPieceFirstMove() && castlingPlayer.getRightRook().getPieceFirstMove()){

            if (!positionIsTaken(this.getRowPosition(), this.getColumPosition()+1)
                    && !positionIsTaken(this.getRowPosition(), this.getColumPosition()+2)){

                if (!EmptyPiece.isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()+1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()+2, attackingColor)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(this.getRowPosition(), this.getColumPosition()+2));
                    return true;
                }
            }
        }return false;
    }
    boolean possibleBigCastling(Color attackingColor){
        if (this.getPieceFirstMove() && castlingPlayer.getLeftRook().getPieceFirstMove()){

            if (!positionIsTaken(this.getRowPosition(), this.getColumPosition()-1)
                    && !positionIsTaken(this.getRowPosition(), this.getColumPosition()-2)
                    && !positionIsTaken(this.getRowPosition(), this.getColumPosition()-3)){

                if (!EmptyPiece.isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()-1, attackingColor)
                        && !EmptyPiece.isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()-2, attackingColor)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(this.getRowPosition(), this.getColumPosition()-2));
                    return true;
                }
            }
        }return false;
    }

    void kingMoveDiagonallyUpLeft(){
        if (cleanMoveForKing(getRowPosition()-1, getColumPosition()-1)){
            impossibleMove(getPlayer(),getRowPosition()-1, getColumPosition()-1);
        }
    }

    void kingMoveDiagonallyUpRight(){
        if (cleanMoveForKing(getRowPosition()-1,getColumPosition()+1)){
            impossibleMove(getPlayer(),getRowPosition()-1,getColumPosition()+1);
        }
    }
    void kingMoveDiagonallyDownLeft(){
        if (cleanMoveForKing(getRowPosition()+1,getColumPosition()-1)){
            impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition()-1);
        }
    }
    void kingMoveDiagonallyDownRight(){
        if (cleanMoveForKing(getRowPosition()+1,getColumPosition()+1)){
            impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition()+1);
        }
    }

    void kingMoveUp(){
        if (cleanMoveForKing(getRowPosition()-1,getColumPosition())){
            impossibleMove(getPlayer(),getRowPosition()-1, getColumPosition());
        }
    }

    void kingMoveDown(){
        if (cleanMoveForKing(getRowPosition()+1, getColumPosition())){
            impossibleMove(getPlayer(),getRowPosition()+1, getColumPosition());
        }
    }
    void kingMoveLeft(){
        if (cleanMoveForKing(getRowPosition(),getColumPosition()-1)){
            impossibleMove(getPlayer(),getRowPosition(), getColumPosition()-1);
        }
    }
    void kingMoveRight(){
        if (cleanMoveForKing(getRowPosition(), getColumPosition()+1)){
            impossibleMove(getPlayer(),getRowPosition(), getColumPosition()+1);
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

        if (this.getPieceMove() && !this.kingIsInCheck ){
            setCastling();
        }
    }
}
