package pt.ipleiria.estg.dei.ei.dae.cardiacos.utils;

import java.lang.reflect.ParameterizedType;

public class TypeResolver {
    public static <T> Class<T> getGenericClazz(Object object, int index) {
        var genericSuperClass = object.getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;

        while (parametrizedType == null) {
            if ((genericSuperClass instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }

        return (Class<T>) parametrizedType.getActualTypeArguments()[index];
    }
}
