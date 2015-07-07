package br.com.extractor.easyfinance.arquitetura.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

import br.com.extractor.easyfinance.R;

public abstract class ListEntityFragment<T extends EntityCRUDFragment> extends Fragment
        implements View.OnClickListener, View.OnLongClickListener {

    protected T crudEntityFragment;

    @Override
    public void onClick(View view) {
        Class<T> clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
        try {
            if (crudEntityFragment == null) {
                crudEntityFragment = clazz.newInstance();
            }
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(),
                    crudEntityFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            crudEntityFragment.setClickedView(view.findViewById(R.id.txtId));
        } catch (Exception e) {
            Log.e("ListEntityFragment", clazz.getSimpleName(), e);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

}
