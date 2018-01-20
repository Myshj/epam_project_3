package models.commands;

import models.Exposition;
import models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.queries.ListEntitiesQuery;
import orm.queries.SqlQueryContext;

import java.sql.SQLException;

/**
 * Select all tickets for exposition.
 */
public class FindTicketsByExposition extends ListEntitiesQuery<Ticket> {
    private static final Logger logger = LogManager.getLogger(FindTicketsByExposition.class);

    public FindTicketsByExposition withExposition(Exposition exposition) {
        logger.info("started remembering exposition");
        try {
            statement.setLong(1, exposition.getId().getValue());
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered exposition");
        return this;
    }

    public FindTicketsByExposition(SqlQueryContext<Ticket> context) {
        super(
                context,
                "SELECT * FROM tickets WHERE exposition_id=?;"
        );
        logger.info("constructed");
    }
}
