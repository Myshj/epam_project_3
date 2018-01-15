package orm.repository;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CountingCommand;

import java.sql.Connection;

/**
 * Counts ALL entities in a table.
 *
 * @param <T>
 */
public final class CountAllCommand<T extends Model> extends CountingCommand<T> {
    public CountAllCommand(Class<T> clazz, Connection connection) {
        super(
                clazz, connection,
                String.format(
                        "SELECT COUNT(*) FROM %s;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }
}
