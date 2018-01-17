package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Converts ResultSet into a map.
 */
public class ResultSetToMapConverter implements Function<ResultSet, Map<String, Object>> {
    private static final Logger logger = LogManager.getLogger(ResultSetToMapConverter.class);

    @Override
    public Map<String, Object> apply(ResultSet resultSet) {
        logger.info("started execution");
        Map<String, Object> row = new HashMap<>();
        try {
            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), resultSet.getObject(i));
            }
            logger.info("executed");
            return row;
        } catch (Exception e) {
            logger.error("error occured --> return null");
            logger.error(e);
            return null;
        }
    }
}
