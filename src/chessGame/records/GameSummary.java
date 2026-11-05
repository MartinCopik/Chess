package chessGame.records;

public record GameSummary(
        long id,
        String whitePlayerName,
        String blackPlayerName,
        String status,
        int historyPosition,
        int moveCount,
        String updatedAt
) {}
