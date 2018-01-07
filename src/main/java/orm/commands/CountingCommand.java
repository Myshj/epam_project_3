package orm.commands;

import orm.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountingCommand<T extends Model> extends DbCommand<T> {
    public CountingCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) throws SQLException {
        super(clazz, connection, sql);
    }

    public long execute() throws SQLException {
        try (ResultSet rs = statement.executeQuery()) {
            rs.first();
            return rs.getLong(1);
        }
    }
}
