package orm.repository;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.GetEntityCommand;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Selects entity by id.
 *
 * @param <T>
 */
final class GetByIdCommand<T extends Model> extends GetEntityCommand<T> {

    GetByIdCommand(
            Class<T> clazz,
            Connection connection
    )  {
        super(
                clazz, connection,
                String.format(
                        "SELECT * from %s WHERE id=?;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }

    GetByIdCommand<T> withId(long id) {
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
