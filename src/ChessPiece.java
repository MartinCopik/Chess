import javax.swing.*;
import java.awt.*;

public enum ChessPiece{
    ROOK_WHITE_LEFT(Color.BLACK, "whiteRook.png", 7, 0){
    },
    ROOK_WHITE_RIGHT(Color.WHITE, "whiteRook.png", 7,7){

    },
    ROOK_BLACK_LEFT(Color.BLACK, "blackRook.png", 0, 0){

    },
    ROOK_BLACK_RIGHT(Color.BLACK, "blackRook.png", 0, 7){

    },
    KNIGHT_WHITE_LEFT(Color.WHITE, "whiteKnight.png", 7, 1){},
    KNIGHT_WHITE_RIGHT(Color.WHITE, "whiteKnight.png", 7, 6){},
    KNIGTH_BLACK_LEFT(Color.BLACK, "blackKnight.png", 0, 1){},
    KNIGHT_BLACK_RIGHT(Color.BLACK, "blackKnight.png", 0, 6){
    },
    BISHOP_WHITE_LEFT(Color.WHITE, "whiteBishop.png", 7, 2){},
    BISHOP_WHITE_RIGHT(Color.WHITE, "whiteBishop.png", 7, 5){},
    BISHOP_BLACK_LEFT(Color.BLACK, "blackBishop.png", 0, 2){},
    BISHOP_BLACK_RIGHT(Color.BLACK, "blackBishop.png", 0, 5){},
    QUEEN_WHITE(Color.WHITE, "whiteQueen.png", 7, 3){},
    QUEEN_BLACK(Color.BLACK, "blackQueen.png", 0, 3){},
    KING_WHITE(Color.WHITE, "whiteKing.png", 7, 4){},
    KING_BLACK(Color.BLACK, "blackKing.png", 0, 4){},

    PAWN_WHITE_0(Color.WHITE, "whitepawn.png", 6, 0){},
    PAWN_WHITE_1(Color.WHITE, "whitepawn.png", 6, 1){},
    PAWN_WHITE_2(Color.WHITE, "whitepawn.png", 6, 2){},
    PAWN_WHITE_3(Color.WHITE, "whitepawn.png", 6, 3){},
    PAWN_WHITE_4(Color.WHITE, "whitepawn.png", 6, 4){},
    PAWN_WHITE_5(Color.WHITE, "whitepawn.png", 6, 5){},
    PAWN_WHITE_6(Color.WHITE, "whitepawn.png", 6, 6){},
    PAWN_WHITE_7(Color.WHITE, "whitepawn.png", 6, 7){},
    PAWN_BLACK_0(Color.BLACK, "blackpawn.png", 1, 0){},
    PAWN_BLACK_1(Color.BLACK, "blackpawn.png", 1, 1){},
    PAWN_BLACK_2(Color.BLACK, "blackpawn.png", 1, 2){},
    PAWN_BLACK_3(Color.BLACK, "blackpawn.png", 1, 3){},
    PAWN_BLACK_4(Color.BLACK, "blackpawn.png", 1, 4){},
    PAWN_BLACK_5(Color.BLACK, "blackpawn.png", 1, 5){},
    PAWN_BLACK_6(Color.BLACK, "blackpawn.png", 1, 6){},
    PAWN_BLACK_7(Color.BLACK, "blackpawn.png", 1, 7){};






    ChessPiece(Color pieceColor, String iconPath, int rowPosition, int columnPosition){
        setPieceColor(pieceColor);
        setPieceImageIcon(iconPath);
        this.pieceLabel = new JLabel();
        setRowPosition(rowPosition);
        setColumnPosition(columnPosition);
    }
    public void scaleImageOfPiece(int widthOfPiece, int heightOfPiece){
        Image scaledImage = this.getPieceImageIcon().getImage().getScaledInstance(widthOfPiece, heightOfPiece,Image.SCALE_SMOOTH);
        this.getPieceImageIcon().setImage(scaledImage);
        this.getPieceLabel().setIcon(this.getPieceImageIcon());
    }

    private Color pieceColor;
    private ImageIcon pieceImageIcon;
    private JLabel pieceLabel;
    private int rowPosition;
    private int columnPosition;
    private boolean pieceFirstMove = true;


    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ImageIcon getPieceImageIcon() {
        return pieceImageIcon;
    }

    public void setPieceImageIcon(String iconPath) {
        this.pieceImageIcon = new ImageIcon(iconPath);
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

}
