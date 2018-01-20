package utils.meta.functions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.annotations.Entity;

import java.util.function.Function;

/**
 * Returns name of table to store entity in.
 */
public class GetTableName implements Function<Class<? extends Model>, String> {
    private static final Logger logger = LogManager.getLogger(GetTableName.class);

    @Override
    public String apply(Class<? extends Model> clazz) {
        logger.info("started execution");
        Entity annotation = clazz.getAnnotation(Entity.class);
        return annotation == null ? clazz.getName() : annotation.table();
    }
}
