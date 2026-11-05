package chessGame.persistence;

import chessGame.PieceColor;
import chessGame.PieceType;
import chessGame.records.CastlingMove;
import chessGame.records.GameSummary;
import chessGame.records.Move;
import chessGame.records.PieceState;
import chessGame.records.SavedGame;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class GameRepository {
    // Distinguish rapid saves/switches even when the system clock has coarse precision.
    private static final String NEXT_UPDATE_TIME = """
            strftime('%Y-%m-%d %H:%M:%f', max(julianday('now'),
                coalesce((SELECT max(julianday(updated_at)) FROM games), 0) + 1.0 / 86400000.0))
            """;
    private final Database database;

    public GameRepository(Database database) {
        this.database = database;
    }

    public long createPlayer(String name) throws IOException, SQLException {
        try (Connection connection = database.openConnection()) {
            return insertPlayer(connection, name);
        }
    }

    private long insertPlayer(Connection connection, String name) throws SQLException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name must not be blank.");
        }

        String sql = "INSERT INTO players (name) VALUES (?) RETURNING id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name.strip());

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    throw new SQLException("Player ID was not returned.");
                }

                return result.getLong("id");
            }
        }
    }

    public long createGame(long whitePlayerId, long blackPlayerId)
            throws IOException, SQLException {
        try (Connection connection = database.openConnection()) {
            return insertGame(connection, whitePlayerId, blackPlayerId);
        }
    }

    private long insertGame(Connection connection, long whitePlayerId, long blackPlayerId)
            throws SQLException {

        String sql = """
                INSERT INTO games (white_player_id, black_player_id, updated_at)
                VALUES (?, ?, %s)
                RETURNING id
                """.formatted(NEXT_UPDATE_TIME);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, whitePlayerId);
            statement.setLong(2, blackPlayerId);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    throw new SQLException("Game ID was not returned.");
                }

                return result.getLong("id");
            }
        }
    }

    /** Starts a local game, reusing the participants of the most recently created game. */
    public SavedGame createLocalGame() throws IOException, SQLException {
        try (Connection connection = database.openConnection()) {
            connection.setAutoCommit(false);
            try {
                long whiteId = 0;
                long blackId = 0;
                try (Statement statement = connection.createStatement();
                     ResultSet result = statement.executeQuery(
                             "SELECT white_player_id, black_player_id FROM games ORDER BY id DESC LIMIT 1")) {
                    if (result.next()) {
                        whiteId = result.getLong("white_player_id");
                        blackId = result.getLong("black_player_id");
                    }
                }
                if (whiteId == 0) {
                    whiteId = insertPlayer(connection, "Player 1");
                    blackId = insertPlayer(connection, "Player 2");
                }
                long id = insertGame(connection, whiteId, blackId);
                SavedGame game = new SavedGame(id, whiteId, blackId, 0, "IN_PROGRESS", List.of());
                connection.commit();
                return game;
            } catch (SQLException | RuntimeException error) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackError) {
                    error.addSuppressed(rollbackError);
                }
                throw error;
            }
        }
    }

    public Optional<SavedGame> findLatestUnfinishedGame() throws IOException, SQLException {
        return readGame("""
                SELECT * FROM games WHERE status = 'IN_PROGRESS'
                ORDER BY updated_at DESC, id DESC LIMIT 1
                """, null);
    }

    public Optional<SavedGame> loadGame(long gameId) throws IOException, SQLException {
        return readGame("SELECT * FROM games WHERE id = ?", gameId);
    }

    public List<GameSummary> listGames() throws IOException, SQLException {
        String sql = """
                SELECT g.id, w.name AS white_name, b.name AS black_name,
                       g.status, g.history_position, g.updated_at,
                       (SELECT count(*) FROM moves m WHERE m.game_id = g.id) AS move_count
                FROM games g
                JOIN players w ON w.id = g.white_player_id
                JOIN players b ON b.id = g.black_player_id
                ORDER BY g.updated_at DESC, g.id DESC
                """;
        List<GameSummary> games = new ArrayList<>();
        try (Connection connection = database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                games.add(new GameSummary(
                        result.getLong("id"), result.getString("white_name"), result.getString("black_name"),
                        result.getString("status"), result.getInt("history_position"),
                        result.getInt("move_count"), result.getString("updated_at")
                ));
            }
        }
        return List.copyOf(games);
    }

    /** Remember an opened game without replacing its moves or history cursor. */
    public void markOpened(long gameId) throws IOException, SQLException {
        String sql = "UPDATE games SET updated_at = " + NEXT_UPDATE_TIME + " WHERE id = ?";
        try (Connection connection = database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, gameId);
            if (statement.executeUpdate() != 1) {
                throw new SQLException("Game not found: " + gameId);
            }
        }
    }

    private Optional<SavedGame> readGame(String sql, Long gameId) throws IOException, SQLException {
        try (Connection connection = database.openConnection()) {
            connection.setAutoCommit(false);
            try {
                Optional<SavedGame> saved = Optional.empty();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    if (gameId != null) {
                        statement.setLong(1, gameId);
                    }
                    try (ResultSet result = statement.executeQuery()) {
                        if (result.next()) {
                            long id = result.getLong("id");
                            saved = Optional.of(new SavedGame(
                                    id,
                                    result.getLong("white_player_id"),
                                    result.getLong("black_player_id"),
                                    result.getInt("history_position"),
                                    result.getString("status"),
                                    readMoves(connection, id)
                            ));
                        }
                    }
                }
                connection.commit();
                return saved;
            } catch (SQLException | RuntimeException error) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackError) {
                    error.addSuppressed(rollbackError);
                }
                throw error;
            }
        }
    }

    private List<Move> readMoves(Connection connection, long gameId) throws SQLException {
        List<Move> moves = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM moves WHERE game_id = ? ORDER BY move_number")) {
            statement.setLong(1, gameId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    if (result.getInt("move_number") != moves.size() + 1) {
                        throw new SQLException("Saved history contains a gap.");
                    }
                    PieceState moving = readPieceState(result, "moving");
                    if (moving == null) {
                        throw new SQLException("Saved move has no moving piece.");
                    }
                    CastlingMove castling = result.getObject("castling_row") == null ? null
                            : new CastlingMove(
                                    result.getInt("castling_row"),
                                    result.getInt("rook_from_column"),
                                    result.getInt("rook_to_column"),
                                    new PieceState(PieceType.ROOK, moving.color(),
                                            result.getInt("rook_first_move") == 1)
                            );
                    String promotion = result.getString("promoted_to");
                    moves.add(new Move(
                            result.getInt("from_row"), result.getInt("from_column"),
                            result.getInt("to_row"), result.getInt("to_column"),
                            moving, readPieceState(result, "captured"), castling,
                            promotion == null ? null : PieceType.valueOf(promotion)
                    ));
                }
            }
        }
        return List.copyOf(moves);
    }

    private PieceState readPieceState(ResultSet result, String prefix) throws SQLException {
        String type = result.getString(prefix + "_type");
        return type == null ? null : new PieceState(
                PieceType.valueOf(type),
                PieceColor.valueOf(result.getString(prefix + "_color")),
                result.getInt(prefix + "_first_move") == 1
        );
    }

    public void saveGame(long gameId, List<Move> moveHistory,
                         int historyPosition, String status)
            throws IOException, SQLException {

        List<Move> history = List.copyOf(moveHistory);

        if (historyPosition < 0 || historyPosition > history.size()) {
            throw new IllegalArgumentException("Invalid history position.");
        }

        if (status == null || !List.of(
                "IN_PROGRESS", "WHITE_WON", "BLACK_WON", "DRAW"
        ).contains(status)) {
            throw new IllegalArgumentException("Invalid game status.");
        }

        String updateSql = """
            UPDATE games
            SET history_position = ?, status = ?, updated_at = %s
            WHERE id = ?
            """.formatted(NEXT_UPDATE_TIME);

        try (Connection connection = database.openConnection()) {
            connection.setAutoCommit(false);

            try {
                try (PreparedStatement statement =
                             connection.prepareStatement(updateSql)) {

                    statement.setInt(1, historyPosition);
                    statement.setString(2, status);
                    statement.setLong(3, gameId);

                    if (statement.executeUpdate() != 1) {
                        throw new SQLException("Game not found: " + gameId);
                    }
                }

                try (PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM moves WHERE game_id = ?")) {

                    statement.setLong(1, gameId);
                    statement.executeUpdate();
                }

                insertMoves(connection, gameId, history);
                connection.commit();

            } catch (SQLException | RuntimeException error) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackError) {
                    error.addSuppressed(rollbackError);
                }

                throw error;
            }
        }
    }

    private void insertMoves(Connection connection, long gameId,
                             List<Move> history) throws SQLException {

        if (history.isEmpty()) {
            return;
        }

        String sql = """
            INSERT INTO moves (
                game_id, move_number,
                from_row, from_column, to_row, to_column,
                moving_type, moving_color, moving_first_move,
                captured_type, captured_color, captured_first_move,
                castling_row, rook_from_column, rook_to_column, rook_first_move,
                promoted_to
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < history.size(); i++) {
                Move move = history.get(i);

                statement.setLong(1, gameId);
                statement.setInt(2, i + 1);

                statement.setInt(3, move.fromRow());
                statement.setInt(4, move.fromColumn());
                statement.setInt(5, move.toRow());
                statement.setInt(6, move.toColumn());

                bindPieceState(statement, 7, move.movingBefore());
                bindPieceState(statement, 10, move.capturedBefore());

                CastlingMove castling = move.castlingMove();

                if (castling == null) {
                    for (int index = 13; index <= 16; index++) {
                        statement.setNull(index, Types.INTEGER);
                    }
                } else {
                    statement.setInt(13, castling.row());
                    statement.setInt(14, castling.fromColumn());
                    statement.setInt(15, castling.toColumn());
                    statement.setInt(
                            16,
                            castling.rookBefore().firstMove() ? 1 : 0
                    );
                }

                if (move.promotedTo() == null) {
                    statement.setNull(17, Types.VARCHAR);
                } else {
                    statement.setString(17, move.promotedTo().name());
                }

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    private void bindPieceState(PreparedStatement statement, int startIndex,
                                PieceState piece) throws SQLException {

        if (piece == null) {
            statement.setNull(startIndex, Types.VARCHAR);
            statement.setNull(startIndex + 1, Types.VARCHAR);
            statement.setNull(startIndex + 2, Types.INTEGER);
        } else {
            statement.setString(startIndex, piece.type().name());
            statement.setString(startIndex + 1, piece.color().name());
            statement.setInt(startIndex + 2, piece.firstMove() ? 1 : 0);
        }
    }
}
