package orm.repository;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CountingCommand;

import java.sql.Connection;
import java.sql.SQLException;

public final class CountAllCommand<T extends Model> extends CountingCommand<T> {
    public CountAllCommand(Class<T> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                String.format(
                        "SELECT COUNT(*) FROM %s;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }
}
