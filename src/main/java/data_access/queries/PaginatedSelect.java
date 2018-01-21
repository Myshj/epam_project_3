package data_access.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.impl.sql.queries.ListEntitiesQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;

import java.sql.SQLException;

/**
 * Select entities with pagination.
 *
 * @param <T>
 */
public class PaginatedSelect<T extends Model> extends ListEntitiesQuery<T> {
    private static final Logger logger = LogManager.getLogger(PaginatedSelect.class);

    private int parameterCount;

    public PaginatedSelect<T> withPageNumber(int number) {
        logger.info("started remembering pageNumber");
        try {
            statement.setInt(parameterCount - 1, number);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered pageNumber");
        return this;
    }

    public PaginatedSelect<T> withPageSize(int size) {
        logger.info("started remembering pageSize");
        try {
            statement.setInt(parameterCount, size);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered pageSize");
        return this;
    }

    public PaginatedSelect(
            SqlQueryContext<T> context,
            String sql
    ) {
        super(
                context,
                sql.lastIndexOf(';') == -1 ? sql + " LIMIT ?, ?" : sql.substring(0, sql.lastIndexOf(';')) + " LIMIT ?, ?"
        );
        logger.info("started construction");
        try {
            parameterCount = statement.getParameterMetaData().getParameterCount();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("constructed");
    }
}
