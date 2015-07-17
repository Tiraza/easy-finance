package br.com.extractor.easyfinance.ui.despesa;

import android.support.v7.widget.RecyclerView;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.DespesasListAdapter;

public class DespesaListFragment extends ListEntityFragment<DespesaCRUDFragment> {

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

}
