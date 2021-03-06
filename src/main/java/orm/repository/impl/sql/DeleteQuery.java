package orm.repository.impl.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.impl.sql.queries.QueryWithNoReturn;
import orm.repository.impl.sql.queries.SqlQueryContext;
import utils.meta.functions.GetTableName;

/**
 * Deletes entity from database.
 *
 * @param <T>
 */
final class DeleteQuery<T extends Model> extends QueryWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(DeleteQuery.class);

    DeleteQuery(SqlQueryContext<T> context) {
        super(
                context,
                String.format(
                        "DELETE FROM %s WHERE id=?;",
                        new GetTableName().apply(context.getClazz())
                )
        );
        logger.info("created");
    }

    @Override
    protected final void execute(T entity) throws Exception {
        logger.info("started execution");
        statement.setLong(1, entity.getId().get().get());
        statement.executeUpdate();
        logger.info("executed");
    }
}
