package data_access.queries;

import models.Exposition;
import models.Showroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.repository.impl.sql.queries.ListEntitiesQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;

import java.sql.SQLException;

/**
 * Select all expositions in showroom.
 */
public class FindExpositionsByShowroom extends ListEntitiesQuery<Exposition> {
    private static final Logger logger = LogManager.getLogger(FindExpositionsByShowroom.class);

    public FindExpositionsByShowroom withShowroom(Showroom showroom) {
        logger.info("remembering showroom");
        try {
            statement.setLong(1, showroom.getId().getValue());
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered showroom");
        return this;
    }

    public FindExpositionsByShowroom(SqlQueryContext<Exposition> context) {
        super(
                context,
                "SELECT * FROM expositions WHERE showroom_id=?;"
        );
    }
}
