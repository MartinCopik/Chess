import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(Color knightColor, ImageIcon knightImage, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece){
        super(knightColor,knightImage, rowPosition, columPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(knightImage);
    }
    public void knightMoveUpShortLeft(Chessboard chessboard){
        impossibleMove(getRowPosition()-1, getColumPosition()-2, chessboard);
    }
    public void knightMoveUpShortRight(Chessboard chessboard){
        impossibleMove(getRowPosition()-1, getColumPosition()+2, chessboard);
    }
    public void knightMoveUpLongLeft(Chessboard chessboard){
        impossibleMove(getRowPosition()-2, getColumPosition()-1, chessboard);
    }
    public void knightMoveUpLongRight(Chessboard chessboard){
        impossibleMove(getRowPosition()-2, getColumPosition()+1, chessboard);
    }
    public void knightMoveDownShortLeft(Chessboard chessboard){
        impossibleMove(getRowPosition()+1, getColumPosition()-2, chessboard);
    }
    public void knightMoveDownShortRight(Chessboard chessboard){
        impossibleMove(getRowPosition()+1, getColumPosition()+2, chessboard);
    }
    public void knightMoveDownLongLeft(Chessboard chessboard){
        impossibleMove(getRowPosition()+2, getColumPosition()-1, chessboard);
    }
    public void knightMoveDownLongRight(Chessboard chessboard){
        impossibleMove(getRowPosition()+2, getColumPosition()+1, chessboard);
    }


    @Override
    public void showMovePossibilities(Chessboard chessboard) {
        knightMoveUpShortLeft(chessboard);
        knightMoveUpShortRight(chessboard);
        knightMoveUpLongLeft(chessboard);
        knightMoveUpLongRight(chessboard);

        knightMoveDownShortLeft(chessboard);
        knightMoveDownShortRight(chessboard);
        knightMoveDownLongLeft(chessboard);
        knightMoveDownLongRight(chessboard);
    }
}