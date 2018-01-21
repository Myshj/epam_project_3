package utils.converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.impl.sql.queries.SqlQueryContext;
import utils.factories.generic.DefaultFactory;

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
    private final DefaultFactory<T> instantiator;

    public MapToEntityConverter(
            SqlQueryContext<T> context
    ) {
        entityFromMapWriter = new EntityFromMapWriter<>(context);
        instantiator = new DefaultFactory<>(context.getClazz());
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
