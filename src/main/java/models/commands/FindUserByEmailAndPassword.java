package models.commands;

import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.commands.GetEntityCommand;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Find user with given credentials.
 */
public class FindUserByEmailAndPassword extends GetEntityCommand<User> {
    private static final Logger logger = LogManager.getLogger(FindUserByEmailAndPassword.class);

    public FindUserByEmailAndPassword withEmail(String email) {
        logger.info("started remembering email");
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered email");
        return this;
    }

    public FindUserByEmailAndPassword withPassword(String password) {
        logger.info("started remembering password");
        try {
            statement.setString(2, password);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered password");
        return this;
    }

    public FindUserByEmailAndPassword(Class<User> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT * FROM users WHERE email=? and password=?;"
        );
        logger.info("constructed");
    }
}
