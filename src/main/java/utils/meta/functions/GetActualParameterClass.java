package utils.meta.functions;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

/**
 * Returns actual parameter of parametrized field.
 * Works with fields with one parameter.
 */
public class GetActualParameterClass implements Function<Field, Class> {
    private static final Logger logger = LogManager.getLogger(GetActualParameterClass.class);
    @Override
    public Class apply(Field field) {
        logger.info("executing");
        try {
            return Class.forName(TypeUtils.getTypeArguments(
                    (ParameterizedType) field.getGenericType()
            ).values().iterator().next().getTypeName());
        } catch (ClassNotFoundException e) {
            logger.error("exception thrown --> return null");
            logger.error(e);
            return null;
        }
    }
}
