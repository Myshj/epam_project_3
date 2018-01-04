package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ResultSetToMapConverter implements Function<ResultSet, Map<String, Object>> {
    @Override
    public Map<String, Object> apply(ResultSet resultSet) {
        Map<String, Object> row = new HashMap<>();
        try {
            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), resultSet.getObject(i));
            }
            return row;
        } catch (SQLException e) {
            return row;
        }
    }
}
