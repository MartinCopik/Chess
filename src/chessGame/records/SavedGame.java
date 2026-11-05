package chessGame.records;

import java.util.List;

public record SavedGame(
        long id,
        long whitePlayerId,
        long blackPlayerId,
        int historyPosition,
        String status,
        List<Move> moves
) {
    public SavedGame {
        moves = List.copyOf(moves);
        if (id <= 0 || whitePlayerId <= 0 || blackPlayerId <= 0) {
            throw new IllegalArgumentException("Invalid saved game ID.");
        }
        if (historyPosition < 0 || historyPosition > moves.size()) {
            throw new IllegalArgumentException("Invalid saved history position.");
        }
        if (status == null || !List.of(
                "IN_PROGRESS", "WHITE_WON", "BLACK_WON", "DRAW"
        ).contains(status)) {
            throw new IllegalArgumentException("Invalid saved game status.");
        }
    }
}
