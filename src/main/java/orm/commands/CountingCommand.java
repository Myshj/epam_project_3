package orm.commands;

import orm.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Base class for all commands querying database for count of entities.
 *
 * @param <T>
 */
public abstract class CountingCommand<T extends Model> extends DbCommand<T> {
    public CountingCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    )  {
        super(clazz, connection, sql);
    }

    /**
     * @return count of entities or null if SQLException occured.
     */
    public Long execute() {
        try (ResultSet rs = statement.executeQuery()) {
            rs.first();
            return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
