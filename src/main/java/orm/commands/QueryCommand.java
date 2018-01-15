package orm.commands;

import orm.Model;

import java.sql.Connection;

/**
 * Base class for all querying commands.
 *
 * @param <T>
 */
public abstract class QueryCommand<T extends Model> extends DbCommand<T> {
    public QueryCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) {
        super(clazz, connection, sql);
    }
}
