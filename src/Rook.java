import javax.swing.*;
import java.awt.*;


public class Rook extends Piece{

    public Rook(Color rookColor, ImageIcon rookImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece) {
        super(rookColor, rookImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(rookImage);
    }

    public void rookMoveUP(Chessboard chessboard){
        for (int row = getRowPosition()-1; row >= 0 ; row--){
            if (impossibleMove(row, this.getColumnPosition(), chessboard)){
                break;
            }
        }
    }

    public void rookMoveDown(Chessboard chessboard){
        for (int row = getRowPosition()+1; row<= 7; row++){
            if (impossibleMove(row, this.getColumnPosition(), chessboard)){
                break;
            }
        }
    }
    public void rookMoveLeft(Chessboard chessboard){
        for (int colum = getColumnPosition()-1; colum>= 0; colum--){
            if (impossibleMove(this.getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    public void rookMoveRight(Chessboard chessboard){
        for (int colum = getColumnPosition()+1; colum<= 7; colum++){
            if (impossibleMove(this.getRowPosition(), colum, chessboard)){
                break;
            }
        }
    }
    @Override
    public void showMovePossibilities(Chessboard chessboard) {
        rookMoveUP(chessboard);
        rookMoveDown(chessboard);
        rookMoveLeft(chessboard);
        rookMoveRight(chessboard);
    }
}