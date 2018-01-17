package utils.meta.functions;

import models.annotations.EntityNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import utils.meta.ModelNames;

import java.util.function.Function;

/**
 * Returns names of a given entity.
 */
public class GetNames implements Function<Class<? extends Model>, ModelNames> {
    private static final Logger logger = LogManager.getLogger(GetNames.class);
    @Override
    public ModelNames apply(Class<? extends Model> clazz) {
        logger.info("entering");
        return new ModelNames(
                clazz.getAnnotation(EntityNames.class).singular(),
                clazz.getAnnotation(EntityNames.class).plural()
        );
    }
}
