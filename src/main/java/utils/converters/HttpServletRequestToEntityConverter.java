package utils.converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletServiceContext;
import utils.factories.generic.DefaultFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

/**
 * Makes entity from http request parameters.
 *
 * @param <T>
 */
public class HttpServletRequestToEntityConverter<T extends Model> implements Function<HttpServletRequest, T> {
    private static final Logger logger = LogManager.getLogger(HttpServletRequestToEntityConverter.class);

    private DefaultFactory<T> instantiator;
    private EntityFromHttpServletRequestWriter<T> writer;

    public HttpServletRequestToEntityConverter(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        logger.info("started construction");
        writer = new EntityFromHttpServletRequestWriter<>(context, clazz);
        instantiator = new DefaultFactory<>(clazz);
        logger.info("constructed");
    }

    @Override
    public T apply(HttpServletRequest request) {
        try {
            return writer.apply(instantiator.get(), request);
        } catch (Exception e) {
            logger.error("exception occured --> return null");
            return null;
        }
    }


}
