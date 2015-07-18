package br.com.extractor.easyfinance.ui.tipo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.controller.EntityFormFragment;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.model.domain.CategoriaTitulo;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TipoFormFragment extends EntityFormFragment<Tipo> {

    @Bind(R.id.spn_categoria_tipo)
    Spinner spnCategoriaTipo;

    @Bind(R.id.edt_descricao_tipo)
    EditText edtDescricaoTipo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tipo_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);

        ArrayAdapter<CategoriaTitulo> adapter = new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_list_item_1, CategoriaTitulo.values());

        spnCategoriaTipo.setAdapter(adapter);
        spnCategoriaTipo.setSelection(adapter.getPosition(CategoriaTitulo.getById(entity.getCategoria())), true);
        edtDescricaoTipo.setText(entity.getDescricao());

        return rootView;
    }

    @OnClick(R.id.salvar_tipo)
    @Override
    public void salvar(View view) {
        CategoriaTitulo categoriaTitulo = (CategoriaTitulo) spnCategoriaTipo.getSelectedItem();
        String descricao = edtDescricaoTipo.getText().toString();

        if ("".equals(descricao)) {
            edtDescricaoTipo.setError("É necessário informar a descrição");
        } else {
            if (categoriaTitulo == null) {
                Toast.makeText(getActivity(), "É necessário informar o tipo", Toast
                        .LENGTH_LONG).show();
            } else {

                entity.setDescricao(descricao);
                entity.setCategoria(categoriaTitulo.getCodigo());

                realm.commitTransaction();
                getFragmentManager().popBackStack();
            }
        }
    }

}
