import javax.swing.*;
import java.awt.*;

public class Piece {

    protected Color pieceColor;
    private ImageIcon pieceImageIcon;
    private Move move = new Move();

    private int rowPosition;
    private int columPosition;
    private JLabel pieceLabel;
    private JPanel emptyPiecePanel;
    private int widthOfPiece;
    private int heightOfPiece;
    private boolean pieceFirstMove = true;

    public Piece(Color pieceColor, ImageIcon pieceImageIcon, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece){
        this.setPieceColor(pieceColor);
        this.setPieceLabel(new JLabel());
        this.setPieceImageIcon(pieceImageIcon);
        this.setWidthOfPiece(widthOfPiece);
        this.setHeightOfPiece(heightOfPiece);
        this.scaleImageOfPiece(widthOfPiece, heightOfPiece);
        this.setRowPosition(rowPosition);
        this.setColumPosition(columPosition);
    }

    public Piece(Color emptyPieceColor, int rowPosition, int columPosition, int widthOfPiece, int heightOfPiece){
        this.setPieceColor(emptyPieceColor);
        this.setEmptyPiecePanel(new JPanel());
        this.setRowPosition(rowPosition);
        this.setColumPosition(columPosition);
        this.setWidthOfPiece(widthOfPiece);
        this.setHeightOfPiece(heightOfPiece);
    }

    private void scaleImageOfPiece(int widthOfPiece, int heightOfPiece){
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

    public int getColumPosition() {
        return columPosition;
    }

    public void setColumPosition(int columPosition) {
        this.columPosition = columPosition;
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

    public void setMove(Move move) {
        this.move = move;
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
        return getMove().isMoveValid(this, rowToCheck, columToCheck, chessboard);
    }

}