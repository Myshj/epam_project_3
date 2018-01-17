package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.function.Function;

/**
 * Makes entity from map.
 *
 * @param <T>
 */
public class MapToEntityConverter<T extends Model> implements Function<Map<String, Object>, T> {
    private static final Logger logger = LogManager.getLogger(MapToEntityConverter.class);
    private final FieldFromMapWriter<T> fieldFromMapWriter;
    private Constructor<T> constructor;
    private final DefaultInstantiator<T> instantiator;

    public MapToEntityConverter(Class<T> clazz) {
        constructor = new DefaultConstructorExtractor<T>().apply(clazz);
        fieldFromMapWriter = new FieldFromMapWriter<>(clazz);
        instantiator = new DefaultInstantiator<>(clazz);
    }

    @Override
    public T apply(Map<String, Object> map) {
        try {
            return fieldFromMapWriter.apply(instantiator.get(), map);
        } catch (Exception e) {
            logger.error("error occured --> return null");
            logger.error(e);
            return null;
        }
    }
}
