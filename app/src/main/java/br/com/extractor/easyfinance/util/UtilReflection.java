package br.com.extractor.easyfinance.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import br.com.extractor.easyfinance.arquitetura.controller.EntityFormFragment;
import io.realm.RealmObject;

public class UtilReflection {

    public static Class<? extends RealmObject> getParameterizedTypeFromForm(Class<? extends EntityFormFragment> clazz) {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type type = parameterizedType.getActualTypeArguments()[0];
        return (Class) type;
    }

}
