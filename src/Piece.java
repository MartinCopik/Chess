import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piece implements MouseListener {

    protected Color pieceColor;
    ImageIcon pieceImageIcon;

    int rowPosition;
    int columPosition;
    JLabel pieceLabel;

    JPanel emptyPiecePanel;

    boolean pieceIsSelected = false;
    boolean pieceFirstMove = true;
    boolean pieceMove = false;

    public Piece(Color pieceColor, ImageIcon pieceImageIcon, int rowPosition, int columPosition){
        this.pieceColor = pieceColor;
        this.pieceLabel = new JLabel();
        this.pieceLabel.addMouseListener(this);
        this.pieceImageIcon = pieceImageIcon;
        scaleImageOfPiece();
        this.rowPosition = rowPosition;
        this.columPosition = columPosition;
    }

    public Piece(Color emptyPieceColor, int rowPosition, int columPosition){
        this.pieceColor = emptyPieceColor;
        this.emptyPiecePanel = new JPanel();
        this.emptyPiecePanel.addMouseListener(this);
        this.rowPosition = rowPosition;
        this.columPosition = columPosition;
    }

    private void scaleImageOfPiece(){
        Image scaledImage = this.pieceImageIcon.getImage().getScaledInstance(EmptyPiece.width/2, EmptyPiece.height/2,Image.SCALE_SMOOTH);
        this.pieceImageIcon = new ImageIcon(scaledImage);
        this.pieceLabel.setIcon(this.pieceImageIcon);

    }

    boolean isAnyPieceSelected(){
        for (int row = 0; row < Chessboard.getArrayBoard().length; row++){
            for (int colum = 0; colum < Chessboard.getArrayBoard().length; colum++){
                if (Chessboard.arrayBoard[row][colum][0].emptyPiecePanel.getBackground() == Color.green
                        || Chessboard.arrayBoard[row][colum][0].emptyPiecePanel.getBackground() == Color.red ){
                    return this.pieceIsSelected = true;
                }
            }
        }
        return this.pieceIsSelected = false;
    }

    void setActualPositionOfPiece(Piece pieceToFind){
        for (int row = 0; row < Chessboard.getArrayBoard().length; row++ ){
            for (int colum = 0; colum < Chessboard.getArrayBoard().length; colum++){
                if (pieceToFind == Chessboard.arrayBoard[row][colum][1]){
                    this.rowPosition = row;
                    this.columPosition = colum;
                }
            }
        }
    }
    static void setAttackedSquares(Player  attackingPlayer){
        for (Piece piece : attackingPlayer.playerPieces){
            piece.showMovePossibilities();
        }
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumPosition() {
        return columPosition;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public void showMovePossibilities(){
    }

    boolean isOutOfBorder(int rowToCheck, int columToCheck){
        if (rowToCheck <0 || columToCheck <0 || rowToCheck > Chessboard.getArrayBoard().length-1 || columToCheck > Chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }
    boolean pieceIsAttacking(Piece pieceToMakeAttack, int rowToCheck, int columToCheck){
        if (pieceToMakeAttack.pieceColor != Chessboard.getArrayBoard()[rowToCheck][columToCheck][1].pieceColor){
            return true;
        }
        return false;
    }
    boolean positionIsTaken(int rowToCheck, int columToCheck){
        if (Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] != null){
            return true;
        }
        return false;
    }
    boolean impossibleMove(int rowToCheck, int columToCheck){
        if (isOutOfBorder(rowToCheck,columToCheck)){
            return true;
        }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)){
            if (this.pieceMove){
                Chessboard.getArrayBoard()[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.gray);
                if (Move.figureToMove != null){
                    Chessboard.getArrayBoard()[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.green);
                }
                return true;
            }
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck,columToCheck), this.pieceColor);
            return true;
        } else if (positionIsTaken(rowToCheck, columToCheck) && !pieceIsAttacking(this, rowToCheck, columToCheck)) {
            return true;
        } else if (!positionIsTaken(rowToCheck, columToCheck)) {
            if (this.pieceMove){
                Chessboard.getArrayBoard()[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.gray);
                if (Move.figureToMove != null){
                    Chessboard.getArrayBoard()[rowToCheck][columToCheck][0].emptyPiecePanel.setBackground(Color.green);
                }
                return false;
            }
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck,columToCheck), this.pieceColor);
            return false;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isAnyPieceSelected()) {
            Move.figureToMove = null;
            Chessboard.setColors();
        }
        if (Move.rightColorToMakeMove(this)){
            this.pieceMove = true;
            setActualPositionOfPiece(this);
            Move.setFigureToMove(this);
            showMovePossibilities();
            if (!isAnyPieceSelected()){
                Move.figureToMove = null;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (Move.rightColorToMakeMove(this) && !isAnyPieceSelected()){
            this.pieceMove = true;
            setActualPositionOfPiece(this);
            showMovePossibilities();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.pieceMove = false;
        if (!isAnyPieceSelected()){
            Chessboard.setColors();
        }
    }
}
