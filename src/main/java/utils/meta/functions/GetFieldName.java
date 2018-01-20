package utils.meta.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.annotations.Column;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * Returns name of column to store field value in.
 */
public class GetFieldName implements Function<Field, String> {
    private static final Logger logger = LogManager.getLogger(GetFieldName.class);

    @Override
    public String apply(Field field) {
        logger.info("chooseNameForField");
        Column columnAnnotation = field.getAnnotation(Column.class);
        return columnAnnotation == null ? field.getName() : columnAnnotation.name();
    }
}
