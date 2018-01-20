package orm.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

import java.sql.ResultSet;
import java.util.function.Supplier;

/**
 * Base class for all queries querying database for single entities.
 *
 * @param <T>
 */
public abstract class GetEntityQuery<T extends Model> extends DbQuery<T> implements Supplier<T> {
    private static final Logger logger = LogManager.getLogger(GetEntityQuery.class);

    public GetEntityQuery(
            SqlQueryContext<T> context,
            String sql
    ) {
        super(context, sql);
        logger.info("constructed");
    }

    @Override
    public T get() {
        try {
            return execute();
        } catch (Exception e) {
            logger.error("exception during execution --> return null");
            logger.error(e);
            return null;
        }
    }

    /**
     */
    protected final T execute() throws Exception {
        logger.info("started execution");
        try (ResultSet rs = statement.executeQuery()) {
            return rs.first() ? converter.apply(rs) : null;
        }
    }
}
