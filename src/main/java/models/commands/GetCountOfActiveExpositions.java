package models.commands;

import models.Exposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Get count of currently active expositions.
 */
public class GetCountOfActiveExpositions extends ExpositionCountingByDateCommand {
    private static final Logger logger = LogManager.getLogger(GetCountOf / currentlyActiveExpositions.class);

    public GetCountOfActiveExpositions(Class<Exposition> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT COUNT(*) FROM expositions WHERE begins <= ? AND ends >= ?;"
        );
        logger.info("constructed");
    }

    @Override
    public GetCountOfActiveExpositions withDateTime(LocalDateTime dateTime) {
        logger.info("started remembering dateTime");
        try {
            statement.setTimestamp(1, Timestamp.valueOf(dateTime));
            statement.setTimestamp(2, Timestamp.valueOf(dateTime));
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered dateTime");
        return this;
    }
}
