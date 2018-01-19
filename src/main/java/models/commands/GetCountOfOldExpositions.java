package models.commands;

import models.Exposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.commands.CommandContext;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Get count of over expositions.
 */
public class GetCountOfOldExpositions extends ExpositionCountingByDateCommand {
    private static final Logger logger = LogManager.getLogger(GetCountOfOldExpositions.class);

    public GetCountOfOldExpositions(CommandContext<Exposition> context) throws SQLException {
        super(
                context,
                "SELECT COUNT(*) FROM expositions WHERE ends < ?;"
        );
        logger.info("constructed");
    }

    @Override
    public ExpositionCountingByDateCommand withDateTime(LocalDateTime dateTime) {
        logger.info("started remembering dateTime");
        try {
            statement.setTimestamp(1, Timestamp.valueOf(dateTime));
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered dateTime");
        return this;
    }
}
