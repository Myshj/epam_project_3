package data_access.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.impl.sql.queries.ListEntitiesQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;
import utils.meta.functions.GetTableName;

import java.sql.SQLException;

/**
 * Select entities with pagination.
 *
 * @param <T>
 */
public class PaginatedSelectAll<T extends Model> extends ListEntitiesQuery<T> {
    private static final Logger logger = LogManager.getLogger(PaginatedSelectAll.class);

    public PaginatedSelectAll<T> withParameters(int number, int size) {
        logger.info("started remembering parameters");
        try {
            statement.setInt(1, number * size);
            statement.setInt(2, size);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered parameters");
        return this;
    }


    public PaginatedSelectAll(
            SqlQueryContext<T> context
    ) {
        super(
                context,
                String.format(
                        "SELECT * FROM %s LIMIT ?, ?;",
                        new GetTableName().apply(context.getClazz())
                )
        );
        logger.info("constructed");
    }
}
