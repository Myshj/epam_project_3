package utils.transactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.function.Function;

public class TransactionExecutor implements Function<Runnable, TransactionResult> {
    private static final Logger logger = LogManager.getLogger(TransactionExecutor.class);
    private final Connection connection;

    public TransactionExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TransactionResult apply(Runnable command) {
        logger.info("started execution");
        synchronized (connection) {
            try {
                boolean oldAutoCommit = connection.getAutoCommit();
                connection.setAutoCommit(false);
                command.run();
                connection.commit();
                connection.setAutoCommit(oldAutoCommit);
                logger.info("committed");
                return TransactionResult.COMMIT;
            } catch (Exception e) {
                try {
                    connection.rollback();
                    logger.error("error occured --> rolled back");
                    logger.error(e);
                    return TransactionResult.ROLLBACK;
                } catch (Exception e1) {
                    logger.error("error while rolling back");
                    logger.error(e1);
                    return TransactionResult.ERROR_WHILE_ROLLBACK;
                }
            }
        }
    }
}
