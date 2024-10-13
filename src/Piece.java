import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Piece implements MouseListener {

    protected Color pieceColor;
    private ImageIcon pieceImageIcon;
    private Player player;

    private int rowPosition;
    private int columPosition;
    private JLabel pieceLabel;

    private JPanel emptyPiecePanel;
    private int width = ChessGame.getWidthFrame()/8;
    private int height = ChessGame.getHeightFrame()/8;

    private boolean pieceIsSelected = false;
    private boolean pieceFirstMove = true;
    private boolean pieceMove = false;

    public Piece(Player player, Color pieceColor, ImageIcon pieceImageIcon, int rowPosition, int columPosition){
        this.setPlayer(player);
        this.setPieceColor(pieceColor);
        this.setPieceLabel(new JLabel());
        this.getPieceLabel().addMouseListener(this);
        this.setPieceImageIcon(pieceImageIcon);
        scaleImageOfPiece();
        this.setRowPosition(rowPosition);
        this.setColumPosition(columPosition);
    }

    public Piece(Color emptyPieceColor, int rowPosition, int columPosition){
        this.setPieceColor(emptyPieceColor);
        this.setEmptyPiecePanel(new JPanel());
        this.getEmptyPiecePanel().addMouseListener(this);
        this.setRowPosition(rowPosition);
        this.setColumPosition(columPosition);
    }

    private void scaleImageOfPiece(){
        Image scaledImage = this.getPieceImageIcon().getImage().getScaledInstance(getWidth()/2, getHeight()/2,Image.SCALE_SMOOTH);
        this.setPieceImageIcon(new ImageIcon(scaledImage));
        this.getPieceLabel().setIcon(this.getPieceImageIcon());

    }

    private boolean isAnyPieceSelected(){
        for (int row = 0; row < Chessboard.getArrayBoard().length; row++){
            for (int colum = 0; colum < Chessboard.getArrayBoard().length; colum++){
                if (Chessboard.getArrayBoard()[row][colum][0].getEmptyPiecePanel().getBackground() == Color.green
                        || Chessboard.getArrayBoard()[row][colum][0].getEmptyPiecePanel().getBackground() == Color.red ){
                    this.setPieceIsSelected(true);
                    return this.getIsPieceSelected();
                }
            }
        }
        this.setPieceIsSelected(false);
        return this.getIsPieceSelected();
    }

    public void setActualPositionOfPiece(Piece pieceToFind){
        for (int row = 0; row < Chessboard.getArrayBoard().length; row++ ){
            for (int colum = 0; colum < Chessboard.getArrayBoard().length; colum++){
                if (pieceToFind == Chessboard.getArrayBoard()[row][colum][1]){
                    this.setRowPosition(row);
                    this.setColumPosition(colum);
                }
            }
        }
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getPieceFirstMove() {
        return pieceFirstMove;
    }

    public void setPieceFirstMove(boolean pieceFirstMove) {
        this.pieceFirstMove = pieceFirstMove;
    }

    public boolean getIsPieceSelected() {
        return pieceIsSelected;
    }

    public void setPieceIsSelected(boolean pieceIsSelected) {
        this.pieceIsSelected = pieceIsSelected;
    }

    public boolean getPieceMove() {
        return pieceMove;
    }

    public void setPieceMove(boolean pieceMove) {
        this.pieceMove = pieceMove;
    }

    public ImageIcon getPieceImageIcon() {
        return pieceImageIcon;
    }

    public void setPieceImageIcon(ImageIcon pieceImageIcon) {
        this.pieceImageIcon = pieceImageIcon;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void showMovePossibilities(){
    }

    public void markTheSquareForMove(){}
    public void markTheSquareForAttack(){}

    public boolean selfCheckOnEmptySquare(Piece pieceToBeMoved, int rowToCheck, int columToCheck){
        Chessboard.getAttackingPlayer(pieceToBeMoved.getPlayer()).getAttackedSquares().clear();
        Chessboard.getArrayBoard()[pieceToBeMoved.getRowPosition()][pieceToBeMoved.getColumPosition()][1] = null;
        Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = pieceToBeMoved;

        Chessboard.getAttackingPlayer(pieceToBeMoved.getPlayer()).setAttackedSquares();
        pieceToBeMoved.getPlayer().getKing().kingIsInCheck();

        Chessboard.getArrayBoard()[pieceToBeMoved.getRowPosition()][pieceToBeMoved.getColumPosition()][1] = pieceToBeMoved;
        Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = null;
        Chessboard.getAttackingPlayer(pieceToBeMoved.getPlayer()).getAttackedSquares().clear();
        return pieceToBeMoved.getPlayer().getKing().getKingIsInCheck();
    }

    public void checkIfCheckIsMade(Piece pieceToMakeCheck){
        pieceToMakeCheck.getPlayer().setAttackedSquares();
        if (Chessboard.getAttackingPlayer(pieceToMakeCheck.getPlayer()).getKing().kingIsInCheck()){
            Chessboard.getAttackingPlayer(pieceToMakeCheck.getPlayer()).setPieceAttackingKing(pieceToMakeCheck);
        }
    }
    public boolean stepIntoAttack(int rowToCheck, int columToCheck, Piece pieceToDefend){
            Chessboard.getArrayBoard()[pieceToDefend.getRowPosition()][pieceToDefend.getColumPosition()][1] = null;
            Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = pieceToDefend;

            Chessboard.getAttackingPlayer(pieceToDefend.getPlayer()).getAttackedSquares().clear();
            Chessboard.getAttackingPlayer(pieceToDefend.getPlayer()).setAttackedSquares();

            if (!pieceToDefend.getPlayer().getKing().kingIsInCheck()){
                Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = null;
                Chessboard.getArrayBoard()[pieceToDefend.getRowPosition()][pieceToDefend.getColumPosition()][1] = pieceToDefend;
                Chessboard.getAttackingPlayer(pieceToDefend.getPlayer()).setAttackedSquares();
                return true;
            }
            Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = null;
            Chessboard.getArrayBoard()[pieceToDefend.getRowPosition()][pieceToDefend.getColumPosition()][1] = pieceToDefend;
        return false;
    }

    public boolean isOutOfBorder(int rowToCheck, int columToCheck){
        if (rowToCheck <0 || columToCheck <0 || rowToCheck > Chessboard.getArrayBoard().length-1 || columToCheck > Chessboard.getArrayBoard().length-1){
            return true;
        }
        return false;
    }
    public boolean pieceIsAttacking(Piece pieceToMakeAttack, int rowToCheck, int columToCheck){
        if (pieceToMakeAttack.getPieceColor() != Chessboard.getArrayBoard()[rowToCheck][columToCheck][1].getPieceColor()){
            return true;
        }
        return false;
    }
    public boolean positionIsTaken(int rowToCheck, int columToCheck){
        if (Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] != null){
            return true;
        }
        return false;
    }
    public boolean impossibleMove(Player player, int rowToCheck, int columToCheck){
        if (isOutOfBorder(rowToCheck,columToCheck)){
            return true;
        }
        if (this.getPieceMove() && !player.getKing().kingIsInCheck()){
            if (!positionIsTaken(rowToCheck, columToCheck)){
                if (selfCheckOnEmptySquare(this, rowToCheck, columToCheck)){
                    return true;
                }
            }
        }
        if (player.getPieceAttackingKing() != null && Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] == player.getPieceAttackingKing()){
            if (GameManager.getCheckOfGameManager()){
                player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            if (player.getKing().kingIsInCheck() && this.getPieceMove()){
                Chessboard.getEmptySquare(rowToCheck, columToCheck).markTheSquareForAttack();
            }
            return true;
        }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (this.getPieceMove() && !player.getKing().kingIsInCheck()) {
                Chessboard.getEmptySquare(rowToCheck, columToCheck).markTheSquareForAttack();
                return true;
            }
            if (GameManager.getCheckOfGameManager() && !player.getKing().getKingIsInCheck()){
                player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            if (!GameManager.getCheckOfGameManager()){
                player.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
//                EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
            }
            return true;
        }
        else if (positionIsTaken(rowToCheck, columToCheck) && !pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (!player.getKing().getKingIsInCheck()){
                player.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
            }
            return true;
        } else if (!positionIsTaken(rowToCheck, columToCheck)) {
            if (this.getPieceMove()){
                if (!player.getKing().kingIsInCheck() || this instanceof King || stepIntoAttack(rowToCheck, columToCheck, this)){
                    Chessboard.getEmptySquare(rowToCheck, columToCheck).markTheSquareForMove();
                    return false;
                }
            }
            if (GameManager.getCheckOfGameManager()){
                if (!player.getKing().getKingIsInCheck() || this instanceof King){
                    player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
            }
            if (!GameManager.getCheckOfGameManager()){
                player.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
            }
            return false;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isAnyPieceSelected()) {
            Move.setFigureToMove(null);
            Chessboard.setColors();
        }
        if (Move.rightColorToMakeMove(this)){
            this.setPieceMove(true);
            setActualPositionOfPiece(this);
            Move.setFigureToMove(this);
            showMovePossibilities();
            if (!isAnyPieceSelected()){
                Move.setFigureToMove(null);
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
            this.setPieceMove(true);
            setActualPositionOfPiece(this);
            showMovePossibilities();
            this.setPieceMove(false);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setPieceMove(false);
        if (!isAnyPieceSelected()){
            Chessboard.setColors();
        }
    }
}
