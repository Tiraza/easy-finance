package br.com.extractor.easyfinance.ui.receita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.ListEntityFragment;
import br.com.extractor.easyfinance.ui.adapter.ReceitasAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceitaListFragment extends ListEntityFragment<ReceitaEntityCRUDFragment> {

    @Bind(R.id.list_receitas)
    RecyclerView listReceitas;

    @Bind(R.id.nova_receita)
    FloatingActionButton novaReceita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receita_list_fragment, container, false);
        ButterKnife.bind(this, rootView);
        listReceitas.setLayoutManager(new LinearLayoutManager(listReceitas.getContext()));
        listReceitas.setAdapter(new ReceitasAdapter(this, this));
        return rootView;
    }

    @OnClick(R.id.nova_receita)
    public void onClickNovaReceita(View view) {
        onClick(view);
    }

}
