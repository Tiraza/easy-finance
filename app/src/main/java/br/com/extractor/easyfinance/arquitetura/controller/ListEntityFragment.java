package br.com.extractor.easyfinance.arquitetura.controller;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.DividerItemDecoration;
import br.com.extractor.easyfinance.ui.FragmentCommunication;
import br.com.extractor.easyfinance.util.ReflectionUtil;
import butterknife.ButterKnife;

public abstract class ListEntityFragment<T extends EntityCRUDFragment> extends Fragment
        implements View.OnLongClickListener, FragmentCommunication {

    protected View rootView;
    protected T crudEntityFragment;
    protected RecyclerView list;
    protected View viewCreateEntitys;
    protected View viewDeleteEntitys;

    private Class<T> clazz;
    private boolean hasItemClicked;
    private boolean hasViewDeleteEntityInflated;

    private View.OnClickListener onClickNewEntity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                crudEntityFragment = clazz.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(((ViewGroup) rootView.getParent()).getId(),
                        crudEntityFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                crudEntityFragment.setClickedView(view.findViewById(R.id.txt_id));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };

    private View.OnClickListener onClickDeleteEntity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("", "Removendo entidade ...");
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(getResourceLayoutID(), container, false);
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
    public void onResume() {
        super.onResume();
        viewCreateEntitys = ButterKnife.findById(getActivity(), getViewCreateEntity());
        viewCreateEntitys.setOnClickListener(onClickNewEntity);
        clazz = ReflectionUtil.getFirstGeneric(getClass());
    }

    @Override
    public final boolean onLongClick(View view) {

        view.setBackgroundResource(R.color.itemListSelected);

        if (!hasItemClicked) {
            hasItemClicked = true;
            viewCreateEntitys.setVisibility(View.INVISIBLE);
            if (!hasViewDeleteEntityInflated) {
                hasViewDeleteEntityInflated = true;
                FrameLayout frameLayout = (FrameLayout) viewCreateEntitys.getParent();
                viewDeleteEntitys = View.inflate(getActivity(), R.layout.float_action_button_delete_state,
                        frameLayout);
                viewDeleteEntitys.setOnClickListener(onClickDeleteEntity);
            } else {
                viewDeleteEntitys.setVisibility(View.VISIBLE);
            }
        }

        return false;
    }


    @Override
    public boolean hasPendencies() {
        return hasItemClicked;
    }

    @Override
    public void freePendencies() {
        hasItemClicked = false;
        // TODO: Desmarcar as views e voltar o botão de adicionar
    }

    public View.OnClickListener getOnClickNewEntity() {
        return onClickNewEntity;
    }

    public abstract int getViewCreateEntity();

    public abstract int getResourceLayoutID();

    public abstract int getListID();

    public abstract RecyclerView.Adapter getAdapter();

}
