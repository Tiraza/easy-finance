package br.com.extractor.easyfinance.arquitetura.controller;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.DividerItemDecoration;
import br.com.extractor.easyfinance.ui.FragmentCommunication;
import butterknife.ButterKnife;
import io.realm.Realm;

public abstract class ListEntityFragment
        extends Fragment
        implements View.OnLongClickListener, FragmentCommunication, View.OnClickListener {

    protected View rootView;
    protected EntityFormFragment formEntityFragment;
    protected RecyclerView list;
    protected View viewCreateDeleteEntities;

    private List<View> listClickedViews;
    private Bundle bundle;
    private AnimatorSet addToDelete;
    private AnimatorSet deleteToAdd;

    @Override
    public void onClick(View view) {
        if (hasPendencies()) {
            freePendencies();
        } else {
            openEntityForm(view);
        }
    }

    private void openEntityForm(View view) {
        bundle.clear();
        View viewIdEntity = view.findViewById(R.id.txt_id);
        long id = 0;
        if (viewIdEntity != null) {
            id = Long.parseLong(((TextView) viewIdEntity).getText().toString());
        }
        bundle.putLong("id", id);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(((ViewGroup) rootView.getParent()).getId(), formEntityFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        formEntityFragment.putData(bundle);
    }

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

        listClickedViews = new ArrayList<>();
        bundle = new Bundle();
        formEntityFragment = getFormEntityFragment();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        addToDelete = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R
                .animator.add_to_delete);
        deleteToAdd = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R
                .animator.delete_to_add);
        viewCreateDeleteEntities = ButterKnife.findById(getActivity(), getViewCreateEntity());
        viewCreateDeleteEntities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPendencies()) {
                    removeEntities();
                } else {
                    openEntityForm(v);
                }
            }
        });

    }

    protected void removeEntities() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        for (View view : listClickedViews) {
            long id = Long.parseLong(((TextView) view.findViewById(R.id.txt_id)).getText().toString());
            realm.where(formEntityFragment.getEntityClass()).equalTo("id", id).findFirst().removeFromRealm();
        }

        list.getAdapter().notifyDataSetChanged();
        realm.commitTransaction();
        realm.close();
        listClickedViews.clear();
        deleteToAdd.setTarget(viewCreateDeleteEntities);
        deleteToAdd.start();
    }

    @Override
    public final boolean onLongClick(View view) {

        freePendencies();
        view.setBackgroundResource(R.color.itemListSelected);

        if (listClickedViews.isEmpty()) {
            addToDelete.setTarget(viewCreateDeleteEntities);
            addToDelete.start();
        }

        listClickedViews.add(view);

        return false;
    }

    @Override
    public boolean hasPendencies() {
        return !listClickedViews.isEmpty();
    }

    @Override
    public void freePendencies() {
        for (View view : listClickedViews) {
            view.setBackgroundResource(R.color.mdtp_white);
        }
        deleteToAdd.setTarget(viewCreateDeleteEntities);
        deleteToAdd.start();
        listClickedViews.clear();
    }

    public abstract int getViewCreateEntity();

    public abstract int getResourceLayoutID();

    public abstract int getListID();

    public abstract RecyclerView.Adapter getAdapter();

    public abstract EntityFormFragment getFormEntityFragment();

}
