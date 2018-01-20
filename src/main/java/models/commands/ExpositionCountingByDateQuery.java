package models.commands;

import models.Exposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.queries.CountingQuery;
import orm.queries.SqlQueryContext;

import java.time.LocalDateTime;

/**
 * Base class for all queries to count expositions using given date.
 */
public abstract class ExpositionCountingByDateQuery extends CountingQuery<Exposition> {
    private static final Logger logger = LogManager.getLogger(ExpositionCountingByDateQuery.class);

    public ExpositionCountingByDateQuery(SqlQueryContext<Exposition> context, String sql) {
        super(context, sql);
        logger.info("constructed");
    }

    public abstract ExpositionCountingByDateQuery withDateTime(LocalDateTime dateTime);
}
