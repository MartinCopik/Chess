import javax.swing.*;
import java.awt.*;

public class Bishop extends Piece {

    public Bishop(Color bishopColor, ImageIcon bishopImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(bishopColor, bishopImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(bishopImage);
    }

    public void bishopMoveDiagonallyUpLeft(Chessboard chessboard){
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

    public void bishopMoveDiagonallyDownLeft(Chessboard chessboard){
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
    public void bishopMoveDiagonallyUpRight(Chessboard chessboard){
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
    public void bishopMoveDiagonallyDownRight(Chessboard chessboard){
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
        bishopMoveDiagonallyUpLeft(chessboard);
        bishopMoveDiagonallyDownLeft(chessboard);
        bishopMoveDiagonallyUpRight(chessboard);
        bishopMoveDiagonallyDownRight(chessboard);
    }
}