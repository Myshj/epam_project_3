package orm.commands;

import orm.Model;
import utils.MapToEntityConverter;
import utils.ResultSetToMapConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class DbCommand<T extends Model> {
    protected PreparedStatement statement;
    Function<ResultSet, T> converter;

    public DbCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) throws SQLException {
        statement = connection.prepareStatement(sql);
        converter = new MapToEntityConverter<>(clazz).compose(new ResultSetToMapConverter());
    }
}
