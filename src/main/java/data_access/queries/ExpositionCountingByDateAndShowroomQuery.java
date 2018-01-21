package data_access.queries;

import models.Exposition;
import models.Showroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.repository.impl.sql.queries.CountingQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;

import java.time.LocalDateTime;

/**
 * Base class for all queries to count expositions using given date.
 */
public abstract class ExpositionCountingByDateAndShowroomQuery extends CountingQuery<Exposition> {
    private static final Logger logger = LogManager.getLogger(ExpositionCountingByDateAndShowroomQuery.class);

    public ExpositionCountingByDateAndShowroomQuery(SqlQueryContext<Exposition> context, String sql) {
        super(context, sql);
        logger.info("constructed");
    }

    public abstract ExpositionCountingByDateAndShowroomQuery withDateTime(LocalDateTime dateTime);

    public abstract ExpositionCountingByDateAndShowroomQuery withShowroom(Showroom showroom);
}
