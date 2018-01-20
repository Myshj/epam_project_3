package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.queries.ListEntitiesQuery;
import orm.queries.SqlQueryContext;
import utils.meta.functions.GetTableName;

/**
 * Returns ALL stored entities of such class.
 *
 * @param <T>
 */
final class GetAllQuery<T extends Model> extends ListEntitiesQuery<T> {
    private static final Logger logger = LogManager.getLogger(GetAllQuery.class);

    GetAllQuery(SqlQueryContext<T> context) {
        super(
                context,
                String.format(
                        "SELECT * FROM %s;",
                        new GetTableName().apply(context.getClazz())
                )
        );
        logger.info("created");
    }
}
