package models.commands;

import models.Exposition;
import models.Showroom;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FindActiveExpositionsByShowroom extends ListEntitiesCommand<Exposition> {
    public FindActiveExpositionsByShowroom withDateTime(LocalDateTime dateTime) {

        try {
            statement.setTimestamp(3, Timestamp.valueOf(dateTime));
            statement.setTimestamp(2, Timestamp.valueOf(dateTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindActiveExpositionsByShowroom withShowroom(Showroom showroom) {
        try {
            statement.setLong(1, showroom.getId().getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindActiveExpositionsByShowroom(Class<Exposition> clazz, Connection connection) throws SQLException {
        super(clazz, connection, "SELECT * FROM expositions WHERE showroom_id=? AND begins <= ? AND ends >= ?;");
    }
}
