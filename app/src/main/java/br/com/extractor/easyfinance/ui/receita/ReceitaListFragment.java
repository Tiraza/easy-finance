package br.com.extractor.easyfinance.ui.receita;

import android.support.v7.widget.RecyclerView;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.controller.EntityFormFragment;
import br.com.extractor.easyfinance.arquitetura.controller.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.ReceitasListAdapter;

public class ReceitaListFragment extends ListEntityFragment {

    @Override
    public int getViewCreateEntity() {
        return R.id.nova_receita;
    }

    @Override
    public int getResourceLayoutID() {
        return R.layout.receita_list_fragment;
    }

    @Override
    public int getListID() {
        return R.id.list_receitas;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new ReceitasListAdapter(this, this);
    }

    @Override
    public EntityFormFragment getFormEntityFragment() {
        return new ReceitaFormFragment();
    }

}
