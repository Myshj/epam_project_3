package orm.repository;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.queries.QueryWithNoReturn;
import orm.queries.SqlQueryContext;
import utils.meta.functions.GetObjectToRelationalMapper;
import utils.meta.functions.GetTableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Writes entity into a new row in a table.
 *
 * @param <T>
 */
final class InsertQuery<T extends Model> extends QueryWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(InsertQuery.class);


    @Override
    protected PreparedStatement prepareStatement(String sql) {
        try {
            return context.getConnection().prepareStatement(
                    String.format(
                            sql,
                            new GetTableName().apply(context.getClazz()),
                            String.join(
                                    ", ",
                                    queryParametersMap.keySet().stream()
                                            .sorted(Comparator.comparing(queryParametersMap::get))
                                            .map(new GetObjectToRelationalMapper().apply(context.getClazz())::get)
                                            .collect(Collectors.toList())
                            ),
                            String.join(
                                    ", ",
                                    Collections.nCopies(
                                            queryParametersMap.size(),
                                            "?"
                                    )
                            )
                    ),
                    Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException e) {
            logger.error("SQLException occured. Rethrowing.");
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    InsertQuery(SqlQueryContext<T> context) {
        super(
                context,
                "INSERT INTO %s (%s) VALUES (%s);"
        );
        logger.info("started construction");

        logger.info("constructed");
    }

    @Override
    protected void execute(T entity) throws Exception {
        logger.info("started execution");
        queryParametersMap.forEach(
                (f, n) -> {
                    try {
                        statement.setObject(n, FieldUtils.readField(f, entity, true).toString());
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
        );
        statement.executeUpdate();
        try (ResultSet rs = statement.getGeneratedKeys()) {
            rs.first();
            FieldUtils.writeField(
                    entity.getId(),
                    "value",
                    rs.getLong(1),
                    true
            );
        }
        logger.info("executed");
        //return rs.getLong(1);
    }
}
