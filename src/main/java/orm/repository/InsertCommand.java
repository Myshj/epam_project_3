package orm.repository;

import org.apache.commons.lang3.reflect.FieldUtils;
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
    private PreparedStatement statement;

    private Map<Field, Integer> queryParametersMap;

    InsertCommand(
            Class<T> clazz,
            Connection connection
    )  {
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
            throw new RuntimeException(e);
        }
    }

    void execute(T entity) {
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
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //return rs.getLong(1);
    }
}
