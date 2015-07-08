package br.com.extractor.easyfinance.ui.despesa;

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
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.ui.adapter.TipoRealmAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class DespesaEntityCRUDFragment extends EntityCRUDFragment<Despesa> {

    @Bind(R.id.spnTipoDespesa)
    Spinner spnTipoDespesa;

    @Bind(R.id.edtDescricaoDespesa)
    EditText edtDescricaoDespesa;

    @Bind(R.id.edtValorDespesa)
    EditText edtValorDespesa;

    @Bind(R.id.edtDataDespesa)
    EditText edtDataDespesa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.despesa_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);

        RealmResults<Tipo> list = realm.where(Tipo.class).notEqualTo("tipo", 1).findAll();
        TipoRealmAdapter adapter = new TipoRealmAdapter(getActivity(), list);
        spnTipoDespesa.setAdapter(adapter);

        if (entity != null) {
            int position = 0;
            for (Tipo tipo : list) {
                if (tipo.getId().equals(entity.getTipo().getId())) {
                    break;
                }
                position++;
            }
            spnTipoDespesa.setSelection(position, true);
            edtDescricaoDespesa.setText(entity.getDescricao());
            edtValorDespesa.setText(NumberFormat.getCurrencyInstance().format(entity.getValor()));
            edtDataDespesa.setText(SimpleDateFormat.getDateInstance().format(entity.getData()));
        }

        return rootView;
    }

}
