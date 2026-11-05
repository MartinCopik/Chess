package chessGame.records;

public record CastlingMove(
        int row,
        int fromColumn,
        int toColumn,
        PieceState rookBefore
) {
}
