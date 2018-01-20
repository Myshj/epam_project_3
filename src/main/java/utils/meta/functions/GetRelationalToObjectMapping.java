package utils.meta.functions;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.fields.OrmField;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Returns map of (fieldName --> Field of entity class).
 */
public class GetRelationalToObjectMapping implements Function<Class<? extends Model>, Map<String, Field>> {
    private static final Logger logger = LogManager.getLogger(GetRelationalToObjectMapping.class);

    private final GetFieldName fieldNameGetter = new GetFieldName();

    @Override
    public Map<String, Field> apply(Class<? extends Model> clazz) {
        logger.info("started execution");
        Map<String, Field> ret = new HashMap<>();
        FieldUtils.getAllFieldsList(clazz).stream().filter(
                f -> OrmField.class.isAssignableFrom(f.getType())
        ).forEach(
                f -> ret.put(fieldNameGetter.apply(f), f)
        );
        logger.info("executed");
        return ret;
    }
}
