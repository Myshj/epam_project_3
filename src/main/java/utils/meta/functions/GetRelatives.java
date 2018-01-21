package utils.meta.functions;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import utils.meta.annotations.Relatives;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Returns all related classes referenced with ForeignKeys of models.
 */
public class GetRelatives implements Function<Class<? extends Model>, Map<String, Class<? extends Model>>> {
    private static final Logger logger = LogManager.getLogger(GetRelatives.class);
    @Override
    public Map<String, Class<? extends Model>> apply(Class<? extends Model> clazz) {
        logger.info("entering");
        return new HashMap<String, Class<? extends Model>>() {{
            for (Field field : FieldUtils.getFieldsWithAnnotation(clazz, Relatives.class)) {
                put(
                        field.getAnnotation(Relatives.class).pluralName(),
                        (Class<? extends Model>) new GetActualParameterClass().apply(field)
                );
            }
            logger.info("leaving");
        }};
    }
}
