package utils.managers;

import utils.managers.resource.IResourceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.function.Supplier;

/**
 * Stores connection to DB.
 */
public class ConnectionManager implements Supplier<Connection> {
    private Connection connection;

    public ConnectionManager(IResourceManager resourceManager) {
        //ResourceManager rm = ResourceManager.APPLICATION;
        try {
            Class.forName(
                    resourceManager.withKey("db.driver").get()
            );
            connection = DriverManager.getConnection(
                    resourceManager.withKey("db.url").get(),
                    resourceManager.withKey("db.user").get(),
                    resourceManager.withKey("db.password").get()
            );
        } catch (Exception e) {
        }
    }

    @Override
    public Connection get() {
        return connection;
    }
}
