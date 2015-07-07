package extractor.com.easyfinance.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import extractor.com.easyfinance.ui.adapter.DespesasAdapter;
import extractor.com.myapplication.R;

public class DespesasFragment extends Fragment {

    @Bind(R.id.list_despesas)
    RecyclerView listDespesas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_despesas, container, false);
        ButterKnife.bind(this, rootView);
        listDespesas.setLayoutManager(new LinearLayoutManager(listDespesas.getContext()));
        listDespesas.setAdapter(new DespesasAdapter(getActivity()));
        return rootView;
    }

}
