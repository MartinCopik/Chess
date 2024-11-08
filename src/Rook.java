import java.awt.*;

public class Rook extends ChessPiece {

    public Rook(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void chessPieceMovePossibilities(Chessboard chessboard) {
        rookMoveUP(chessboard);
        rookMoveDown(chessboard);
        rookMoveLeft(chessboard);
        rookMoveRight(chessboard);
    }

    private void rookMoveUP(Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (Move.isMoveValid(this, row, getColumnPosition(), chessboard)){
                break;
            }
        }
    }

    private void rookMoveDown(Chessboard chessboard){
        for (int row = getRowPosition()+1; row<= 7; row++){
            if (Move.isMoveValid(this, row, getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    private void rookMoveLeft(Chessboard chessboard){
        for (int colum = getColumnPosition()-1; colum>= 0; colum--){
            if (Move.isMoveValid(this, getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    private void rookMoveRight(Chessboard chessboard){
        for (int colum = getColumnPosition()+1; colum<= 7; colum++){
            if (Move.isMoveValid(this, getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }


}
