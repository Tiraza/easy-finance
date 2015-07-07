package br.com.extractor.easyfinance.ui.receita;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.ui.receita.adapter.ReceitasAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceitaListFragment extends Fragment {

    @Bind(R.id.list_receitas)
    RecyclerView listReceitas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receita_list_fragment, container, false);
        ButterKnife.bind(this, rootView);
        listReceitas.setLayoutManager(new LinearLayoutManager(listReceitas.getContext()));
        listReceitas.setAdapter(new ReceitasAdapter());
        return rootView;
    }


}
