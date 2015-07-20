package br.com.extractor.easyfinance.arquitetura.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import br.com.extractor.easyfinance.util.UtilReflection;
import io.realm.Realm;
import io.realm.RealmObject;

public abstract class EntityFormFragment<T extends RealmObject> extends Fragment {

    protected T entity;
    protected Realm realm;
    protected boolean isNewRegistry;

    private Class<T> clazz;

    public EntityFormFragment() {
        clazz = (Class<T>) UtilReflection.getParameterizedTypeFromForm(getClass());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            realm.cancelTransaction();
        } catch (Exception e) {

        }
        entity = null;
        realm.close();
    }

    public void putData(Bundle bundle) {

        long id = bundle.getLong("id");

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        if (id != 0) {
            isNewRegistry = false;
            entity = realm.where(clazz).equalTo("id", id).findFirst();
        } else {
            try {
                isNewRegistry = true;
                entity = realm.copyToRealm(clazz.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Class<T> getEntityClass() {
        return clazz;
    }

    public abstract void salvar(View view);

}
