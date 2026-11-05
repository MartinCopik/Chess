package chessGame;

import chessGame.persistence.Database;
import chessGame.persistence.GameRepository;
import chessGame.records.SavedGame;
import chessGame.screens.SavedGamesDialog;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.OptionalLong;

public class MainClass {

    public static void main(String[] args) {
        Database database;
        try {
            database = new Database();
            database.initialize();
        } catch (IOException | SQLException | RuntimeException error) {
            error.printStackTrace();
            SwingUtilities.invokeLater(MainClass::showDatabaseError);
            return;
        }

        GameRepository repository = new GameRepository(database);
        try {
            Optional<SavedGame> unfinished = repository.findLatestUnfinishedGame();
            SavedGame game = unfinished.isPresent() ? unfinished.get() : repository.createLocalGame();

            SwingUtilities.invokeLater(() -> openGame(repository, game));
        } catch (IOException | SQLException | RuntimeException error) {
            error.printStackTrace();
            SwingUtilities.invokeLater(() -> recoverStartup(repository));
        }
    }

    static Chessboard openGame(GameRepository repository, SavedGame game) {
        try {
            return new GameController(repository).start(game);
        } catch (IOException | SQLException | RuntimeException error) {
            error.printStackTrace();
            return recoverStartup(repository);
        }
    }

    /** Runs on the EDT; no game is saved or replaced until the user chooses an action. */
    private static Chessboard recoverStartup(GameRepository repository) {
        String message = "The last game could not be opened.";
        String[] options = {"Saved games", "New game", "Exit"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    message + "\nChoose another saved game or start a new one.\n"
                            + "Existing saved games have not been replaced.",
                    "Game recovery",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (choice != 0 && choice != 1) return null;

            try {
                SavedGame game;
                if (choice == 0) {
                    message = "Saved games could not be listed.";
                    var games = repository.listGames();
                    message = "No game was opened.";
                    OptionalLong selected = SavedGamesDialog.choose(null, games, -1);
                    if (selected.isEmpty()) continue;
                    message = "The selected game could not be opened.";
                    game = repository.loadGame(selected.getAsLong())
                            .orElseThrow(() -> new SQLException("The selected game no longer exists."));
                } else {
                    message = "The new game could not be started.";
                    game = repository.createLocalGame();
                }
                // Call start directly: another failure returns to this loop, without nesting recovery dialogs.
                return new GameController(repository).start(game);
            } catch (IOException | SQLException | RuntimeException error) {
                error.printStackTrace();
            }
        }
    }

    private static void showDatabaseError() {
        JOptionPane.showMessageDialog(
                null,
                "The database could not be opened. Check the database and data folder, then try again.\n"
                        + "Existing saved games have not been replaced.",
                "Startup failed",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
