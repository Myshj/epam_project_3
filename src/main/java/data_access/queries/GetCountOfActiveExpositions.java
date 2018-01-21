package data_access.queries;

import models.Exposition;
import models.Showroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.repository.impl.sql.queries.SqlQueryContext;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Get count of currently active expositions.
 */
public class GetCountOfActiveExpositions extends ExpositionCountingByDateAndShowroomQuery {
    private static final Logger logger = LogManager.getLogger(GetCountOfActiveExpositions.class);

    public GetCountOfActiveExpositions(SqlQueryContext<Exposition> context) {
        super(
                context,
                "SELECT COUNT(*) FROM expositions WHERE begins <= ? AND ends >= ? AND showroom_id=?;"
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

    @Override
    public ExpositionCountingByDateAndShowroomQuery withShowroom(Showroom showroom) {
        logger.info("started remembering showroom");
        try {
            statement.setLong(3, showroom.getId().getValue());
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered showroom");
        return this;
    }
}
