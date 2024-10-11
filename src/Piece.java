import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class Piece implements MouseListener {

    protected Color pieceColor;
    ImageIcon pieceImageIcon;
    Player player;
    HashMap <Piece,Piece> moveRecordOfPiece = new HashMap<>();


    int rowPosition;
    int columPosition;
    JLabel pieceLabel;

    JPanel emptyPiecePanel;

    boolean pieceIsSelected = false;
    boolean pieceFirstMove = true;
    boolean pieceMove = false;

    public Piece(Player player, Color pieceColor, ImageIcon pieceImageIcon, int rowPosition, int columPosition){
        this.player = player;
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

    void addMoveRecord(Piece movedPiece, Piece squarePosition){
        moveRecordOfPiece.put(movedPiece, squarePosition);
        this.player.setMovesRecord(moveRecordOfPiece);
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
    boolean selfCheckOnEmptySquare(Piece pieceToBeMoved, int rowToCheck, int columToCheck){
        EmptyPiece.attackedSquares.clear();
        Chessboard.getArrayBoard()[pieceToBeMoved.rowPosition][pieceToBeMoved.columPosition][1] = null;
        Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = pieceToBeMoved;

        setAttackedSquares(Chessboard.getAttackingPlayer(pieceToBeMoved.player));
        pieceToBeMoved.player.king.kingIsInCheck();

        Chessboard.getArrayBoard()[pieceToBeMoved.rowPosition][pieceToBeMoved.columPosition][1] = pieceToBeMoved;
        Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] = null;
        EmptyPiece.attackedSquares.clear();
        return pieceToBeMoved.player.king.kingIsInCheck;
    }

    boolean checkIfCheckIsMade(Piece pieceToMakeCheck){
        setAttackedSquares(pieceToMakeCheck.player);
        if (Chessboard.getAttackingPlayer(pieceToMakeCheck.player).king.kingIsInCheck()){
            Chessboard.getAttackingPlayer(pieceToMakeCheck.player).pieceAttackingKing = pieceToMakeCheck;
        }
        if (pieceToMakeCheck.pieceColor.equals(Color.WHITE)){
            if (Chessboard.blackPlayer.king.kingIsInCheck()){
                Chessboard.blackPlayer.pieceAttackingKing = pieceToMakeCheck;
                return true;
            }
        }else {
            if (Chessboard.whitePlayer.king.kingIsInCheck()){
                Chessboard.whitePlayer.pieceAttackingKing = pieceToMakeCheck;
                return true;
            }
        }
        return false;
    }
    boolean stepIntoAttack(int rowToCheck, int columToCheck, Piece pieceToDefend){
            Chessboard.arrayBoard[pieceToDefend.rowPosition][pieceToDefend.columPosition][1] = null;
            Chessboard.arrayBoard[rowToCheck][columToCheck][1] = pieceToDefend;
            EmptyPiece.attackedSquares.clear();
            setAttackedSquares(Chessboard.getAttackingPlayer(pieceToDefend.player));
            if (!pieceToDefend.player.king.kingIsInCheck()){
                Chessboard.arrayBoard[rowToCheck][columToCheck][1] = null;
                Chessboard.arrayBoard[pieceToDefend.rowPosition][pieceToDefend.columPosition][1] = pieceToDefend;
                setAttackedSquares(Chessboard.getAttackingPlayer(pieceToDefend.player));
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
        if (this.pieceMove && !player.king.kingIsInCheck()){
            if (!positionIsTaken(rowToCheck, columToCheck)){
                if (selfCheckOnEmptySquare(this, rowToCheck, columToCheck)){
                    return true;
                }
            }
        }
        if (player.pieceAttackingKing != null && Chessboard.getArrayBoard()[rowToCheck][columToCheck][1] == player.pieceAttackingKing){
            if (GameManager.checkOfGameManager){
                player.setMovePossibilities(Chessboard.getEmptySquare(rowToCheck,columToCheck), this);
            }
            if (player.king.kingIsInCheck() && this.pieceMove){
                EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
            }
            return true;
        }
        if (positionIsTaken(rowToCheck, columToCheck) && pieceIsAttacking(this, rowToCheck, columToCheck)) {
            if (this.pieceMove && !player.king.kingIsInCheck()) {
                EmptyPiece.markTheSquareForAttack(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                return true;
            }
            if (GameManager.checkOfGameManager && !player.king.kingIsInCheck){
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
                if (!player.king.kingIsInCheck() || this instanceof King || stepIntoAttack(rowToCheck, columToCheck, this)){
                    EmptyPiece.markTheSquareForMove(Chessboard.getEmptySquare(rowToCheck, columToCheck));
                    return false;
                }
            }
            if (GameManager.checkOfGameManager){
                if (!player.king.kingIsInCheck || this instanceof King){
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
