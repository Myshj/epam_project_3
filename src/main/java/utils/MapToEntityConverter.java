package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.queries.SqlQueryContext;
import utils.converters.EntityFromMapWriter;

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
    private final EntityFromMapWriter<T> entityFromMapWriter;
    private Constructor<T> constructor;
    private final DefaultInstantiator<T> instantiator;
    private final SqlQueryContext<T> context;

    public MapToEntityConverter(
            SqlQueryContext<T> context
    ) {
        this.context = context;
        constructor = new DefaultConstructorExtractor<T>().apply(context.getClazz());
        entityFromMapWriter = new EntityFromMapWriter<>(context);
        instantiator = new DefaultInstantiator<>(context.getClazz());
    }

    @Override
    public T apply(Map<String, Object> map) {
        try {
            return entityFromMapWriter.apply(instantiator.get(), map);
        } catch (Exception e) {
            logger.error("error occured --> return null");
            logger.error(e);
            return null;
        }
    }
}
