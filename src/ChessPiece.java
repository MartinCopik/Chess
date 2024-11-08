import javax.swing.*;
import java.awt.*;

public abstract class ChessPiece {

    private Color chessPieceColor;
    private ImageIcon pieceImageIcon;
    private JLabel pieceLabel;
    private int rowPosition;
    private int columnPosition;
    private boolean pieceFirstMove = true;

    ChessPiece(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        this.chessPieceColor = chessPieceColor;
        this.pieceImageIcon = new ImageIcon(iconPath);
        this.pieceLabel = new JLabel();
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    public void scaleImageOfPiece(int widthOfPiece, int heightOfPiece){
        Image scaledImage = this.pieceImageIcon.getImage().getScaledInstance(widthOfPiece, heightOfPiece,Image.SCALE_SMOOTH);
        this.pieceImageIcon.setImage(scaledImage);
        this.pieceLabel.setIcon(this.pieceImageIcon);
    }

    public abstract void chessPieceMovePossibilities(Chessboard chessboard);

    public Color getChessPieceColor() {
        return chessPieceColor;
    }

    public JLabel getPieceLabel() {
        return pieceLabel;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    public boolean getPieceFirstMove() {
        return pieceFirstMove;
    }

    public void setPieceFirstMove(boolean pieceFirstMove) {
        this.pieceFirstMove = pieceFirstMove;
    }
}
