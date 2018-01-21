package orm.repository.impl.sql;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.impl.sql.queries.QueryWithNoReturn;
import orm.repository.impl.sql.queries.SqlQueryContext;
import utils.meta.functions.GetObjectToRelationalMapper;
import utils.meta.functions.GetTableName;
import utils.meta.functions.GetUpdateMapping;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Writes entity fields to the corresponding record in database.
 *
 * @param <T>
 */
final class UpdateQuery<T extends Model> extends QueryWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(UpdateQuery.class);

    UpdateQuery(SqlQueryContext<T> context) {
        super(
                context,
                ((Supplier<String>) () -> {
                    GetUpdateMapping getUpdateMapping = new GetUpdateMapping();
                    GetObjectToRelationalMapper getObjectToRelationalMapper = new GetObjectToRelationalMapper();
                    GetTableName getTableName = new GetTableName();
                    return String.format(
                            "UPDATE %s SET %s WHERE id=?",
                            getTableName.apply(context.getClazz()),
                            String.join(
                                    ", ",
                                    String.join(
                                            ", ",
                                            getUpdateMapping.apply(context.getClazz()).keySet().stream()
                                                    .sorted(
                                                            Comparator.comparing(
                                                                    f -> getUpdateMapping.apply(context.getClazz())
                                                                            .get(f)
                                                            )
                                                    )
                                                    .map(f -> getObjectToRelationalMapper.apply(context.getClazz()).get(f) + "= ?")
                                                    .collect(Collectors.toList())
                                    )
                            )
                    );
                }).get()
        );
        logger.info("constructed");
    }

    @Override
    protected final void execute(T entity) {
        logger.info("started execution");
        queryParametersMap.forEach(
                (f, n) -> {
                    try {
                        statement.setObject(n, FieldUtils.readField(f, entity, true).toString());
                    } catch (IllegalAccessException | SQLException e) {
                        //e.printStackTrace();
                        logger.error(e);
                    }
                }
        );
        try {
            statement.setLong(queryParametersMap.size() + 1, entity.getId().get().get());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("executed");
    }
}
