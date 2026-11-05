package chessGame;

import chessPieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    private final Toolkit tk = Toolkit.getDefaultToolkit();
    private final int widthFrame = ((int) tk.getScreenSize().getWidth());
    private final int heightFrame = ((int) tk.getScreenSize().getHeight());

    private final JFrame chessGame;
    private final JPanel chessboard;

    private final ChessSquare[][] arrayBoard = new ChessSquare[8][8];
    private ArrayList<ChessPiece> listOfPieces = new ArrayList<>();

    private ChessPiece selectedPieceToMove;
    private GameManager gameManager = new GameManager(this);
    private ChessPiecesPackage chessPiecesPackage;

    private JPanel historyControls;
    private final JButton backButton = new JButton("Back");
    private final JButton forwardButton = new JButton("Forward");
    private final JButton newGameButton = new JButton("New game");
    private final JButton savedGamesButton = new JButton("Saved games");
    private final JLabel historyStatus = new JLabel();
    private boolean gameActionsConfigured;

    public Chessboard() {
        this(true);
    }

    public Chessboard(boolean visible){

        chessGame = new JFrame("Chess");
        chessGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessGame.setSize(getWidthFrame(), getHeightFrame());
        chessGame.setResizable(false);

        chessboard = new JPanel();
        chessboard.setLayout(new GridLayout(8,8));

        boardInitialization();
        addChessPiecesToList();
        setAllChessPiecesOnBoard();
        addEmptyPiecesToFrame();

        historyControls = new JPanel(new FlowLayout(FlowLayout.CENTER));
        historyControls.add(newGameButton);
        historyControls.add(savedGamesButton);
        backButton.addActionListener(event -> gameManager.undoMove());
        forwardButton.addActionListener(event -> gameManager.redoMove());
        historyControls.add(backButton);
        historyControls.add(forwardButton);
        historyControls.add(historyStatus);

        chessGame.add(historyControls, BorderLayout.NORTH);
        chessGame.add(chessboard, BorderLayout.CENTER);
        updateHistoryControls();
        chessGame.setVisible(visible);
    }

    public void updateHistoryControls() {
        boolean canSwitch = gameActionsConfigured && !gameManager.getPromotionPending();
        newGameButton.setEnabled(canSwitch);
        savedGamesButton.setEnabled(canSwitch);
        backButton.setEnabled(gameManager.canUndo());
        forwardButton.setEnabled(gameManager.canRedo());
        String status;
        if (gameManager.getPromotionPending()) {
            status = "Choose a promotion piece";
        } else if (gameManager.isGameOver()) {
            status = "Game over";
        } else {
            status = (gameManager.getCurrentPlayerColor() == PieceColor.WHITE ? "White" : "Black") + " to move";
        }
        historyStatus.setText("Move " + gameManager.getHistoryPosition() + "/"
                + gameManager.getMoveHistory().size() + " | " + status);
    }

    public void refreshBoard() {
        setColors();
        chessboard.revalidate();
        chessboard.repaint();
    }

    public void configureGameActions(Runnable newGame, Runnable savedGames) {
        if (gameActionsConfigured) {
            throw new IllegalStateException("Game actions are already configured.");
        }
        newGameButton.addActionListener(event -> newGame.run());
        savedGamesButton.addActionListener(event -> savedGames.run());
        gameActionsConfigured = true;
        updateHistoryControls();
    }

    public void dispose() {
        gameManager.closeResultWindow();
        chessGame.dispose();
    }

    private void boardInitialization(){
        for (int row = 0; row  < arrayBoard.length; row ++){
            for (int column = 0; column < arrayBoard.length; column++){
                if (row%2 == 0) {
                    if (column%2 == 0){
                        arrayBoard[row][column] = new ChessSquare(Color.WHITE, row, column, widthFrame/8, heightFrame/8, this);
                    }else {
                        arrayBoard[row][column] = new ChessSquare(Color.BLACK, row, column, widthFrame/8, heightFrame/8, this);
                    }
                }else {
                    if (column%2 == 0){
                        arrayBoard[row][column] = new ChessSquare(Color.BLACK, row, column, widthFrame/8, heightFrame/8, this);
                    }else {
                        arrayBoard[row][column] = new ChessSquare(Color.WHITE, row, column, widthFrame/8, heightFrame/8, this);
                    }
                }
            }
        }
    }

    private void addChessPiecesToList(){
        chessPiecesPackage = new  ChessPiecesPackage(listOfPieces);
    }

    /**
     * scale image for all chess pieces
     * set starting position for all chess pieces
     * set movement map for all chess pieces
     */
    private void setAllChessPiecesOnBoard(){
        listOfPieces.forEach(this::scalePieceImage);
        listOfPieces.forEach(chessPiece -> arrayBoard[chessPiece.getRowPosition()][chessPiece.getColumnPosition()].setPieceOnSquare(chessPiece));
    }

    public void scalePieceImage(ChessPiece piece) {
        piece.scaleImageOfPiece(widthFrame / 16, heightFrame / 16);
    }

    private void addEmptyPiecesToFrame(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int column = 0; column < arrayBoard.length; column++){
                chessboard.add(getArrayBoard()[row][column].getEmptyPiecePanel());
            }
        }
    }

    /**
     * color the chessboard
     */
    public  void setColors(){
        for (int row = 0; row < getArrayBoard().length; row++){
            for (int column = 0; column < getArrayBoard().length; column++){
                arrayBoard[row][column].getEmptyPiecePanel().setBackground(getArrayBoard()[row][column].getEmptyPieceColor());
            }
        }
        chessboard.repaint();
    }

    public int getWidthFrame() {
        return widthFrame;
    }

    public int getHeightFrame() {
        return heightFrame;
    }

    public JFrame getChessGame() {
        return chessGame;
    }

    public  ChessSquare[][] getArrayBoard() {
        return arrayBoard;
    }

    public ArrayList<ChessPiece> getListOfPieces() {
        return listOfPieces;
    }

    public ChessPiece getSelectedPieceToMove() {
        return selectedPieceToMove;
    }

    public void setSelectedPieceToMove(ChessPiece selectedPieceToMove) {
        this.selectedPieceToMove = selectedPieceToMove;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ChessPiecesPackage getChessPiecesPackage() {
        return chessPiecesPackage;
    }
}
