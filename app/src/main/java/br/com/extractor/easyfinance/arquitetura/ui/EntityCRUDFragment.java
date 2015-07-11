package br.com.extractor.easyfinance.arquitetura.ui;

import android.app.Fragment;
import android.os.Bundle;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm.beginTransaction();
    }

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
        if (viewById != null) {
            Class<T> clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);
            String id = ((TextView) viewById).getText().toString();
            entity = realm.where(clazz).equalTo("id", id).findFirst();
        } else {
            entity = null;
        }
    }

}
