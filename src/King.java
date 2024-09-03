import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    public King(Color kingColor, ImageIcon kingImage, int rowPosition, int columPosition) {
        super(kingColor,kingImage, rowPosition, columPosition);
        super.pieceImageIcon = kingImage;
    }

    void castling(){
        if (this.pieceFirstMove && Chessboard.whitePlayer.rightRook.pieceFirstMove){
            System.out.println("jop rook2 je na mieste");
            if (!positionIsTaken(this.rowPosition, this.columPosition+1) && !positionIsTaken(this.rowPosition, this.columPosition+2)){
                System.out.println("this " + this);
                System.out.println("policka su prazdne");
                System.out.println(this.rowPosition + " row a colum " + this.columPosition);

                squareIsUnderAttack(Chessboard.blackPlayer);
            }
//            impossibleMove(rowPosition, columPosition+2);
        }
    }

    void kingMoveDiagonallyUpLeft(){
        impossibleMove(rowPosition-1, columPosition-1);
    }

    void kingMoveDiagonallyUpRight(){
        impossibleMove(rowPosition-1,columPosition+1);
    }
    void kingMoveDiagonallyDownLeft(){
        impossibleMove(rowPosition+1, columPosition-1);
    }
    void kingMoveDiagonallyDownRight(){
        impossibleMove(rowPosition+1, columPosition+1);
    }

    void kingMoveUp(){
        impossibleMove(rowPosition-1, columPosition);
    }

    void kingMoveDown(){
        impossibleMove(rowPosition+1, columPosition);
    }
    void kingMoveLeft(){
        impossibleMove(rowPosition, columPosition-1);
    }
    void kingMoveRight(){
        impossibleMove(rowPosition, columPosition+1);
    }

    @Override
    public void showMovePossibilities(){
        castling();

        kingMoveUp();
        kingMoveDown();
        kingMoveRight();
        kingMoveLeft();
        kingMoveDiagonallyUpLeft();
        kingMoveDiagonallyUpRight();
        kingMoveDiagonallyDownLeft();
        kingMoveDiagonallyDownRight();
    }
}
