package br.com.extractor.easyfinance.util;

import java.lang.reflect.ParameterizedType;

public class ReflectionUtil {

    public static <T> Class<T> getFirstGeneric(Class clazz) {
        return ((Class<T>) ((ParameterizedType) clazz.getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }

}
