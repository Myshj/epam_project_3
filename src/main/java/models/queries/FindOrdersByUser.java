package models.queries;

import models.Order;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.queries.ListEntitiesQuery;
import orm.queries.SqlQueryContext;

import java.sql.SQLException;

public class FindOrdersByUser extends ListEntitiesQuery<Order> {
    private static final Logger logger = LogManager.getLogger(FindOrdersByUser.class);

    public FindOrdersByUser withUser(User user) {
        logger.info("started remembering user");
        try {
            statement.setLong(1, user.getId().getValue());
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered user");
        return this;
    }

    public FindOrdersByUser(SqlQueryContext<Order> context) {
        super(
                context,
                "SELECT * FROM orders WHERE user_id=?;"
        );
    }
}
