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

    private boolean pieceIsSelected = false;
    private boolean pieceFirstMove = true;
    private boolean pieceMove = false;

    public Piece(Player player, Color pieceColor, ImageIcon pieceImageIcon, int rowPosition, int columPosition){
        this.player = player;
        this.setPieceColor(pieceColor);
        this.setPieceLabel(new JLabel());
        this.pieceLabel.addMouseListener(this);
        this.setPieceImageIcon(pieceImageIcon);
        scaleImageOfPiece();
        this.setRowPosition(rowPosition);
        this.setColumPosition(columPosition);
    }

    public Piece(Color emptyPieceColor, int rowPosition, int columPosition){
        this.setPieceColor(emptyPieceColor);
        this.setEmptyPiecePanel(new JPanel());
        this.emptyPiecePanel.addMouseListener(this);
        this.setRowPosition(rowPosition);
        this.setColumPosition(columPosition);
    }

    private void scaleImageOfPiece(){
        Image scaledImage = this.pieceImageIcon.getImage().getScaledInstance(EmptyPiece.width/2, EmptyPiece.height/2,Image.SCALE_SMOOTH);
        this.pieceImageIcon = new ImageIcon(scaledImage);
        this.pieceLabel.setIcon(this.pieceImageIcon);

    }

    private boolean isAnyPieceSelected(){
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

    public void setActualPositionOfPiece(Piece pieceToFind){
        for (int row = 0; row < Chessboard.getArrayBoard().length; row++ ){
            for (int colum = 0; colum < Chessboard.getArrayBoard().length; colum++){
                if (pieceToFind == Chessboard.arrayBoard[row][colum][1]){
                    this.rowPosition = row;
                    this.columPosition = colum;
                }
            }
        }
    }
//    void setAttackedSquares(Player  attackingPlayer){
//        for (Piece piece : attackingPlayer.getPlayerPieces()){
//            piece.showMovePossibilities();
//        }
//    }

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

    public boolean getPieceFirstMove() {
        return pieceFirstMove;
    }

    public void setPieceFirstMove(boolean pieceFirstMove) {
        this.pieceFirstMove = pieceFirstMove;
    }

    public boolean getIsPieceIsSelected() {
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

    public void showMovePossibilities(){
    }

    boolean selfCheckOnEmptySquare(Piece pieceToBeMoved, int rowToCheck, int columToCheck){
        EmptyPiece.attackedSquares.clear();
        Chessboard.getArrayBoard()[pieceToBeMoved.rowPosition][pieceToBeMoved.columPosition][1] = null;
        Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = pieceToBeMoved;

        pieceToBeMoved.player.setAttackedSquares(Chessboard.getAttackingPlayer(pieceToBeMoved.player));
//        setAttackedSquares(Chessboard.getAttackingPlayer(pieceToBeMoved.player));
        pieceToBeMoved.player.getKing().kingIsInCheck();

        Chessboard.getArrayBoard()[pieceToBeMoved.rowPosition][pieceToBeMoved.columPosition][1] = pieceToBeMoved;
        Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = null;
        EmptyPiece.attackedSquares.clear();
        return pieceToBeMoved.player.getKing().kingIsInCheck;
    }

    boolean checkIfCheckIsMade(Piece pieceToMakeCheck){
        pieceToMakeCheck.player.setAttackedSquares(pieceToMakeCheck.player);
//        setAttackedSquares(pieceToMakeCheck.player);
        if (Chessboard.getAttackingPlayer(pieceToMakeCheck.player).getKing().kingIsInCheck()){
            Chessboard.getAttackingPlayer(pieceToMakeCheck.player).setPieceAttackingKing(pieceToMakeCheck);
        }
        if (pieceToMakeCheck.pieceColor.equals(Color.WHITE)){
            if (Chessboard.getBlackPlayer().getKing().kingIsInCheck()){
                Chessboard.getBlackPlayer().setPieceAttackingKing(pieceToMakeCheck);
                return true;
            }
        }else {
            if (Chessboard.getWhitePlayer().getKing().kingIsInCheck()){
                Chessboard.getWhitePlayer().setPieceAttackingKing(pieceToMakeCheck);
                return true;
            }
        }
        return false;
    }
    boolean stepIntoAttack(int rowToCheck, int columToCheck, Piece pieceToDefend){
            Chessboard.arrayBoard[pieceToDefend.rowPosition][pieceToDefend.columPosition][1] = null;
            Chessboard.arrayBoard[rowToCheck][columToCheck][1] = pieceToDefend;
            EmptyPiece.attackedSquares.clear();
            pieceToDefend.player.setAttackedSquares(Chessboard.getAttackingPlayer(pieceToDefend.player));
//            setAttackedSquares(Chessboard.getAttackingPlayer(pieceToDefend.player));
            if (!pieceToDefend.player.getKing().kingIsInCheck()){
                Chessboard.arrayBoard[rowToCheck][columToCheck][1] = null;
                Chessboard.arrayBoard[pieceToDefend.rowPosition][pieceToDefend.columPosition][1] = pieceToDefend;
                pieceToDefend.player.setAttackedSquares(Chessboard.getAttackingPlayer(pieceToDefend.player));
//                setAttackedSquares(Chessboard.getAttackingPlayer(pieceToDefend.player));
                return true;
            }
            Chessboard.arrayBoard[rowToCheck][columToCheck][1] = null;
            Chessboard.arrayBoard[pieceToDefend.rowPosition][pieceToDefend.columPosition][1] = pieceToDefend;
        return false;
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
    boolean impossibleMove(Player player, int rowToCheck, int columToCheck){
        if (isOutOfBorder(rowToCheck,columToCheck)){
            return true;
        }
        if (this.pieceMove && !player.getKing().kingIsInCheck()){
            if (!positionIsTaken(rowToCheck, columToCheck)){
                if (selfCheckOnEmptySquare(this, rowToCheck, columToCheck)){
                    return true;
                }
            }
        }
        if (player.getPieceAttackingKing() != null && Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] == player.getPieceAttackingKing()){
            if (GameManager.checkOfGameManager){
                player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            if (player.getKing().kingIsInCheck() && this.pieceMove){
                EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
            }
            return true;
        }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (this.pieceMove && !player.getKing().kingIsInCheck()) {
                EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                return true;
            }
            if (GameManager.checkOfGameManager && !player.getKing().kingIsInCheck){
                player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            if (!GameManager.checkOfGameManager){
                EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck, columToCheck), this);
            }
            return true;
        }
        else if (positionIsTaken(rowToCheck, columToCheck) && !pieceIsAttacking(this, rowToCheck, columToCheck)) {
            EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            return true;
        } else if (!positionIsTaken(rowToCheck, columToCheck)) {
            if (this.pieceMove){
                if (!player.getKing().kingIsInCheck() || this instanceof King || stepIntoAttack(rowToCheck, columToCheck, this)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                    return false;
                }
            }
            if (GameManager.checkOfGameManager){
                if (!player.getKing().kingIsInCheck || this instanceof King){
                    player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
                }
            }
            if (!GameManager.checkOfGameManager){
                EmptyPiece.arrangementOfAttackedSquares(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
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
        if (this instanceof  King && Move.moveCounter ==3){
            System.out.println();
        }
        if (Move.rightColorToMakeMove(this) && !isAnyPieceSelected()){
            this.pieceMove = true;
            setActualPositionOfPiece(this);
            showMovePossibilities();
            this.pieceMove = false;
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
