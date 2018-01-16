package models.commands;

import models.Exposition;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Get count of not started expositions.
 */
public class GetCountOfPlannedExpositions extends ExpositionCountingByDateCommand {
    @Override
    public ExpositionCountingByDateCommand withDateTime(LocalDateTime dateTime) {
        try {
            statement.setTimestamp(1, Timestamp.valueOf(dateTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public GetCountOfPlannedExpositions(Class<Exposition> clazz, Connection connection) throws SQLException {
        super(clazz, connection, "SELECT COUNT(*) FROM expositions WHERE begins > ?;");
    }
}
