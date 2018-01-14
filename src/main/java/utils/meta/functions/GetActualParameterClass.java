package utils.meta.functions;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public class GetActualParameterClass implements Function<Field, Class> {
    @Override
    public Class apply(Field field) {
        try {
            return Class.forName(TypeUtils.getTypeArguments(
                    (ParameterizedType) field.getGenericType()
            ).values().iterator().next().getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
