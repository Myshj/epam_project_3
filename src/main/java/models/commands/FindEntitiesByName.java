package models.commands;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

public class FindEntitiesByName<T extends Model> extends ListEntitiesCommand<T> {
    public FindEntitiesByName(
            Class<T> clazz,
            Connection connection
    ) throws SQLException {
        super(
                clazz, connection,
                String.format(
                        "SELECT * FROM %s WHERE name LIKE ?",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }

    public FindEntitiesByName<T> withName(String name) {
        try {
            statement.setString(1, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
