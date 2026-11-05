package chessGame;

public enum PieceColor {
    BLACK, WHITE;

    public PieceColor opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
