package orm.repository;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;

/**
 * Returns ALL stored entities of such class.
 *
 * @param <T>
 */
final class GetAllCommand<T extends Model> extends ListEntitiesCommand<T> {
    GetAllCommand(Class<T> clazz, Connection connection) {
        super(
                clazz, connection,
                String.format(
                        "SELECT * FROM %s;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }
}
