package models.commands;

import models.Exposition;
import models.Ticket;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Select all tickets for exposition.
 */
public class FindTicketsByExposition extends ListEntitiesCommand<Ticket> {
    public FindTicketsByExposition withExposition(Exposition exposition) {
        try {
            statement.setLong(1, exposition.getId().getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindTicketsByExposition(Class<Ticket> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT * FROM tickets WHERE exposition_id=?;"
        );
    }
}
