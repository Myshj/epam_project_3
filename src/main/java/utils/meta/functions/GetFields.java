package utils.meta.functions;

import models.annotations.Relatives;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.fields.SimpleOrmField;
import utils.meta.FieldMetaInfo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Returns metainfo about all fields of model.
 */
public class GetFields implements Function<Class<? extends Model>, Map<String, FieldMetaInfo>> {
    private static final Logger logger = LogManager.getLogger(GetFields.class);
    @Override
    public Map<String, FieldMetaInfo> apply(Class<? extends Model> clazz) {
        logger.info("entering");
        return new HashMap<String, FieldMetaInfo>() {{
            Map<String, Field> fieldMap = OrmFieldUtils.getRelationalToObjectMapping(clazz);
            Map<Field, Integer> updateMapping = OrmFieldUtils.getUpdateMapping(clazz);
            fieldMap.entrySet().stream()
                    .filter(p -> updateMapping.containsKey(p.getValue()))
                    .forEach(p -> {
                        String s = p.getKey();
                        Field f = p.getValue();
                        Class type = f.getType();

                        put(
                                f.getName(),
                                new FieldMetaInfo(
                                        s,
                                        (Class<? extends SimpleOrmField>) type,
                                        f.getAnnotation(Relatives.class) == null ?
                                                null :
                                                f.getAnnotation(Relatives.class).pluralName()
                                )
                        );
                    });
            logger.info("leaving");
        }};
    }
}
