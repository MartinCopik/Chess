package chessGame;

import chessGame.persistence.GameRepository;
import chessGame.records.GameSummary;
import chessGame.records.SavedGame;
import chessGame.screens.SavedGamesDialog;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.OptionalLong;

/** Coordinates switching between saved games on the Swing event thread. */
public final class GameController {
    private final GameRepository repository;
    private Chessboard board;
    private SavedGame currentGame;

    public GameController(GameRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public Chessboard start(SavedGame game) throws IOException, SQLException {
        if (board != null) {
            throw new IllegalStateException("A game is already open.");
        }
        return replaceBoard(game);
    }

    public Chessboard getBoard() {
        return board;
    }

    public boolean newGame() {
        if (!saveBeforeSwitch()) return false;
        try {
            long id = repository.createGame(currentGame.whitePlayerId(), currentGame.blackPlayerId());
            replaceBoard(new SavedGame(id, currentGame.whitePlayerId(), currentGame.blackPlayerId(),
                    0, "IN_PROGRESS", List.of()));
            return true;
        } catch (IOException | SQLException | RuntimeException error) {
            showError("The new game could not be started. Your current game is still open.", error);
            return false;
        }
    }

    public boolean openSavedGame(long gameId) {
        return saveBeforeSwitch() && loadSelectedGame(gameId);
    }

    public void chooseSavedGame() {
        if (!saveBeforeSwitch()) return;
        try {
            List<GameSummary> games = repository.listGames();
            OptionalLong selected = SavedGamesDialog.choose(board.getChessGame(), games, currentGame.id());
            if (selected.isPresent()) {
                loadSelectedGame(selected.getAsLong());
            }
        } catch (IOException | SQLException | RuntimeException error) {
            showError("Saved games could not be listed. Your current game is still open.", error);
        }
    }

    private boolean saveBeforeSwitch() {
        return board != null && !board.getGameManager().getPromotionPending()
                && board.getGameManager().saveCurrentGame();
    }

    private boolean loadSelectedGame(long gameId) {
        if (gameId == currentGame.id()) return true;
        try {
            SavedGame game = repository.loadGame(gameId)
                    .orElseThrow(() -> new SQLException("The selected game no longer exists."));
            replaceBoard(game);
            return true;
        } catch (IOException | SQLException | RuntimeException error) {
            showError("The selected game could not be opened. Your current game is still open.", error);
            return false;
        }
    }

    private Chessboard replaceBoard(SavedGame game) throws IOException, SQLException {
        Chessboard next = new Chessboard(false);
        try {
            next.getGameManager().restoreGame(game);
            next.getGameManager().configurePersistence(repository, game.id());
            next.configureGameActions(this::newGame, this::chooseSavedGame);
            repository.markOpened(game.id());
            next.getChessGame().setVisible(true);
        } catch (IOException | SQLException | RuntimeException error) {
            next.dispose();
            throw error;
        }
        Chessboard previous = board;
        board = next;
        currentGame = game;
        if (previous != null) previous.dispose();
        return board;
    }

    private void showError(String message, Exception error) {
        error.printStackTrace();
        JOptionPane.showMessageDialog(board.getChessGame(), message, "Game could not be opened", JOptionPane.ERROR_MESSAGE);
    }
}
