package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.function.Supplier;

public class ConnectionService implements Supplier<Connection> {
    private Connection connection;

    ConnectionService() {
        ResourceManager rm = ResourceManager.APPLICATION;
        try {
            Class.forName(
                    rm.get("db.driver")
            );
            connection = DriverManager.getConnection(
                    rm.get("db.url"),
                    rm.get("db.user"),
                    rm.get("db.password")
            );
        } catch (Exception e) {
        }
    }

    @Override
    public Connection get() {
        return connection;
    }
}
