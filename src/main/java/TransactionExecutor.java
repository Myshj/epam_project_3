import java.sql.Connection;
import java.sql.SQLException;

public class TransactionExecutor {
    private Connection connection;

    public TransactionExecutor(Connection connection) {
        this.connection = connection;
    }

    public void execute(Runnable command) throws SQLException {
        try {
            boolean oldAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            command.run();
            connection.commit();
            connection.setAutoCommit(oldAutoCommit);
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
