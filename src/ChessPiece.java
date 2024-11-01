import javax.swing.*;
import java.awt.*;

public enum ChessPiece{
    ROOK_WHITE_LEFT(Color.BLACK, "whiteRook.png", 7, 0){
        RookMoves rookMoves = new RookMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            rookMoves.rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    ROOK_WHITE_RIGHT(Color.WHITE, "whiteRook.png", 7,7){
        RookMoves rookMoves = new RookMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            rookMoves.rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    ROOK_BLACK_LEFT(Color.BLACK, "blackRook.png", 0, 0){
        RookMoves rookMoves = new RookMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            rookMoves.rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    ROOK_BLACK_RIGHT(Color.BLACK, "blackRook.png", 0, 7){
        RookMoves rookMoves = new RookMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            rookMoves.rookMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_WHITE_LEFT(Color.WHITE, "whiteKnight.png", 7, 1){
        KnightMoves knightMoves = new KnightMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            knightMoves.knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_WHITE_RIGHT(Color.WHITE, "whiteKnight.png", 7, 6){
        KnightMoves knightMoves = new KnightMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            knightMoves.knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_BLACK_LEFT(Color.BLACK, "blackKnight.png", 0, 1){
        KnightMoves knightMoves = new KnightMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            knightMoves.knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    KNIGHT_BLACK_RIGHT(Color.BLACK, "blackKnight.png", 0, 6){
        KnightMoves knightMoves = new KnightMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            knightMoves.knightMoves(chessboard,this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_WHITE_LEFT(Color.WHITE, "whiteBishop.png", 7, 2){
        BishopMoves bishopMoves = new BishopMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            bishopMoves.bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_WHITE_RIGHT(Color.WHITE, "whiteBishop.png", 7, 5){
        BishopMoves bishopMoves = new BishopMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            bishopMoves.bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_BLACK_LEFT(Color.BLACK, "blackBishop.png", 0, 2){
        BishopMoves bishopMoves = new BishopMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            bishopMoves.bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    BISHOP_BLACK_RIGHT(Color.BLACK, "blackBishop.png", 0, 5){
        BishopMoves bishopMoves = new BishopMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            bishopMoves.bishopMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    QUEEN_WHITE(Color.WHITE, "whiteQueen.png", 7, 3){
        QueenMoves queenMoves = new QueenMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            queenMoves.queenMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    QUEEN_BLACK(Color.BLACK, "blackQueen.png", 0, 3){
        QueenMoves queenMoves = new QueenMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            queenMoves.queenMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    KING_WHITE(Color.WHITE, "whiteKing.png", 7, 4){
        KingMoves kingMoves = new KingMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            kingMoves.kingMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    KING_BLACK(Color.BLACK, "blackKing.png", 0, 4){
        KingMoves kingMoves = new KingMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            kingMoves.kingMoves(chessboard, this, getRowPosition(), getColumnPosition());
        }
    },

    PAWN_WHITE_0(Color.WHITE, "whitepawn.png", 6, 0){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_1(Color.WHITE, "whitepawn.png", 6, 1){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_2(Color.WHITE, "whitepawn.png", 6, 2){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_3(Color.WHITE, "whitepawn.png", 6, 3){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_4(Color.WHITE, "whitepawn.png", 6, 4){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_5(Color.WHITE, "whitepawn.png", 6, 5){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_6(Color.WHITE, "whitepawn.png", 6, 6){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_WHITE_7(Color.WHITE, "whitepawn.png", 6, 7){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.whitePawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_0(Color.BLACK, "blackpawn.png", 1, 0){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_1(Color.BLACK, "blackpawn.png", 1, 1){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_2(Color.BLACK, "blackpawn.png", 1, 2){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_3(Color.BLACK, "blackpawn.png", 1, 3){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_4(Color.BLACK, "blackpawn.png", 1, 4){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_5(Color.BLACK, "blackpawn.png", 1, 5){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_6(Color.BLACK, "blackpawn.png", 1, 6){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    },
    PAWN_BLACK_7(Color.BLACK, "blackpawn.png", 1, 7){
        PawnMoves pawnMoves = new PawnMoves();

        @Override
        public void showMovePossibilities(Chessboard chessboard) {
            pawnMoves.blackPawnMoves(getPieceFirstMove(), chessboard, this, getRowPosition(), getColumnPosition());
        }
    };


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

    private Move move = new Move();

    private Color pieceColor;
    private ImageIcon pieceImageIcon;
    private JLabel pieceLabel;
    private int rowPosition;
    private int columnPosition;
    private boolean pieceFirstMove = true;


    public Move getMove() {
        return move;
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

//    public void knightMoveUpShortLeft(Chessboard chessboard){
//        getMove().isMoveValid(this, getRowPosition()-1, getColumnPosition()-2, chessboard);
//    }
//    public void knightMoveUpShortRight(Chessboard chessboard){
//        getMove().isMoveValid(this, getRowPosition() - 1, getColumnPosition() + 2, chessboard);
//    }
//    public void knightMoveUpLongLeft(Chessboard chessboard){
//        getMove().isMoveValid(this, getRowPosition()-2, getColumnPosition()-1, chessboard);
//    }
//    public void knightMoveUpLongRight(Chessboard chessboard){
//        getMove().isMoveValid(this,getRowPosition() - 2, getColumnPosition() + 1, chessboard);
//    }
//    public void knightMoveDownShortLeft(Chessboard chessboard){
//        getMove().isMoveValid(this,getRowPosition()+1, getColumnPosition()-2, chessboard);
//    }
//    public void knightMoveDownShortRight(Chessboard chessboard){
//        getMove().isMoveValid(this,getRowPosition()+1, getColumnPosition()+2, chessboard);
//    }
//    public void knightMoveDownLongLeft(Chessboard chessboard){
//        getMove().isMoveValid(this,getRowPosition()+2, getColumnPosition()-1, chessboard);
//    }
//    public void knightMoveDownLongRight(Chessboard chessboard){
//        getMove().isMoveValid(this,getRowPosition()+2, getColumnPosition()+1, chessboard);
//    }

}
