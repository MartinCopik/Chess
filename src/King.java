import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    public King(Color kingColor, ImageIcon kingImage, int RowPosition, int ColumPosition, int widthOfPiece, int heightOfPiece) {
        super(kingColor, kingImage, RowPosition, ColumPosition, widthOfPiece, heightOfPiece);
        super.setPieceImageIcon(kingImage);
    }
    void kingMoveUp(Chessboard chessboard){
        impossibleMove(getRowPosition()-1, getColumnPosition(), chessboard);
    }

    void kingMoveDown(Chessboard chessboard){
        impossibleMove(getRowPosition()+1, getColumnPosition(), chessboard);
    }
    void kingMoveLeft(Chessboard chessboard){
        impossibleMove(getRowPosition(), getColumnPosition()-1, chessboard);
    }
    void kingMoveRight(Chessboard chessboard){
        impossibleMove(getRowPosition(), getColumnPosition()+1, chessboard);
    }

    void kingMoveDiagonallyUpLeft(Chessboard chessboard){
        impossibleMove(getRowPosition()-1, getColumnPosition()-1, chessboard);
    }

    void kingMoveDiagonallyUpRight(Chessboard chessboard){
        impossibleMove(getRowPosition()-1, getColumnPosition()+1, chessboard);
    }
    void kingMoveDiagonallyDownLeft(Chessboard chessboard){
        impossibleMove(getRowPosition()+1, getColumnPosition()-1, chessboard);
    }
    void kingMoveDiagonallyDownRight(Chessboard chessboard){
        impossibleMove(getRowPosition()+1, getColumnPosition()+1, chessboard);
    }

    @Override
    public void showMovePossibilities(Chessboard chessboard) {
        kingMoveUp(chessboard);
        kingMoveDown(chessboard);
        kingMoveRight(chessboard);
        kingMoveLeft(chessboard);
        kingMoveDiagonallyUpLeft(chessboard);
        kingMoveDiagonallyDownLeft(chessboard);
        kingMoveDiagonallyUpRight(chessboard);
        kingMoveDiagonallyDownRight(chessboard);

    }
}