package br.com.extractor.easyfinance.arquitetura.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

import br.com.extractor.easyfinance.R;
import butterknife.ButterKnife;

public abstract class ListEntityFragment<T extends EntityCRUDFragment> extends Fragment
        implements View.OnClickListener, View.OnLongClickListener {

    protected T crudEntityFragment;
    protected RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getResourceLayoutID(), container, false);
        ButterKnife.bind(this, rootView);

        list = ButterKnife.findById(rootView, getListID());

        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        list.setHasFixedSize(true);
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(getAdapter());

        list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        return rootView;
    }

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
        View view = ButterKnife.findById(getActivity(), getViewCreateEntity());
        view.setOnClickListener(this);
    }

    public abstract int getViewCreateEntity();

    public abstract int getResourceLayoutID();

    public abstract int getListID();

    public abstract RecyclerView.Adapter getAdapter();

    @Override
    public final boolean onLongClick(View view) {

        // TODO: Falta implementar
        view.setBackgroundColor(Color.BLUE);

        return false;
    }

}
