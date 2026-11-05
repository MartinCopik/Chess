package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;
import chessGame.PieceType;
import chessGame.PieceColor;

public class Knight extends ChessPiece {


    public Knight(PieceColor chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        knightMoveUpShortLeft(chessboard);
        knightMoveUpShortRight(chessboard);
        knightMoveUpLongLeft(chessboard);
        knightMoveUpLongRight(chessboard);
        knightMoveDownShortLeft(chessboard);
        knightMoveDownShortRight(chessboard);
        knightMoveDownLongLeft(chessboard);
        knightMoveDownLongRight(chessboard);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    private  void knightMoveUpShortLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()-2, chessboard);
    }
    private  void knightMoveUpShortRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-1, getColumnPosition()+2, chessboard);
    }
    private  void knightMoveUpLongLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-2, getColumnPosition()-1, chessboard);
    }
    private  void knightMoveUpLongRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()-2, getColumnPosition()+1, chessboard);
    }
    private  void knightMoveDownShortLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()-2, chessboard);
    }
    private  void knightMoveDownShortRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+1, getColumnPosition()+2, chessboard);
    }
    private  void knightMoveDownLongLeft(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+2, getColumnPosition()-1, chessboard);
    }
    private  void knightMoveDownLongRight(Chessboard chessboard){
        ChessPieceMovement.movePossibility(this, getRowPosition()+2, getColumnPosition()+1, chessboard);
    }
}
