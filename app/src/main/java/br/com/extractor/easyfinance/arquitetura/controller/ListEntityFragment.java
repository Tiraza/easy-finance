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

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.DividerItemDecoration;
import br.com.extractor.easyfinance.model.Entidade;
import br.com.extractor.easyfinance.ui.FragmentCommunication;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmObject;

public abstract class ListEntityFragment extends Fragment
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
        realm.setAutoRefresh(true);

        List<RealmObject> listEntitys = new ArrayList<>();

        for (View view : listClickedViews) {
            long id = Long.parseLong(((TextView) view.findViewById(R.id.txt_id)).getText().toString());
            RealmObject result = realm.where(formEntityFragment.getEntityClass()).equalTo("id", id).findFirst();
            listEntitys.add(result);
        }

        resolveDependencies(realm, listEntitys);

        realm.close();
    }

    public void resolveDependencies(final Realm realm, final List<RealmObject> listEntitys) {

        boolean hasRegistries = false;

        String entityName = formEntityFragment.getEntityClass().getSimpleName();
        String foreignKey = entityName.toLowerCase() + ".id";

        for (RealmObject realmObject : listEntitys) {
            for (Class<? extends RealmObject> dependencyClass : getDependencies()) {
                Entidade entidade = (Entidade) realmObject;
                int quantity = realm.where(dependencyClass).equalTo(foreignKey, entidade.getId())
                        .findAll().size();
                if (quantity > 0) {
                    hasRegistries = true;
                }
            }
        }

        if (hasRegistries) {

            new MaterialDialog.Builder(getActivity())
                    .title(R.string.error)
                    .content(R.string.exception_unable_delete_this_has_registries)
                    .neutralText(R.string.ok)
                    .cancelable(false)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onNeutral(MaterialDialog dialog) {
                            dialog.dismiss();
                        }
                    }).show();

        } else {
            realm.beginTransaction();

            for (View view : listClickedViews) {
                long id = Long.parseLong(((TextView) view.findViewById(R.id.txt_id)).getText().toString());
                realm.where(formEntityFragment.getEntityClass()).equalTo("id", id).findAll().clear();
            }

            realm.commitTransaction();
            list.getAdapter().notifyDataSetChanged();
            freePendencies();
        }

    }

    @Override
    public final boolean onLongClick(View view) {

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

    public abstract List<Class<? extends RealmObject>> getDependencies();

}
