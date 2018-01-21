package utils.factories.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * Produces objects using their default constructors.
 *
 * @param <T>
 */
public class DefaultFactory<T> implements Supplier<T> {
    private static final Logger logger = LogManager.getLogger(DefaultFactory.class);
    private final Class<T> clazz;
    private final DefaultConstructorExtractor<T> constructorExtractor = new DefaultConstructorExtractor<>();

    public DefaultFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T get() {
        logger.info("started execution");
        try {
            return constructorExtractor.apply(clazz).newInstance();
        } catch (Exception e) {
            logger.error("exception during instantiation --> return null");
            logger.error(e);
            return null;
        }
    }
}
