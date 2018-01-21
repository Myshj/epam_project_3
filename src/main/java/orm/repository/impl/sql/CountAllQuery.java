package orm.repository.impl.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.impl.sql.queries.CountingQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;
import utils.meta.functions.GetTableName;

/**
 * Counts ALL entities in a table.
 *
 * @param <T>
 */
final class CountAllQuery<T extends Model> extends CountingQuery<T> {
    private static final Logger logger = LogManager.getLogger(CountAllQuery.class);

    CountAllQuery(SqlQueryContext<T> context) {
        super(
                context,
                String.format(
                        "SELECT COUNT(*) FROM %s;",
                        new GetTableName().apply(context.getClazz())
                )
        );
        logger.info("created");
    }
}
