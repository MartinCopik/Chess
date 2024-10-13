import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromotionWindow  implements ActionListener {
    private JFrame promotionWindow;
    private JButton rookButton;
    private JButton knightButton;
    private JButton bishopButton;
    private JButton queenButton;
    private Piece pieceToBePromoted;

    PromotionWindow(Piece pawnToBePromoted){
        ChessGame.getChessGame().setVisible(false);

        setPromotionWindow(new JFrame());
        setRookButton(new JButton("Rook"));
        setKnightButton(new JButton("Knight"));
        setBishopButton(new JButton("Bishop"));
        setQueenButton(new JButton("Queen"));
        setPieceToBePromoted(pawnToBePromoted);

        getRookButton().addActionListener(this);
        getKnightButton().addActionListener(this);
        getBishopButton().addActionListener(this);
        getQueenButton().addActionListener(this);

        getPromotionWindow().add(getRookButton());
        getPromotionWindow().add(getKnightButton());
        getPromotionWindow().add(getBishopButton());
        getPromotionWindow().add(getQueenButton());

        getPromotionWindow().setLayout(new GridLayout());
        getPromotionWindow().setSize(ChessGame.getWidthFrame()/2, ChessGame.getHeightFrame()/2);
        getPromotionWindow().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getPromotionWindow().setLocationRelativeTo(null);
        getPromotionWindow().setResizable(false);
        getPromotionWindow().setVisible(true);
    }

    public JButton getRookButton() {
        return rookButton;
    }

    public void setRookButton(JButton rookButton) {
        this.rookButton = rookButton;
    }

    public JButton getKnightButton() {
        return knightButton;
    }

    public void setKnightButton(JButton knightButton) {
        this.knightButton = knightButton;
    }

    public JButton getBishopButton() {
        return bishopButton;
    }

    public void setBishopButton(JButton bishopButton) {
        this.bishopButton = bishopButton;
    }

    public JButton getQueenButton() {
        return queenButton;
    }

    public void setQueenButton(JButton queenButton) {
        this.queenButton = queenButton;
    }

    public Piece getPieceToBePromoted() {
        return pieceToBePromoted;
    }

    public void setPieceToBePromoted(Piece pieceToBePromoted) {
        this.pieceToBePromoted = pieceToBePromoted;
    }

    public JFrame getPromotionWindow() {
        return promotionWindow;
    }

    public void setPromotionWindow(JFrame promotionWindow) {
        this.promotionWindow = promotionWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getRookButton()) {
            promotePiece(new Rook(setPromotingPlayer(), getPieceToBePromoted().getPieceColor(), new ImageIcon(colorToBePromoted()+"Rook.png"), getPieceToBePromoted().getRowPosition(), getPieceToBePromoted().getColumPosition()));
        }
        if (e.getSource() == getKnightButton()) {
            promotePiece(new Knight(setPromotingPlayer(), getPieceToBePromoted().getPieceColor(), new ImageIcon(colorToBePromoted()+"Knight.png"), getPieceToBePromoted().getRowPosition(), getPieceToBePromoted().getColumPosition()));
        }
        if (e.getSource() == getBishopButton()) {
            promotePiece(new Bishop(setPromotingPlayer(), getPieceToBePromoted().getPieceColor(), new ImageIcon(colorToBePromoted()+"Bishop.png"), getPieceToBePromoted().getRowPosition(), getPieceToBePromoted().getColumPosition()));
        }
        if (e.getSource() == getQueenButton()) {
            promotePiece(new Queen(setPromotingPlayer(), getPieceToBePromoted().getPieceColor(), new ImageIcon(colorToBePromoted()+"Queen.png"), getPieceToBePromoted().getRowPosition(), getPieceToBePromoted().getColumPosition()));
        }
        getPromotionWindow().dispose();
        ChessGame.getChessGame().setVisible(true);
    }

    public String colorToBePromoted(){
        if (getPieceToBePromoted().getPieceColor() == Color.BLACK){
            return "black";
        }else return "white";
    }

    public Player setPromotingPlayer(){
        return getPieceToBePromoted().getPlayer();
    }

    public void promotePiece(Piece newPiece){
        Chessboard.setStartPointOfPiece(newPiece);
        newPiece.getPlayer().getPlayerPieces().add(newPiece);
        newPiece.checkIfCheckIsMade(newPiece);
    }
}


