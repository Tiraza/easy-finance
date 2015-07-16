package br.com.extractor.easyfinance.ui.tipo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.TipoListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TipoListFragment extends ListEntityFragment<TipoCRUDFragment> {

    @Bind(R.id.list_tipo)
    RecyclerView listTipos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tipo_list_fragment, container, false);
        ButterKnife.bind(this, rootView);

        listTipos.setLayoutManager(new LinearLayoutManager(listTipos.getContext()));
        listTipos.setAdapter(new TipoListAdapter(this, this));

        return rootView;
    }

    @Override
    public int setViewCreateEntity() {
        return R.id.novo_tipo;
    }

}
