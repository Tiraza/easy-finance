package br.com.extractor.easyfinance.arquitetura.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

import br.com.extractor.easyfinance.R;
import butterknife.ButterKnife;

public abstract class ListEntityFragment<T extends EntityCRUDFragment> extends Fragment
        implements View.OnClickListener, View.OnLongClickListener {

    protected T crudEntityFragment;

    @Override
    public final void onClick(View view) {
        Class<T> clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
        try {
            crudEntityFragment = clazz.newInstance();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(),
                    crudEntityFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            crudEntityFragment.setClickedView(view.findViewById(R.id.txt_id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ButterKnife.findById(getActivity(), setViewCreateEntity());
        view.setOnClickListener(this);
    }

    public abstract int setViewCreateEntity();

    @Override
    public final boolean onLongClick(View view) {
        return false;
    }

}
