package br.com.extractor.easyfinance.arquitetura.controller;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.ParameterizedType;

import io.realm.Realm;
import io.realm.RealmObject;

public abstract class EntityCRUDFragment<T extends RealmObject> extends Fragment implements
        CallbackCRUDFragment {

    protected T entity;
    protected Realm realm;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            realm.cancelTransaction();
            Log.d("DB", "Transação revertida");
        } catch (Exception e) {
            Log.d("DB", "Transação commitada");
        }
        entity = null;
        realm.close();
    }

    @Override
    public void setClickedView(View viewById) {
        realm = Realm.getDefaultInstance();
        Class<T> clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
        realm.beginTransaction();
        if (viewById != null) {
            long id = Long.parseLong(((TextView) viewById).getText().toString());
            entity = realm.where(clazz).equalTo("id", id).findFirst();
        } else {
            try {
                entity = realm.copyToRealm(clazz.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void salvar(View view);

}
