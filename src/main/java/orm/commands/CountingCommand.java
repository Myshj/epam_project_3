package orm.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Base class for all commands querying database for count of entities.
 *
 * @param <T>
 */
public abstract class CountingCommand<T extends Model> extends DbCommand<T> {
    private static final Logger logger = LogManager.getLogger(CountingCommand.class);

    public CountingCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    )  {
        super(clazz, connection, sql);
        logger.info("constructed");
    }

    /**
     * @return count of entities or null if SQLException occured.
     */
    public Long execute() {
        logger.info("started execution");
        try (ResultSet rs = statement.executeQuery()) {
            rs.first();
            logger.info("executed");
            return rs.getLong(1);
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}
