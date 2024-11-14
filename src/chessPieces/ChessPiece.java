package chessPieces;

import chessGame.ChessSquare;
import chessGame.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class ChessPiece {

    private Color chessPieceColor;
    private ImageIcon pieceImageIcon;
    private JLabel pieceLabel;
    private int rowPosition;
    private int columnPosition;
    private boolean pieceFirstMove = true;
    private HashMap<ChessSquare, ChessPiece> chessPieceMovementMap = new HashMap<>();

     public ChessPiece(Color chessPieceColor, String iconPath, int rowPosition, int columnPosition){
        this.chessPieceColor = chessPieceColor;
        this.pieceImageIcon = new ImageIcon(iconPath);
        this.pieceLabel = new JLabel();
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    /**
     * scale the image of specified chess piece
     * @param widthOfPiece
     * @param heightOfPiece
     */
    public void scaleImageOfPiece(int widthOfPiece, int heightOfPiece){
        Image scaledImage = this.pieceImageIcon.getImage().getScaledInstance(widthOfPiece, heightOfPiece,Image.SCALE_SMOOTH);
        this.pieceImageIcon.setImage(scaledImage);
        this.pieceLabel.setIcon(this.pieceImageIcon);
    }

    /**
     * colors the chess squares representing the current possible movement of specified chess piece
     */
    public  void showMovePossibilitiesOfPiece(){
        for (ChessSquare square : chessPieceMovementMap.keySet()){
            square.getEmptyPiecePanel().setBackground(Color.gray);
        }
    }

    /**
     * set up the movement map of specified chess piece
     * the method is override in each ChessPiece inheritors
     * @param chessboard
     */
    public abstract void setChessPieceMovementMap(Chessboard chessboard);

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

    public HashMap<ChessSquare, ChessPiece> getChessPieceMovementMap() {
        return chessPieceMovementMap;
    }
}
