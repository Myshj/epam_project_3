package orm.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Base class for all commands querying database for single entities.
 *
 * @param <T>
 */
public abstract class GetEntityCommand<T extends Model> extends QueryCommand<T> {
    private static final Logger logger = LogManager.getLogger(GetEntityCommand.class);

    public GetEntityCommand(
            CommandContext<T> context,
            String sql
    ) {
        super(context, sql);
        logger.info("constructed");
    }

    /**
     * @return Optional describing returned entity or Optional.empty() if SQLException occured.
     */
    public final Optional<T> execute() {
        logger.info("started execution");
        try (ResultSet rs = statement.executeQuery()) {
            try {
                logger.info("executed");
                return rs.first() ? Optional.ofNullable(converter.apply(rs)) : Optional.empty();
            } catch (SQLException e) {
                logger.error("returned nothing");
                logger.error(e);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("returned nothing");
            logger.error(e);
            return Optional.empty();
        }
    }
}
