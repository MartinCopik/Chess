package chessPieces;

import chessGame.Chessboard;
import chessGame.ChessPieceMovement;
import chessGame.PieceType;
import chessGame.PieceColor;

public class Rook extends ChessPiece {

    public Rook(PieceColor chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovementMap(Chessboard chessboard) {
        rookMoveUP(chessboard);
        rookMoveDown(chessboard);
        rookMoveLeft(chessboard);
        rookMoveRight(chessboard);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    private void rookMoveUP(Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (ChessPieceMovement.movePossibility(this, row, getColumnPosition(), chessboard)) {
                break;
            }
        }
    }

    private void rookMoveDown (Chessboard chessboard){
        for (int row = getRowPosition() + 1; row <= 7; row++) {
            if (ChessPieceMovement.movePossibility(this, row, getColumnPosition(), chessboard)) {
                break;
            }
        }
    }

    private void rookMoveLeft (Chessboard chessboard){
        for (int colum = getColumnPosition() - 1; colum >= 0; colum--) {
            if (ChessPieceMovement.movePossibility(this, getRowPosition(), colum, chessboard)) {
                break;
            }
        }
    }

    private void rookMoveRight (Chessboard chessboard){
        for (int colum = getColumnPosition() + 1; colum <= 7; colum++) {
            if (ChessPieceMovement.movePossibility(this, getRowPosition(), colum, chessboard)) {
                break;
            }
        }
    }

}
