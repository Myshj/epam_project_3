package models.commands;

import models.Exposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.commands.CommandContext;
import orm.commands.CountingCommand;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Base class for all commands to count expositions using given date.
 */
public abstract class ExpositionCountingByDateCommand extends CountingCommand<Exposition> {
    private static final Logger logger = LogManager.getLogger(ExpositionCountingByDateCommand.class);

    public ExpositionCountingByDateCommand(CommandContext<Exposition> context, String sql) throws SQLException {
        super(context, sql);
        logger.info("constructed");
    }

    public abstract ExpositionCountingByDateCommand withDateTime(LocalDateTime dateTime);
}
