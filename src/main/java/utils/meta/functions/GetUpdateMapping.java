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
 * Returns map of (Field of entity class --> parameter number in update command)
 */
public class GetUpdateMapping implements Function<Class<? extends Model>, Map<Field, Integer>> {
    private static final Logger logger = LogManager.getLogger(GetUpdateMapping.class);

    @Override
    public Map<Field, Integer> apply(Class<? extends Model> clazz) {
        logger.info("entering getUpdateMapping");
        Map<Field, Integer> ret = new HashMap<>();
        FieldUtils.getAllFieldsList(clazz).stream().filter(
                f -> OrmField.class.isAssignableFrom(f.getType()) && !f.getName().equalsIgnoreCase("id")

        ).forEach(
                f -> ret.put(f, (ret.size() + 1))
        );
        logger.info("leaving getUpdateMapping");
        return ret;
    }
}
