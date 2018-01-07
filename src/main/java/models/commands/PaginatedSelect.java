package models.commands;

import orm.Model;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.sql.SQLException;

public class PaginatedSelect<T extends Model> extends ListEntitiesCommand<T> {
    private int parameterCount;

    public PaginatedSelect<T> withPageNumber(int number) {
        try {
            statement.setInt(parameterCount - 1, number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PaginatedSelect<T> withPageSize(int size) {
        try {
            statement.setInt(parameterCount, size);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PaginatedSelect(
            Class<T> clazz,
            Connection connection,
            String sql
    ) throws SQLException {
        super(
                clazz, connection,
                sql.lastIndexOf(';') == -1 ? sql + " LIMIT ?, ?" : sql.substring(0, sql.lastIndexOf(';')) + " LIMIT ?, ?"
        );
        parameterCount = statement.getParameterMetaData().getParameterCount();
    }
}
