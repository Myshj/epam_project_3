package models.commands;

import models.User;
import orm.commands.GetEntityCommand;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Find user with given credentials.
 */
public class FindUserByEmailAndPassword extends GetEntityCommand<User> {
    public FindUserByEmailAndPassword withEmail(String email) {
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindUserByEmailAndPassword withPassword(String password) {
        try {
            statement.setString(2, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FindUserByEmailAndPassword(Class<User> clazz, Connection connection) throws SQLException {
        super(
                clazz, connection,
                "SELECT * FROM users WHERE email=? and password=?;"
        );
    }
}
