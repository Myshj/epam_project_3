package orm.commands;

import orm.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Base class for all commands querying database for lists of entities.
 *
 * @param <T>
 */
public abstract class ListEntitiesCommand<T extends Model> extends QueryCommand<T> {
    public ListEntitiesCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) {
        super(clazz, connection, sql);
    }

    /**
     *
     * @return returned entities or empty list if SQLException occured.
     */
    public final List<T> execute() {
        List<T> result = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Optional.ofNullable(converter.apply(rs)).ifPresent(result::add);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
