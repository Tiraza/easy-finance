package br.com.extractor.easyfinance.arquitetura.ui;

import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.ParameterizedType;

import br.com.extractor.easyfinance.ui.CallbackCRUDFragment;
import io.realm.Realm;
import io.realm.RealmObject;

public abstract class EntityCRUDFragment<T extends RealmObject> extends Fragment implements
        CallbackCRUDFragment {

    protected T entity;
    protected Realm realm;
    private Class<T> clazz;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        entity = null;
        realm.close();
    }

    @Override
    public void setClickedView(View viewById) {
        clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
        String id = ((TextView) viewById).getText().toString();
        realm = Realm.getDefaultInstance();
        entity = realm.where(clazz).equalTo("id", id).findFirst();
    }

}
