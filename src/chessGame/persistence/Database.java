package chessGame.persistence;

import org.sqlite.SQLiteConfig;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    private final Path databasePath;

    public Database() {
        this(Path.of(
                System.getProperty("chess.dataDir", "data"),"chess.db"));
    }

    public Database(Path databasePath) {
        this.databasePath = databasePath.toAbsolutePath().normalize();
    }

    public Path getPath() {
        return databasePath;
    }

    public Connection openConnection() throws IOException, SQLException {
        Files.createDirectories(databasePath.getParent());

        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        return DriverManager.getConnection(
                "jdbc:sqlite:" + databasePath,
                config.toProperties()
        );
    }

    public void initialize() throws IOException, SQLException {
        String schema;

        try (InputStream input =
                     Database.class.getResourceAsStream("/db/schema.sql")) {

            if (input == null) {
                throw new IOException("Missing resource: /db/schema.sql");
            }

            schema = new String(
                    input.readAllBytes(),
                    StandardCharsets.UTF_8
            );
        }

        try (Connection connection = openConnection()) {
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(schema);
                connection.commit();
            } catch (SQLException error) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackError) {
                    error.addSuppressed(rollbackError);
                }

                throw error;
            }
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Database database = new Database();
        database.initialize();

        System.out.println("Database ready: " + database.getPath());
    }
}