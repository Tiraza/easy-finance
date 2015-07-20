package br.com.extractor.easyfinance.ui.tipo;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.controller.EntityFormFragment;
import br.com.extractor.easyfinance.arquitetura.controller.ListEntityFragment;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import br.com.extractor.easyfinance.ui.adapter.TipoListAdapter;
import io.realm.RealmObject;

public class TipoListFragment extends ListEntityFragment {

    @Override
    public int getViewCreateEntity() {
        return R.id.novo_tipo;
    }

    @Override
    public int getResourceLayoutID() {
        return R.layout.tipo_list_fragment;
    }

    @Override
    public int getListID() {
        return R.id.list_tipo;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new TipoListAdapter(this, this);
    }

    @Override
    public EntityFormFragment getFormEntityFragment() {
        return new TipoFormFragment();
    }

    @Override
    public List<Class<? extends RealmObject>> getDependencies() {
        List<Class<? extends RealmObject>> list = new ArrayList<>();
        list.add(Despesa.class);
        list.add(Receita.class);
        return list;
    }

}
