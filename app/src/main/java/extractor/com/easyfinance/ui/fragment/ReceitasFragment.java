package extractor.com.easyfinance.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.entities.Receita;
import extractor.com.easyfinance.ui.activity.MainActivity;
import extractor.com.easyfinance.ui.adapter.ReceitasAdapter;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class ReceitasFragment extends Fragment{

    private ArrayList<Receita> receitas;

    private FloatingActionMenu fab;
    private FloatingActionButton fabNovo;
    private FloatingActionButton fabExcluir;
    private MainActivity mainActivity;

    private ListView lvDespesas;
    private ReceitasAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        mainActivity = (MainActivity) getActivity();
        mainActivity.mToolbar.setTitle("Receitas");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_receitas, container, false);

        receitas = EasyFinance.getReceitaDAO().getList();

        fab = (FloatingActionMenu) rootView.findViewById(R.id.fab);
        fabNovo = (FloatingActionButton) rootView.findViewById(R.id.fabNovo);
        fabExcluir = (FloatingActionButton) rootView.findViewById(R.id.fabExcluir);

        fabNovo.setOnClickListener(clickListener);
        fabExcluir.setOnClickListener(clickListener);

        lvDespesas = (ListView) rootView.findViewById(R.id.lvDespesas);

        adapter = new ReceitasAdapter(receitas);
        lvDespesas.setAdapter(adapter);

        return rootView;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fabNovo:
                    Toast.makeText(getActivity(), "Button " + fabNovo.getLabelText(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fabExcluir:
                    Toast.makeText(getActivity(), "Button " + fabExcluir.getLabelText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
