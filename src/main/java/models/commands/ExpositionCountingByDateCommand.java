package models.commands;

import models.Exposition;
import orm.commands.CountingCommand;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class ExpositionCountingByDateCommand extends CountingCommand<Exposition> {
    public ExpositionCountingByDateCommand(Class<Exposition> clazz, Connection connection, String sql) throws SQLException {
        super(clazz, connection, sql);
    }

    public abstract ExpositionCountingByDateCommand withDateTime(LocalDateTime dateTime);
}
