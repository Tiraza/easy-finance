package br.com.extractor.easyfinance.ui.despesa;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.DespesasAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DespesaListFragment extends ListEntityFragment<DespesaEntityCRUDFragment> {

    @Bind(R.id.list_despesas)
    RecyclerView listDespesas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.despesa_list_fragment, container, false);
        ButterKnife.bind(this, rootView);
        listDespesas.setLayoutManager(new LinearLayoutManager(listDespesas.getContext()));
        listDespesas.setAdapter(new DespesasAdapter(this, this));
        return rootView;
    }

}
