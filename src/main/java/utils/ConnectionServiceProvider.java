package utils;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * Stores connection to database.
 */
public enum ConnectionServiceProvider implements Supplier<Connection> {
    INSTANCE;

    private final ConnectionService service = new ConnectionService();

    @Override
    public Connection get() {
        return service.get();
    }

}
