package extractor.com.easyfinance.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.dao.DespesaDAO;
import extractor.com.easyfinance.model.entities.Despesa;
import extractor.com.easyfinance.ui.activity.MainActivity;
import extractor.com.easyfinance.ui.adapter.DespesasAdapter;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class DespesasFragment extends Fragment{

    private ArrayList<Despesa> despesas;

    private FloatingActionMenu fab;
    private FloatingActionButton fabNovo;
    private FloatingActionButton fabExcluir;
    private MainActivity mainActivity;

    private ListView lvDespesas;
    private DespesasAdapter adapter;
    private DespesaDAO mDespesaDAO;

    @Override
    public void onResume() {
        super.onResume();
        mainActivity = (MainActivity) getActivity();
        mainActivity.mToolbar.setTitle("Despesas");
        mDespesaDAO = EasyFinance.getDespesaDAO();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_despesas, container, false);

        mDespesaDAO = EasyFinance.getDespesaDAO();
        despesas = mDespesaDAO.getList();

        fab = (FloatingActionMenu) rootView.findViewById(R.id.fab);
        fabNovo = (FloatingActionButton) rootView.findViewById(R.id.fabNovo);
        fabExcluir = (FloatingActionButton) rootView.findViewById(R.id.fabExcluir);

        fabNovo.setOnClickListener(clickListener);
        fabExcluir.setOnClickListener(clickListener);

        lvDespesas = (ListView) rootView.findViewById(R.id.lvDespesas);

        adapter = new DespesasAdapter(despesas);
        lvDespesas.setAdapter(adapter);

        lvDespesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", adapter.getItem(i).getID().intValue());

                EditarDespesaFragment fragment = new EditarDespesaFragment();
                fragment.setArguments(bundle);

                mainActivity.replaceFragment(fragment);
            }
        });

        return rootView;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fabNovo:
                    mainActivity.replaceFragment(new NovaDespesaFragment());
                    break;
                case R.id.fabExcluir:
                    Toast.makeText(getActivity(), "Button " + fabExcluir.getLabelText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
