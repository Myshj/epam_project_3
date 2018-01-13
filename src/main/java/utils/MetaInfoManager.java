package utils;

import models.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import orm.Model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public enum MetaInfoManager {
    INSTANCE(
            Country.class, City.class, Street.class, Building.class,
            Showroom.class, Exposition.class, Ticket.class, TicketType.class,
            User.class, UserRole.class, Order.class, OrderState.class
    );


    public final Map<Class<? extends Model>, ModelMetaInfo> infoMap = new HashMap<>();

    private ModelNames names(Class<? extends Model> clazz) {
        return new ModelNames(
                clazz.getAnnotation(EntityNames.class).singular(),
                clazz.getAnnotation(EntityNames.class).plural()
        );
    }

    private Class<? extends Model> actualParameterClass(Field field) {
//        try {
//            try {
//                Class<? extends Model> c = (Class<? extends Model>) Class.forName(TypeUtils.getTypeArguments(
//                        (ParameterizedType) Showroom.class.getDeclaredField("building").getGenericType()
//                ).values().iterator().next().getTypeName());
//                System.out.println(
//                        ModelNameManager.INSTANCE.singularName(c)
//                );
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        try {
            return (Class<? extends Model>) Class.forName(TypeUtils.getTypeArguments(
                    (ParameterizedType) field.getGenericType()
            ).values().iterator().next().getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, Class<? extends Model>> relatives(Class<? extends Model> clazz) {
        return new HashMap<String, Class<? extends Model>>() {{
            for (Field field : FieldUtils.getFieldsWithAnnotation(clazz, Relatives.class)) {
                put(field.getAnnotation(Relatives.class).pluralName(), actualParameterClass(field));
            }
        }};

    }

    @SafeVarargs
    MetaInfoManager(Class<? extends Model>... classes) {
        for (Class<? extends Model> clazz : classes) {
            infoMap.put(clazz, new ModelMetaInfo(names(clazz), relatives(clazz)));
        }
    }
}
