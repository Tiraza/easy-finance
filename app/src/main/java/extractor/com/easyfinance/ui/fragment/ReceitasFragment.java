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
import extractor.com.easyfinance.ui.adapter.ReceitasAdapter;
import extractor.com.myapplication.R;

public class ReceitasFragment extends Fragment {

    @Bind(R.id.list_receitas)
    RecyclerView listReceitas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_receitas, container, false);
        ButterKnife.bind(this, rootView);
        listReceitas.setLayoutManager(new LinearLayoutManager(listReceitas.getContext()));
        listReceitas.setAdapter(new ReceitasAdapter(getActivity()));
        return rootView;
    }


}
