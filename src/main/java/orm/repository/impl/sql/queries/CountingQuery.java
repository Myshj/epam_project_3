package orm.repository.impl.sql.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.queries.ICountingQuery;

import java.sql.ResultSet;

/**
 * Base class for all queries querying database for count of entities.
 *
 * @param <T>
 */
public abstract class CountingQuery<T extends Model> extends DbQuery<T> implements ICountingQuery<T> {
    private static final Logger logger = LogManager.getLogger(CountingQuery.class);

    public CountingQuery(
            SqlQueryContext<T> context,
            String sql
    ) {
        super(context, sql);
        logger.info("constructed");
    }

    @Override
    public Long get() {
        try {
            return execute();
        } catch (Exception e) {
            logger.error("exception during execution --> return null");
            logger.error(e);
            return null;
        }
    }

    /**
     * @return count of entities or null if SQLException occured.
     */
    protected Long execute() throws Exception {
        logger.info("started execution");
        try (ResultSet rs = statement.executeQuery()) {
            rs.first();
            logger.info("executed");
            return rs.getLong(1);
        }
    }
}
