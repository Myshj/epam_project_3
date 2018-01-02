package models.commands;

import models.Exposition;
import orm.OrmFieldUtils;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

public class FindExpositionsByNameAndShowroomName extends ListEntitiesCommand<Exposition> {
    public FindExpositionsByNameAndShowroomName(
            Class<Exposition> clazz,
            Connection connection
    ) throws SQLException {
        super(
                clazz, connection,
                String.format(
                        "SELECT * FROM %s WHERE name LIKE ? AND " +
                                "showroom_id IN (SELECT id FROM showrooms WHERE name LIKE ?) ",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
    }

    public FindExpositionsByNameAndShowroomName withName(String name) {
        try {
            statement.setString(1, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindExpositionsByNameAndShowroomName withShowroomName(String name) {
        try {
            statement.setString(2, name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
