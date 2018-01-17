package utils;

import java.lang.reflect.Constructor;
import java.util.function.Function;

/**
 * Finds a default constructor of class.
 *
 * @param <T>
 */
public class DefaultConstructorExtractor<T> implements Function<Class<T>, Constructor<T>> {
    @Override
    public Constructor<T> apply(Class<T> tClass) {
        try {
            return tClass.getConstructor();
        } catch (Exception e) {
            return null;
        }
    }
}
