package utils.meta.functions;

import models.Relatives;
import org.apache.commons.lang3.reflect.FieldUtils;
import orm.Model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GetRelatives implements Function<Class<? extends Model>, Map<String, Class<? extends Model>>> {
    @Override
    public Map<String, Class<? extends Model>> apply(Class<? extends Model> clazz) {
        return new HashMap<String, Class<? extends Model>>() {{
            for (Field field : FieldUtils.getFieldsWithAnnotation(clazz, Relatives.class)) {
                put(
                        field.getAnnotation(Relatives.class).pluralName(),
                        (Class<? extends Model>) new GetActualParameterClass().apply(field)
                );
            }
        }};
    }
}
