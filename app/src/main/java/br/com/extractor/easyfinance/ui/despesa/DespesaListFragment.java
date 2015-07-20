package br.com.extractor.easyfinance.ui.despesa;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.controller.EntityFormFragment;
import br.com.extractor.easyfinance.arquitetura.controller.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.DespesasListAdapter;
import io.realm.RealmObject;

public class DespesaListFragment extends ListEntityFragment {

    @Override
    public int getViewCreateEntity() {
        return R.id.nova_despesa;
    }

    @Override
    public int getResourceLayoutID() {
        return R.layout.despesa_list_fragment;
    }

    @Override
    public int getListID() {
        return R.id.list_despesas;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new DespesasListAdapter(this, this);
    }

    @Override
    public EntityFormFragment getFormEntityFragment() {
        return new DespesaFormFragment();
    }

    @Override
    public List<Class<? extends RealmObject>> getDependencies() {
        return new ArrayList<>();
    }

}
