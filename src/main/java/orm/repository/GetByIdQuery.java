package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.queries.GetEntityQuery;
import orm.queries.SqlQueryContext;
import utils.meta.functions.GetTableName;

import java.sql.SQLException;

/**
 * Selects entity by id.
 *
 * @param <T>
 */
final class GetByIdQuery<T extends Model> extends GetEntityQuery<T> {
    private static final Logger logger = LogManager.getLogger(GetByIdQuery.class);

    GetByIdQuery(SqlQueryContext<T> context) {
        super(
                context,
                String.format(
                        "SELECT * from %s WHERE id=?;",
                        new GetTableName().apply(context.getClazz())
                )
        );
        logger.info("created");
    }

    GetByIdQuery<T> withId(long id) {
        logger.info("started remembering id");
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered id");
        return this;
    }
}
