package utils.meta.functions;

import models.EntityNames;
import orm.Model;
import utils.meta.ModelNames;

import java.util.function.Function;

public class GetNames implements Function<Class<? extends Model>, ModelNames> {
    @Override
    public ModelNames apply(Class<? extends Model> clazz) {
        return new ModelNames(
                clazz.getAnnotation(EntityNames.class).singular(),
                clazz.getAnnotation(EntityNames.class).plural()
        );
    }
}
