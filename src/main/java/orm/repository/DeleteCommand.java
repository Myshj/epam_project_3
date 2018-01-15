package orm.repository;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandWithNoReturn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Deletes entity from database.
 *
 * @param <T>
 */
final class DeleteCommand<T extends Model> extends CommandWithNoReturn<T> {
    public DeleteCommand(Class<T> clazz, Connection connection) {
        super(
                clazz, connection,
                String.format(
                        "DELETE FROM %s WHERE id=?;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }

    @Override
    public final void execute(T entity) {
        try {
            statement.setLong(1, entity.getId().get().get());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
