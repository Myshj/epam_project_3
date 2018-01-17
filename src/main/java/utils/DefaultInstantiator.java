package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class DefaultInstantiator<T> implements Supplier<T> {
    private static final Logger logger = LogManager.getLogger(DefaultInstantiator.class);
    private final Class<T> clazz;
    private final DefaultConstructorExtractor<T> constructorExtractor = new DefaultConstructorExtractor<>();

    public DefaultInstantiator(Class<T> clazz) {
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
