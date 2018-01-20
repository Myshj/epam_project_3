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
 * Works reversed to GetRelationalToObjectMapping.
 */
public class GetObjectToRelationalMapper implements Function<Class<? extends Model>, Map<Field, String>> {

    private static final Logger logger = LogManager.getLogger(GetObjectToRelationalMapper.class);

    private final GetFieldName fieldNameGetter = new GetFieldName();

    @Override
    public Map<Field, String> apply(Class<? extends Model> clazz) {
        logger.info("entering getObjectToRelationalMapping");
        Map<Field, String> ret = new HashMap<>();
        FieldUtils.getAllFieldsList(clazz).stream().filter(
                f -> OrmField.class.isAssignableFrom(f.getType())

        ).forEach(
                f -> ret.put(f, fieldNameGetter.apply(f))
        );
        logger.info("leaving getObjectToRelationalMapping");
        return ret;
    }
}
