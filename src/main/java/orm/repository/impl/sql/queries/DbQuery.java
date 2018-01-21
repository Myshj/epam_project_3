package orm.repository.impl.sql.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import utils.converters.EntityResultSetToMapConverter;
import utils.converters.MapToEntityConverter;
import utils.meta.functions.GetUpdateMapping;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;

/**
 * Base class for all database queries.
 *
 * @param <T> Type of related entity.
 */
public abstract class DbQuery<T extends Model> {
    private static final Logger logger = LogManager.getLogger(DbQuery.class);
    protected final SqlQueryContext<T> context;

    protected final Map<Field, Integer> queryParametersMap;
    protected PreparedStatement statement;
    Function<ResultSet, T> converter;

    protected PreparedStatement prepareStatement(String sql) {
        try {
            return context.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            logger.error("unable to prepare statement");
            logger.error(e);
            return null;
        }
    }

    /**
     * Constructor.
     * @param sql Statement to execute.
     */
    public DbQuery(
            SqlQueryContext<T> context,
            String sql
    ) {
        logger.info("started construction");
        this.context = context;
        queryParametersMap = new GetUpdateMapping().apply(context.getClazz());
        statement = prepareStatement(sql);
        converter = new MapToEntityConverter<>(context).compose(new EntityResultSetToMapConverter());

        logger.info("constructed");
    }
}
