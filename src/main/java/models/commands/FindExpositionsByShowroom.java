package models.commands;

import models.Exposition;
import models.Showroom;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Select all expositions in showroom.
 */
public class FindExpositionsByShowroom extends ListEntitiesCommand<Exposition> {
    public FindExpositionsByShowroom withShowroom(Showroom showroom) {
        try {
            statement.setLong(1, showroom.getId().getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindExpositionsByShowroom(Class<Exposition> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT * FROM expositions WHERE showroom_id=?;"
        );
    }
}
