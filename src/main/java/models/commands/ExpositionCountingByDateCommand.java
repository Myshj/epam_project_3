package models.commands;

import models.Exposition;
import orm.commands.CountingCommand;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Base class for all commands to count expositions using given date.
 */
public abstract class ExpositionCountingByDateCommand extends CountingCommand<Exposition> {
    public ExpositionCountingByDateCommand(Class<Exposition> clazz, Connection connection, String sql) throws SQLException {
        super(clazz, connection, sql);
    }

    public abstract ExpositionCountingByDateCommand withDateTime(LocalDateTime dateTime);
}
