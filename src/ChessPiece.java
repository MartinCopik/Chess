import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;

public enum ChessPiece {
    ROOK(Color.black, "blackRook.png", 0, 0){
        @Override
        public void printMove() {
            System.out.println("Rook cannot jump over another Pieces, can go only in 4 word dimensions");;
        }
    },
    KNIGHT(Color.white, "whiteKnight.png", 7, 1){

        @Override
        public void printMove() {
            System.out.println("Knight can jump over another Pieces");
            hopHop();
        }
        public void hopHop(){
            System.out.println("Knight goes hopikyHop");
        }
    };

    public void printMove(){

    }


    ChessPiece(Color pieceColor, String iconPath, int row, int column){
        setPieceColor(pieceColor);
        setPieceImageIcon(iconPath);
        setRow(row);
        setColumn(column);
    }

    Color pieceColor;
    String iconPath;
    ImageIcon pieceImageIcon;
    int row;
    int column;

    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }
    public void setPieceImageIcon(String iconPath) {
        this.pieceImageIcon = new ImageIcon(iconPath);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
