package extractor.com.easyfinance.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import extractor.com.easyfinance.model.Despesa;
import extractor.com.easyfinance.ui.adapter.CommonAdapter;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class DespesasFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private ArrayList<Despesa> despesas;
    private FloatingActionMenu fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_despesas, container, false);

        fab = (FloatingActionMenu) rootView.findViewById(R.id.fab);
        despesas = getDespesas();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list_despesas);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CommonAdapter adapter = (CommonAdapter) mRecyclerView.getAdapter();

                if(despesas.size() == llm.findLastCompletelyVisibleItemPosition() + 1){
                    //Carregar de 10 em 10
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        CommonAdapter adapter = new CommonAdapter(getActivity(), despesas);
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    public ArrayList<Despesa> getDespesas(){
        Despesa despesa;
        ArrayList<Despesa> retorno = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            despesa = new Despesa();
            despesa.setData("06/05/2015");
            despesa.setDescricao("Despesa " + i);
            despesa.setTipo(1);
            despesa.setValor(1.00 * i);
            retorno.add(despesa);
        }
        return retorno;
    }
}
