package orm.commands;

import orm.Model;
import utils.MapToEntityConverter;
import utils.ResultSetToMapConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * Base class for all database commands.
 *
 * @param <T> Type of related entity.
 */
public abstract class DbCommand<T extends Model> {
    protected PreparedStatement statement;
    Function<ResultSet, T> converter;

    /**
     * Constructor.
     * @param clazz Class of related entity.
     * @param connection Connection to execute command in.
     * @param sql Statement to execute.
     * @throws RuntimeException if unable to prepare statement using connection.
     */
    public DbCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) {
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        converter = new MapToEntityConverter<>(clazz).compose(new ResultSetToMapConverter());
    }
}
