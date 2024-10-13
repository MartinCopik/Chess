import javax.swing.*;
import java.awt.*;

public class King extends Piece {
    private Player castlingPlayer;
    private Player attackingPlayer;
    private boolean kingIsInCheck;

    public King(Player player, Color kingColor, ImageIcon kingImage, int RowPosition, int ColumPosition) {
        super(player, kingColor, kingImage, RowPosition, ColumPosition);
        super.setPieceImageIcon(kingImage);
    }

    public Player getCastlingPlayer() {
        return castlingPlayer;
    }
    public void setCastlingPlayer(Player castlingPlayer) {
        this.castlingPlayer = castlingPlayer;
    }
    public Player getAttackingPlayer() {
        return attackingPlayer;
    }
    public void setAttackingPlayer(Player attackingPlayer) {
        this.attackingPlayer = attackingPlayer;
    }
    public boolean getKingIsInCheck() {
        return kingIsInCheck;
    }
    public void setKingIsInCheck(boolean kingIsInCheck) {
        this.kingIsInCheck = kingIsInCheck;
    }
    public boolean kingIsInCheck(){
        if (Chessboard.getAttackingPlayer(getPlayer()).isSquareUnderAttack(getRowPosition(),getColumPosition(),setAttackingColor())){
            setKingIsInCheck(true);
            return getKingIsInCheck();
        }
        setKingIsInCheck(false);
        return getKingIsInCheck();
    }
    boolean cleanMoveForKing(int rowToCheck, int columToCheck){
        setAttackingColor();
        if (!isOutOfBorder(rowToCheck, columToCheck)){
            if (!getAttackingPlayer().isSquareUnderAttack(rowToCheck, columToCheck, setAttackingColor())){
                return true;
            }
        }
        return false;
    }

    Color setAttackingColor(){
        if (this.getPieceColor().equals(Color.WHITE)){
            setCastlingPlayer(Chessboard.getWhitePlayer());
            setAttackingPlayer(Chessboard.getBlackPlayer());
            return Color.BLACK;
        }else {
            setCastlingPlayer(Chessboard.getBlackPlayer());
            setAttackingPlayer(Chessboard.getWhitePlayer());
            return Color.WHITE;
        }
    }
    void setCastling(){
        possibleSmallCastling(setAttackingColor());
        possibleBigCastling(setAttackingColor());
    }

    boolean possibleSmallCastling(Color attackingColor){
        if (this.getPieceFirstMove() && getCastlingPlayer().getRightRook().getPieceFirstMove()){

            if (!positionIsTaken(this.getRowPosition(), this.getColumPosition()+1)
                    && !positionIsTaken(this.getRowPosition(), this.getColumPosition()+2)){

                if (!getPlayer().isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()+1, attackingColor)
                        && !getPlayer().isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()+2, attackingColor)){
                    Chessboard.getEmptySquare(this.getRowPosition(), this.getColumPosition()+2).markTheSquareForMove();
                    return true;
                }
            }
        }return false;
    }
    boolean possibleBigCastling(Color attackingColor){
        if (this.getPieceFirstMove() && getCastlingPlayer().getLeftRook().getPieceFirstMove()){

            if (!positionIsTaken(this.getRowPosition(), this.getColumPosition()-1)
                    && !positionIsTaken(this.getRowPosition(), this.getColumPosition()-2)
                    && !positionIsTaken(this.getRowPosition(), this.getColumPosition()-3)){

                if (!getPlayer().isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()-1, attackingColor)
                        && !getPlayer().isSquareUnderAttack(this.getRowPosition(),this.getColumPosition()-2, attackingColor)){
                    Chessboard.getEmptySquare(this.getRowPosition(), this.getColumPosition()-2).markTheSquareForMove();
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

        if (this.getPieceMove() && !this.getKingIsInCheck()){
            setCastling();
        }
    }
}
