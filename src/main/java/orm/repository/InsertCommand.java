package orm.repository;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Writes entity into a new row in a table.
 *
 * @param <T>
 */
final class InsertCommand<T extends Model> {
    private static final Logger logger = LogManager.getLogger(InsertCommand.class);
    private PreparedStatement statement;

    private Map<Field, Integer> queryParametersMap;

    InsertCommand(
            Class<T> clazz,
            Connection connection
    )  {
        logger.info("started construction");
        queryParametersMap = OrmFieldUtils.getUpdateMapping(clazz);

        try {
            statement = connection.prepareStatement(
                    String.format(
                            "INSERT INTO %s (%s) VALUES (%s);",
                            OrmFieldUtils.getTableName(clazz),
                            String.join(
                                    ", ",
                                    queryParametersMap.keySet().stream().sorted(
                                            Comparator.comparing(f -> queryParametersMap.get(f))
                                    ).map(
                                            OrmFieldUtils.getObjectToRelationalMapping(clazz)::get
                                    ).collect(
                                            Collectors.toList()
                                    )
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
        logger.info("constructed");
    }

    void execute(T entity) {
        logger.info("started execution");
        queryParametersMap.forEach(
                (f, n) -> {
                    try {
                        statement.setObject(n, FieldUtils.readField(f, entity, true).toString());
                    } catch (IllegalAccessException | SQLException e) {
                        //e.printStackTrace();
                    }
                }
        );
        try {
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.first();
                FieldUtils.writeField(
                        entity.getId(),
                        "value",
                        rs.getLong(1),
                        true
                );
            } catch (IllegalAccessException e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        logger.info("executed");
        //return rs.getLong(1);
    }
}
