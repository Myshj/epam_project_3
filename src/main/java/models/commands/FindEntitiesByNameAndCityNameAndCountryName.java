package models.commands;

import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

public class FindEntitiesByNameAndCityNameAndCountryName<T extends Model> extends ListEntitiesCommand<T> {
    public FindEntitiesByNameAndCityNameAndCountryName(
            Class<T> clazz,
            Connection connection
    ) throws SQLException {
        super(
                clazz, connection,
                String.format(
                        "SELECT * FROM %s WHERE name LIKE ? AND " +
                                "city_id IN (SELECT id FROM cities WHERE name LIKE ? AND " +
                                "country_id IN (SELECT id FROM countries WHERE name LIKE ?)) ",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }

    public FindEntitiesByNameAndCityNameAndCountryName<T> withName(String name) {
        try {
            statement.setString(1, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindEntitiesByNameAndCityNameAndCountryName<T> withCityName(String name) {
        try {
            statement.setString(2, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindEntitiesByNameAndCityNameAndCountryName<T> withCountryName(String name) {
        try {
            statement.setString(3, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
