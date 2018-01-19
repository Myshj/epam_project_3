package orm.repository;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandContext;
import orm.commands.CommandWithNoReturn;

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
final class InsertCommand<T extends Model> extends CommandWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(InsertCommand.class);


    @Override
    protected PreparedStatement prepareStatement(String sql) {
        try {
            return context.getConnection().prepareStatement(
                    String.format(
                            sql,
                            OrmFieldUtils.getTableName(context.getClazz()),
                            String.join(
                                    ", ",
                                    queryParametersMap.keySet().stream().sorted(
                                            Comparator.comparing(queryParametersMap::get)
                                    ).map(
                                            OrmFieldUtils.getObjectToRelationalMapping(context.getClazz())::get
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
    }

    InsertCommand(CommandContext<T> context) {
        super(
                context,
                "INSERT INTO %s (%s) VALUES (%s);"
        );
        logger.info("started construction");

        logger.info("constructed");
    }

    @Override
    public void execute(T entity) {
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
