package br.com.extractor.easyfinance.ui.tipo;

import android.support.v7.widget.RecyclerView;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.controller.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.TipoListAdapter;

public class TipoListFragment extends ListEntityFragment<TipoCRUDFragment> {

    @Override
    public int getViewCreateEntity() {
        return R.id.novo_tipo;
    }

    @Override
    public int getResourceLayoutID() {
        return R.layout.tipo_list_fragment;
    }

    @Override
    public int getListID() {
        return R.id.list_tipo;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new TipoListAdapter(getOnClickNewEntity(), this);
    }

}
