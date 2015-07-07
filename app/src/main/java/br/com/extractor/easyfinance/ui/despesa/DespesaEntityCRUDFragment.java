package br.com.extractor.easyfinance.ui.despesa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.EntityCRUDFragment;
import br.com.extractor.easyfinance.model.Despesa;
import butterknife.ButterKnife;

public class DespesaEntityCRUDFragment extends EntityCRUDFragment<Despesa> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.despesa_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);
        if (entity != null) {

        }
        return rootView;
    }

}
