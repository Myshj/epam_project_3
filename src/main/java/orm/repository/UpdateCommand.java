package orm.repository;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandWithNoReturn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Writes entity fields to the corresponding record in database.
 *
 * @param <T>
 */
final class UpdateCommand<T extends Model> extends CommandWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(UpdateCommand.class);

    public UpdateCommand(
            Class<T> clazz,
            Connection connection
    ) {
        super(
                clazz, connection,
                String.format(
                        "UPDATE %s SET %s WHERE id=?",
                        OrmFieldUtils.getTableName(clazz),
                        String.join(
                                ", ",
                                String.join(
                                        ", ",
                                        OrmFieldUtils.getUpdateMapping(clazz).keySet().stream().sorted(
                                                Comparator.comparing(f -> OrmFieldUtils.getUpdateMapping(clazz).get(f))
                                        ).map(
                                                f -> OrmFieldUtils.getObjectToRelationalMapping(clazz).get(f) + "= ?"
                                        ).collect(Collectors.toList())
                                )
                        )
                )
        );
        logger.info("constructed");
    }

    public final void execute(T entity) {
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
