import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chessboard  {

    public static JPanel panelBoard;
    static JPanel discardedPieces;

    public static Piece[][][] arrayBoard = new Piece[8][8][2];
    public static ArrayList<Piece> arrayDiscardedPieces = new ArrayList<>();

    public static Piece[][][] getArrayBoard() {
        return arrayBoard;
    }

    private void boardInitialization(){
        for (int row = 0; row  < arrayBoard.length; row ++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                if (row%2 == 0) {
                    arrayBoard[row][colum][0] = new EmptyPiece( Color.WHITE, row, colum);
                    arrayBoard[row][colum+1][0] = new EmptyPiece(Color.BLACK, row, colum+1);
                    colum++;
                }else {
                    arrayBoard[row][colum][0] = new EmptyPiece( Color.BLACK, row, colum);
                    arrayBoard[row][colum+1][0] = new EmptyPiece( Color.WHITE, row, colum+1);
                    colum++;
                }
            }
        }
    }

    private void setStartPointOfPieces(){
         arrayBoard[0][0][1] = new Rook(Color.BLACK,new ImageIcon("blackRook.png"));
         arrayBoard[0][1][1] = new Knight(Color.BLACK, new ImageIcon("blackKnight.png"));
         arrayBoard[0][2][1] = new Bishop(Color.BLACK, new ImageIcon("blackBishop.png"));
         arrayBoard[0][3][1] = new Queen(Color.BLACK, new ImageIcon("blackQueen.png"));
         arrayBoard[0][4][1] = new King(Color.BLACK, new ImageIcon("blackKing.png"));
         arrayBoard[0][5][1] = new Bishop(Color.BLACK, new ImageIcon("blackBishop.png"));
         arrayBoard[0][6][1] = new Knight(Color.BLACK, new ImageIcon("blackKnight.png"));
         arrayBoard[0][7][1] = new Rook(Color.BLACK, new ImageIcon("blackRook.png"));

         arrayBoard[1][0][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][1][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][2][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][3][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][4][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][5][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][6][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));
         arrayBoard[1][7][1] = new Pawn(Color.BLACK, new ImageIcon("blackPawn.png"));

        arrayBoard[7][0][1]= new Rook(Color.WHITE, new ImageIcon("whiteRook.png"));
        arrayBoard[7][1][1]= new Knight(Color.WHITE, new ImageIcon("whiteKnight.png"));
        arrayBoard[7][2][1]= new Bishop(Color.WHITE, new ImageIcon("whiteBishop.png"));
        arrayBoard[7][3][1]= new Queen(Color.WHITE, new ImageIcon("whiteQueen.png"));
        arrayBoard[7][4][1]= new King(Color.WHITE, new ImageIcon("whiteKing.png"));
        arrayBoard[7][5][1]= new Bishop(Color.WHITE, new ImageIcon("whiteBishop.png"));
        arrayBoard[7][6][1]= new Knight(Color.WHITE, new ImageIcon("whiteKnight.png"));
        arrayBoard[7][7][1]= new Rook(Color.WHITE, new ImageIcon("whiteRook.png"));

        arrayBoard[6][0][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][1][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][2][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][3][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][4][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][5][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][6][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
        arrayBoard[6][7][1] = new Pawn(Color.WHITE, new ImageIcon("whitePawn.png"));
    }

    private void addEmptyPiecesToFrame(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                    panelBoard.add(arrayBoard[row][colum][0].emptyPiecePanel);
            }
        }
    }

    private void scaleImageOfPiece(Piece piece){
         Image scaledImage = piece.pieceImageIcon.getImage().getScaledInstance(arrayBoard[0][0][0].emptyPiecePanel.getWidth(), arrayBoard[0][0][0].emptyPiecePanel.getHeight(),Image.SCALE_SMOOTH);
         piece.pieceImageIcon = new ImageIcon(scaledImage);
         piece.pieceLabel.setIcon(piece.pieceImageIcon);

    }

    private void setPieceImages(){

            for (int colum = 0; colum < arrayBoard.length; colum++){
                scaleImageOfPiece(arrayBoard[0][colum][1]);
                scaleImageOfPiece(arrayBoard[1][colum][1]);
                scaleImageOfPiece(arrayBoard[6][colum][1]);
                scaleImageOfPiece(arrayBoard[7][colum][1]);

                arrayBoard[0][colum][0].emptyPiecePanel.add(arrayBoard[0][colum][1].pieceLabel);
                arrayBoard[1][colum][0].emptyPiecePanel.add(arrayBoard[1][colum][1].pieceLabel);
                arrayBoard[6][colum][0].emptyPiecePanel.add(arrayBoard[6][colum][1].pieceLabel);
                arrayBoard[7][colum][0].emptyPiecePanel.add(arrayBoard[7][colum][1].pieceLabel);
            }

    }
    public static void setColors(){
        for (int row = 0; row < arrayBoard.length; row++){
            for (int colum = 0; colum < arrayBoard.length; colum++){
                arrayBoard[row][colum][0].emptyPiecePanel.setBackground(arrayBoard[row][colum][0].pieceColor);
            }
        }
        panelBoard.repaint();
    }


    Chessboard(){
        panelBoard = new JPanel();
        discardedPieces = new JPanel();

        panelBoard.setLayout(new GridLayout(8,8));
        discardedPieces.setLayout(new GridLayout());

        boardInitialization();
        setStartPointOfPieces();
        addEmptyPiecesToFrame();

        setPieceImages();
    }
}

