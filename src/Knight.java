import java.awt.*;

public class Knight extends ChessPiece{


    public Knight(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        super(chessPieceColor, iconPath, rowPosition, columnPosition);
    }

    @Override
    public void setChessPieceMovesMap(Chessboard chessboard) {
        knightMoveUpShortLeft(chessboard);
        knightMoveUpShortRight(chessboard);
        knightMoveUpLongLeft(chessboard);
        knightMoveUpLongRight(chessboard);
        knightMoveDownShortLeft(chessboard);
        knightMoveDownShortRight(chessboard);
        knightMoveDownLongLeft(chessboard);
        knightMoveDownLongRight(chessboard);
    }

    private  void knightMoveUpShortLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()-1, getColumnPosition()-2, chessboard);
    }
    private  void knightMoveUpShortRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()-1, getColumnPosition()+2, chessboard);
    }
    private  void knightMoveUpLongLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()-2, getColumnPosition()-1, chessboard);
    }
    private  void knightMoveUpLongRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()-2, getColumnPosition()+1, chessboard);
    }
    private  void knightMoveDownShortLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+1, getColumnPosition()-2, chessboard);
    }
    private  void knightMoveDownShortRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+1, getColumnPosition()+2, chessboard);
    }
    private  void knightMoveDownLongLeft(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+2, getColumnPosition()-1, chessboard);
    }
    private  void knightMoveDownLongRight(Chessboard chessboard){
        Move.isMoveValid(this, getRowPosition()+2, getColumnPosition()+1, chessboard);
    }
}
