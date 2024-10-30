import javax.swing.*;
import java.awt.*;

public class Piece {

    protected Color pieceColor;
    private ImageIcon pieceImageIcon;
    private Move move = new Move();

    private int rowPosition;
    private int columnPosition;
    private JLabel pieceLabel;
    private JPanel emptyPiecePanel;
    private int widthOfPiece;
    private int heightOfPiece;
    private boolean pieceFirstMove = true;

    public Piece(Color emptyPieceColor, ImageIcon pieceImageIcon, int rowPosition, int columnPosition, int widthOfPiece, int heightOfPiece){
        this.setPieceColor(emptyPieceColor);
        this.setEmptyPiecePanel(new JPanel());
        this.setRowPosition(rowPosition);
        this.setColumnPosition(columnPosition);
        this.setWidthOfPiece(widthOfPiece);
        this.setHeightOfPiece(heightOfPiece);

        if (!(this instanceof EmptySquare)){
            this.setPieceLabel(new JLabel());
            this.setPieceImageIcon(pieceImageIcon);
            this.scaleImageOfPiece();
        }
    }

    private void scaleImageOfPiece(){
        Image scaledImage = this.getPieceImageIcon().getImage().getScaledInstance(getWidthOfPiece(), getHeightOfPiece(),Image.SCALE_SMOOTH);
        this.setPieceImageIcon(new ImageIcon(scaledImage));
        this.getPieceLabel().setIcon(this.getPieceImageIcon());
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

    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ImageIcon getPieceImageIcon() {
        return pieceImageIcon;
    }

    public void setPieceImageIcon(ImageIcon pieceImageIcon) {
        this.pieceImageIcon = pieceImageIcon;
    }

    public Move getMove() {
        return move;
    }

    public JLabel getPieceLabel() {
        return pieceLabel;
    }

    public void setPieceLabel(JLabel pieceLabel) {
        this.pieceLabel = pieceLabel;
    }

    public JPanel getEmptyPiecePanel() {
        return emptyPiecePanel;
    }

    public void setEmptyPiecePanel(JPanel emptyPiecePanel) {
        this.emptyPiecePanel = emptyPiecePanel;
    }

    public int getWidthOfPiece() {
        return widthOfPiece;
    }

    public void setWidthOfPiece(int widthOfPiece) {
        this.widthOfPiece = widthOfPiece;
    }

    public int getHeightOfPiece() {
        return heightOfPiece;
    }

    public void setHeightOfPiece(int heightOfPiece) {
        this.heightOfPiece = heightOfPiece;
    }

    public boolean getPieceFirstMove() {
        return pieceFirstMove;
    }

    public void setPieceFirstMove(boolean pieceFirstMove) {
        this.pieceFirstMove = pieceFirstMove;
    }

    public void showMovePossibilities(Chessboard chessboard){
    }

    public boolean impossibleMove(int rowToCheck, int columToCheck, Chessboard chessboard){
//        return getMove().isMoveValid(this, rowToCheck, columToCheck, chessboard);
        return false;
    }

}