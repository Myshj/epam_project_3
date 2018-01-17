package orm;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.OrmField;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines several useful ORM-related methods.
 */
public abstract class OrmFieldUtils {
    private static final Logger logger = LogManager.getLogger(OrmFieldUtils.class);

    /**
     * Returns map of (fieldName --> Field of entity class).
     *
     * @param clazz class of model.
     * @return map of (fieldName --> Field of entity class).
     */
    public static Map<String, Field> getRelationalToObjectMapping(Class<? extends Model> clazz) {
        logger.info("entering getRelationalToObjectMapping");
        Map<String, Field> ret = new HashMap<>();
        FieldUtils.getAllFieldsList(clazz).stream().filter(
                f -> OrmField.class.isAssignableFrom(f.getType())
        ).forEach(
                f -> ret.put(chooseNameForField(f), f)
        );
        logger.info("leaving getRelationalToObjectMapping");
        return ret;
    }

    /**
     * Returns map of (Field of entity class --> parameter number in update command)
     * @param clazz class of model
     * @return map of (Field of entity class --> parameter number in update command)
     */
    public static Map<Field, Integer> getUpdateMapping(Class<? extends Model> clazz) {
        logger.info("entering getUpdateMapping");
        Map<Field, Integer> ret = new HashMap<>();
        FieldUtils.getAllFieldsList(clazz).stream().filter(
                f -> OrmField.class.isAssignableFrom(f.getType()) && !f.getName().equalsIgnoreCase("id")

        ).forEach(
                f -> ret.put(f, (ret.size() + 1))
        );
        logger.info("leaving getUpdateMapping");
        return ret;
    }

    /**
     * Works reversed to getRelationalToObjectMapping.
     * @param clazz class of model
     * @return map of (Field of entity class --> fieldName).
     */
    public static Map<Field, String> getObjectToRelationalMapping(Class<? extends Model> clazz) {
        logger.info("entering getObjectToRelationalMapping");
        Map<Field, String> ret = new HashMap<>();
        FieldUtils.getAllFieldsList(clazz).stream().filter(
                f -> OrmField.class.isAssignableFrom(f.getType())

        ).forEach(
                f -> ret.put(f, chooseNameForField(f))
        );
        logger.info("leaving getObjectToRelationalMapping");
        return ret;
    }

    /**
     *
     * @param clazz class of model
     * @return name of table to store entity in.
     */
    public static String getTableName(Class<? extends Model> clazz) {
        logger.info("getTableName");
        return clazz.getAnnotation(Entity.class).table();
    }

    /**
     *
     * @param field of model.
     * @return name of field to store value in.
     */
    private static String chooseNameForField(Field field) {
        logger.info("chooseNameForField");
        Column columnAnnotation = field.getAnnotation(Column.class);
        return columnAnnotation == null ? field.getName() : columnAnnotation.name();
    }
}
