package orm.commands;

import orm.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Base class for all commands querying database for single entities.
 *
 * @param <T>
 */
public abstract class GetEntityCommand<T extends Model> extends QueryCommand<T> {
    public GetEntityCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) {
        super(clazz, connection, sql);
    }

    /**
     * @return Optional describing returned entity or Optional.empty() if SQLException occured.
     */
    public final Optional<T> execute() {
        try (ResultSet rs = statement.executeQuery()) {
            try {
                return rs.first() ? Optional.ofNullable(converter.apply(rs)) : Optional.empty();
            } catch (SQLException e) {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
