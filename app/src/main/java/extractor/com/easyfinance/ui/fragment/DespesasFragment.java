package extractor.com.easyfinance.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import extractor.com.easyfinance.model.Despesa;
import extractor.com.easyfinance.ui.activity.MainActivity;
import extractor.com.easyfinance.ui.adapter.DespesasAdapter;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class DespesasFragment extends Fragment{

    private ArrayList<Despesa> despesas;

    private FloatingActionMenu fab;
    private ListView lvDespesas;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.mToolbar.setTitle("Despesas");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_despesas, container, false);
        despesas = getDespesas();

        fab = (FloatingActionMenu) rootView.findViewById(R.id.fab);
        lvDespesas = (ListView) rootView.findViewById(R.id.lvDespesas);

        DespesasAdapter adapter = new DespesasAdapter(despesas);
        lvDespesas.setAdapter(adapter);

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
