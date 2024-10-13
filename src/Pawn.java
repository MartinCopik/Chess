import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece{

    public Pawn(Player player, Color pawnColor, ImageIcon pawnImage, int rowPosition, int columPosition) {
        super(player, pawnColor,pawnImage, rowPosition, columPosition);
        super.setPieceImageIcon(pawnImage);
    }

    public void readyToBePromoted(){
        setActualPositionOfPiece(this);
        if (getRowPosition() == 0 || getRowPosition() == 7){
            new PromotionWindow(this);
            Move.discardingThePiece(Chessboard.getEmptySquare(this.getRowPosition(), this.getColumPosition()));
            Chessboard.getPanelBoard().repaint();
        }
    }

    public void checkColourOfPawn(Piece pawnToCheck){
        if (pawnToCheck.getPieceColor() == Color.black){
            blackPawnMoves();
        } else {
            whitePawnMoves();
        }
    }
    public void blackPawnMoves(){
        pawnMoveDown();
        blackPawnAttacks();
    }
    public void whitePawnMoves(){
        pawnMoveUp();
        whitePawnAttacks();
    }
    public void blackPawnAttacks(){
        pawnMoveDiagonallyDownLeft();
        pawnMoveDiagonallyDownRight();
    }
    public void whitePawnAttacks(){
        pawnMoveDiagonallyUpLeft();
        pawnMoveDiagonallyUpRight();
    }

    public void pawnMoveUp(){
        if (this.getPieceFirstMove() && !positionIsTaken(this.getRowPosition()-1, this.getColumPosition())){
            impossibleMove(getPlayer(),this.getRowPosition()-2, this.getColumPosition());
        }
        impossibleMove(getPlayer(),this.getRowPosition()-1, this.getColumPosition());
    }

    public void pawnMoveDown(){
        if (this.getPieceFirstMove() && !positionIsTaken(this.getRowPosition()+1, this.getColumPosition())){
            impossibleMove(getPlayer(),this.getRowPosition()+2, this.getColumPosition());
        }
        impossibleMove(getPlayer(),this.getRowPosition()+1, this.getColumPosition());
    }

    public void pawnMoveDiagonallyUpLeft(){
        possibleAttackMove(this.getRowPosition()-1,this.getColumPosition()-1);
    }
    public void pawnMoveDiagonallyUpRight(){
        possibleAttackMove(this.getRowPosition()-1,this.getColumPosition()+1);
    }
    public void pawnMoveDiagonallyDownLeft(){
        possibleAttackMove(this.getRowPosition()+1, this.getColumPosition()-1);
    }
    public void pawnMoveDiagonallyDownRight(){
        possibleAttackMove(this.getRowPosition()+1, this.getColumPosition()+1);
    }
    public void possibleAttackMove(int rowToCheck, int columToCheck) {
        if (isOutOfBorder(rowToCheck, columToCheck)){
            return;
        }
            if (getPlayer().getPieceAttackingKing() != null && Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] == getPlayer().getPieceAttackingKing()){
                if (GameManager.getCheckOfGameManager()){
                    getPlayer().setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
                if (getPlayer().getKing().kingIsInCheck() && this.getPieceMove()){
                    Chessboard.getEmptySquare(rowToCheck, columToCheck).markTheSquareForAttack();
                }
                return;
            }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (this.getPieceMove() && !getPlayer().getKing().kingIsInCheck()) {
                Chessboard.getEmptySquare(rowToCheck, columToCheck).markTheSquareForAttack();
                return;
            }
            if (GameManager.getCheckOfGameManager() && !getPlayer().getKing().getKingIsInCheck()){
                getPlayer().setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            this.getPlayer().arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
//            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
        }
        if (!this.getPieceMove() && !GameManager.getCheckOfGameManager()){
            this.getPlayer().arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
//            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
        }
    }
    @Override
    public boolean impossibleMove(Player player, int rowToCheck, int columToCheck) {
        if (isOutOfBorder(rowToCheck, columToCheck)){
            return true;
        }
        if (this.getPieceMove() && !player.getKing().kingIsInCheck()){
            if (!positionIsTaken(rowToCheck, columToCheck)){
                if (selfCheckOnEmptySquare(this, rowToCheck, columToCheck)){
                    return true;
                }
            }
        }
        if (!positionIsTaken(rowToCheck, columToCheck)){
            if (this.getPieceMove()){
                if (!player.getKing().kingIsInCheck() || stepIntoAttack(rowToCheck, columToCheck, this)){
                    Chessboard.getEmptySquare(rowToCheck, columToCheck).markTheSquareForMove();
                }
            }
            if (GameManager.getCheckOfGameManager()){
                if (!player.getKing().getKingIsInCheck()){
                    player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void showMovePossibilities() {
        checkColourOfPawn(this);
    }
}
