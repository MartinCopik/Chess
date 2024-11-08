import java.awt.*;

public class Queen extends ChessPiece {

    public Queen(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void chessPieceMovePossibilities(Chessboard chessboard) {
        queenMoveUP(chessboard);
        queenMoveDown(chessboard);
        queenMoveLeft(chessboard);
        queenMoveRight(chessboard);
        queenMoveDiagonallyUpLeft(chessboard);
        queenMoveDiagonallyUpRight(chessboard);
        queenMoveDiagonallyDownLeft(chessboard);
        queenMoveDiagonallyDownRight(chessboard);
    }

    private void queenMoveUP(Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (Move.isMoveValid(this, row, getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    private void queenMoveDown(Chessboard chessboard){
        for (int row = getRowPosition()+1; row<= 7; row++){
            if (Move.isMoveValid(this, row, getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    private void queenMoveLeft(Chessboard chessboard){
        for (int colum = getColumnPosition()-1; colum>= 0; colum--){
            if (Move.isMoveValid(this, getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    private void queenMoveRight(Chessboard chessboard){
        for (int colum = getColumnPosition()+1; colum<= 7; colum++){
            if (Move.isMoveValid(this, getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    private void queenMoveDiagonallyUpLeft(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (Move.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }

    private void queenMoveDiagonallyDownLeft(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (Move.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }
    private void queenMoveDiagonallyUpRight(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (Move.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }
    private void queenMoveDiagonallyDownRight(Chessboard chessboard){
        int row = getRowPosition();
        int colum = getColumnPosition();
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (Move.isMoveValid(this, row, colum, chessboard)){
                break;
            }
        }
    }
}
