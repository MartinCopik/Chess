import javax.swing.*;
import java.awt.*;


public class Queen extends Piece {

    public Queen(Color queenColor, ImageIcon queenImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(queenColor, queenImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(queenImage);
    }
    public void queenMoveUP(Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (impossibleMove(row, this.getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    public void queenMoveDown(Chessboard chessboard){
        for (int row = getRowPosition()+1; row<= 7; row++){
            if (impossibleMove(row, this.getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    public void queenMoveLeft(Chessboard chessboard){
        for (int colum = getColumnPosition()-1; colum>= 0; colum--){
            if (impossibleMove(this.getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveRight(Chessboard chessboard){
        for (int colum = getColumnPosition()+1; colum<= 7; colum++){
            if (impossibleMove(this.getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyUpLeft(Chessboard chessboard){
        int row = this.getRowPosition();
        int colum = this.getColumnPosition();
        while (row >= 0 || colum >= 0){
            row--;
            colum--;
            if (impossibleMove(row, colum, chessboard)){
                break;
            }
        }
    }

    public void queenMoveDiagonallyDownLeft(Chessboard chessboard){
        int row = this.getRowPosition();
        int colum = this.getColumnPosition();
        while (row >= 7 || colum >= 0){
            row++;
            colum--;
            if (impossibleMove(row, colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyUpRight(Chessboard chessboard){
        int row = this.getRowPosition();
        int colum = this.getColumnPosition();
        while (row >= 0 || colum <= 7){
            row--;
            colum++;
            if (impossibleMove(row, colum, chessboard)){
                break;
            }
        }
    }
    public void queenMoveDiagonallyDownRight(Chessboard chessboard){
        int row = this.getRowPosition();
        int colum = this.getColumnPosition();
        while (row <= 7 || colum <= 7){
            row++;
            colum++;
            if (impossibleMove(row, colum, chessboard)){
                break;
            }
        }
    }

    @Override
    public void showMovePossibilities(Chessboard chessboard) {

        queenMoveUP(chessboard);
        queenMoveDown(chessboard);
        queenMoveLeft(chessboard);
        queenMoveRight(chessboard);
        queenMoveDiagonallyUpLeft(chessboard);
        queenMoveDiagonallyUpRight(chessboard);
        queenMoveDiagonallyDownLeft(chessboard);
        queenMoveDiagonallyDownRight(chessboard);
    }

}