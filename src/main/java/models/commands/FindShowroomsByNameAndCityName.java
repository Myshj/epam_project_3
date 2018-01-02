package models.commands;

import models.Showroom;
import orm.OrmFieldUtils;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

public class FindShowroomsByNameAndCityName extends ListEntitiesCommand<Showroom> {
    public FindShowroomsByNameAndCityName(
            Class<Showroom> clazz,
            Connection connection
    ) throws SQLException {
        super(
                clazz, connection,
                String.format(
                        "SELECT * FROM %s WHERE name LIKE ? AND " +
                                "building_id IN (SELECT id FROM buildings WHERE " +
                                "street_id IN (SELECT id FROM streets WHERE " +
                                "city_id IN (SELECT id FROM cities WHERE name LIKE ?))) ",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }

    public FindShowroomsByNameAndCityName withName(String name) {
        try {
            statement.setString(1, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindShowroomsByNameAndCityName withCityName(String name) {
        try {
            statement.setString(2, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
