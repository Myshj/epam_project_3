package models.commands;

import models.Exposition;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GetCountOfActiveExpositions extends ExpositionCountingByDateCommand {

    public GetCountOfActiveExpositions(Class<Exposition> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT COUNT(*) FROM expositions WHERE begins <= ? AND ends >= ?;"
        );
    }

    @Override
    public GetCountOfActiveExpositions withDateTime(LocalDateTime dateTime) {

        try {
            statement.setTimestamp(1, Timestamp.valueOf(dateTime));
            statement.setTimestamp(2, Timestamp.valueOf(dateTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
