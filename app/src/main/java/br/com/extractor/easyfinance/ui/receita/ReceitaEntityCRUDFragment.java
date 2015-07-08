package br.com.extractor.easyfinance.ui.receita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.EntityCRUDFragment;
import br.com.extractor.easyfinance.model.Receita;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.ui.adapter.TipoRealmAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class ReceitaEntityCRUDFragment extends EntityCRUDFragment<Receita> {

    @Bind(R.id.spnTipoReceita)
    Spinner spnTipoReceita;

    @Bind(R.id.edtDescricaoReceita)
    EditText edtDescricaoReceita;

    @Bind(R.id.edtValorReceita)
    EditText edtValorReceita;

    @Bind(R.id.edtDataReceita)
    EditText edtDataReceita;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receita_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);

        RealmResults<Tipo> list = realm.where(Tipo.class).notEqualTo("tipo", 0).findAll();
        TipoRealmAdapter adapter = new TipoRealmAdapter(getActivity(), list);
        spnTipoReceita.setAdapter(adapter);

        if (entity != null) {
            int position = 0;
            for (Tipo tipo : list) {
                if (tipo.getId().equals(entity.getTipo().getId())) {
                    break;
                }
                position++;
            }
            spnTipoReceita.setSelection(position, true);
            edtDescricaoReceita.setText(entity.getDescricao());
            edtValorReceita.setText(NumberFormat.getCurrencyInstance().format(entity.getValor()));
            edtDataReceita.setText(SimpleDateFormat.getDateInstance().format(entity.getData()));
        }

        return rootView;
    }

}
