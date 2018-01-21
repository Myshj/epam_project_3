package orm.repository.impl.sql.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.queries.IQueryWithNoReturn;

/**
 * Base class for all queries that do not return anything.
 *
 * @param <T>
 */
public abstract class QueryWithNoReturn<T extends Model> extends DbQuery<T> implements IQueryWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(QueryWithNoReturn.class);


    public QueryWithNoReturn(SqlQueryContext<T> context, String sql) {
        super(context, sql);
        logger.info("started construction");
        logger.info("constructed");
    }

    @Override
    public void accept(T entity) {
        try {
            execute(entity);
        } catch (Exception e) {
            logger.error("error during execution");
            logger.error(e);
        }
    }

    protected abstract void execute(T entity) throws Exception;
}
