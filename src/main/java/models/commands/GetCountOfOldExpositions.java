package models.commands;

import models.Exposition;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GetCountOfOldExpositions extends ExpositionCountingByDateCommand {
    public GetCountOfOldExpositions(Class<Exposition> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT COUNT(*) FROM expositions WHERE ends < ?;"
        );
    }

    @Override
    public ExpositionCountingByDateCommand withDateTime(LocalDateTime dateTime) {
        try {
            statement.setTimestamp(1, Timestamp.valueOf(dateTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
