import javax.swing.*;
import java.awt.*;

public enum ChessPiece{
    ROOK_WHITE_LEFT(Color.BLACK, "whiteRook.png", 7, 0){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getRookMoves().rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    ROOK_WHITE_RIGHT(Color.WHITE, "whiteRook.png", 7,7){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getRookMoves().rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    ROOK_BLACK_LEFT(Color.BLACK, "blackRook.png", 0, 0){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getRookMoves().rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    ROOK_BLACK_RIGHT(Color.BLACK, "blackRook.png", 0, 7){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getRookMoves().rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_WHITE_LEFT(Color.WHITE, "whiteKnight.png", 7, 1){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getKnightMoves().knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_WHITE_RIGHT(Color.WHITE, "whiteKnight.png", 7, 6){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getKnightMoves().knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_BLACK_LEFT(Color.BLACK, "blackKnight.png", 0, 1){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getKnightMoves().knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_BLACK_RIGHT(Color.BLACK, "blackKnight.png", 0, 6){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getKnightMoves().knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_WHITE_LEFT(Color.WHITE, "whiteBishop.png", 7, 2){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getBishopMoves().bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_WHITE_RIGHT(Color.WHITE, "whiteBishop.png", 7, 5){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getBishopMoves().bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_BLACK_LEFT(Color.BLACK, "blackBishop.png", 0, 2){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getBishopMoves().bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_BLACK_RIGHT(Color.BLACK, "blackBishop.png", 0, 5){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getBishopMoves().bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    QUEEN_WHITE(Color.WHITE, "whiteQueen.png", 7, 3){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getQueenMoves().queenMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    QUEEN_BLACK(Color.BLACK, "blackQueen.png", 0, 3){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getQueenMoves().queenMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    KING_WHITE(Color.WHITE, "whiteKing.png", 7, 4){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getKingMoves().kingMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    KING_BLACK(Color.BLACK, "blackKing.png", 0, 4){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getKingMoves().kingMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },

    PAWN_WHITE_0(Color.WHITE, "whitePawn.png", 6, 0){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_1(Color.WHITE, "whitePawn.png", 6, 1){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_2(Color.WHITE, "whitePawn.png", 6, 2){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_3(Color.WHITE, "whitePawn.png", 6, 3){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_4(Color.WHITE, "whitePawn.png", 6, 4){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_5(Color.WHITE, "whitePawn.png", 6, 5){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_6(Color.WHITE, "whitePawn.png", 6, 6){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_7(Color.WHITE, "whitePawn.png", 6, 7){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_0(Color.BLACK, "blackPawn.png", 1, 0){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_1(Color.BLACK, "blackPawn.png", 1, 1){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_2(Color.BLACK, "blackPawn.png", 1, 2){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_3(Color.BLACK, "blackPawn.png", 1, 3){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_4(Color.BLACK, "blackPawn.png", 1, 4){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_5(Color.BLACK, "blackPawn.png", 1, 5){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_6(Color.BLACK, "blackPawn.png", 1, 6){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_7(Color.BLACK, "blackPawn.png", 1, 7){

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            getPawnMoves().blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    };

    private final Move move = new Move();
    private final PawnMoves pawnMoves = new PawnMoves();
    private final RookMoves rookMoves = new RookMoves();
    private final KnightMoves knightMoves = new KnightMoves();
    private final BishopMoves bishopMoves = new BishopMoves();
    private final QueenMoves queenMoves = new QueenMoves();
    private final KingMoves kingMoves = new KingMoves();

    private Color pieceColor;
    private ImageIcon pieceImageIcon;
    private final JLabel pieceLabel;
    private int rowPosition;
    private int columnPosition;
    private boolean pieceFirstMove = true;

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

    public void showMovePossibilities(Chessboard chessboard){
    }

    public Move getMove() {
        return move;
    }

    public PawnMoves getPawnMoves() {
        return pawnMoves;
    }

    public RookMoves getRookMoves() {
        return rookMoves;
    }

    public KnightMoves getKnightMoves() {
        return knightMoves;
    }

    public BishopMoves getBishopMoves() {
        return bishopMoves;
    }

    public QueenMoves getQueenMoves() {
        return queenMoves;
    }

    public KingMoves getKingMoves() {
        return kingMoves;
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

    public boolean getPieceFirstMove() {
        return pieceFirstMove;
    }

    public void setPieceFirstMove(boolean pieceFirstMove) {
        this.pieceFirstMove = pieceFirstMove;
    }
}
