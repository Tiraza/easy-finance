package br.com.extractor.easyfinance.ui.receita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.EntityCRUDFragment;
import br.com.extractor.easyfinance.model.Receita;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.ui.adapter.TipoRealmAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class ReceitaEntityCRUDFragment extends EntityCRUDFragment<Receita> implements DatePickerDialog.OnDateSetListener {

    @Bind(R.id.spnTipoReceita)
    Spinner spnTipoReceita;

    @Bind(R.id.edtDescricaoReceita)
    EditText edtDescricaoReceita;

    @Bind(R.id.edtValorReceita)
    EditText edtValorReceita;

    @Bind(R.id.edtDataReceita)
    EditText edtDataReceitaPaga;

    @Bind(R.id.edtDataVencimentoReceita)
    EditText edtDataVencimentoReceita;

    private EditText edtData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.receita_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);

        RealmResults<Tipo> list = realm.where(Tipo.class).notEqualTo("tipo", 0).findAll();
        TipoRealmAdapter adapter = new TipoRealmAdapter(getActivity(), list);
        spnTipoReceita.setAdapter(adapter);

        if (entity != null) {
            spnTipoReceita.setSelection(list.lastIndexOf(entity.getTipo()) - 1, true);
            edtDescricaoReceita.setText(entity.getDescricao());

            edtValorReceita.setHint(getActivity().getResources().getString(R.string
                    .valor_receita) + " em (" + NumberFormat.getCurrencyInstance().getCurrency()
                    .getSymbol() + ")");

            edtValorReceita.setText(String.valueOf(entity.getValorPago()));

            Date dataPaga = entity.getDataPaga();
            Date dataVencimento = entity.getDataVencimento();

            if (dataPaga == null) {
                dataPaga = new Date();
            }

            if (dataVencimento == null) {
                dataVencimento = new Date();
            }

            edtDataReceitaPaga.setText(SimpleDateFormat.getDateInstance().format(dataPaga));
            edtDataVencimentoReceita.setText(SimpleDateFormat.getDateInstance().format(dataVencimento));

        } else {
            entity = new Receita();
        }

        return rootView;
    }

    @OnClick(R.id.salvar_receita)
    public void onClickSalvarReceita() {

        Tipo tipo = (Tipo) spnTipoReceita.getSelectedItem();
        String dataVencimento = edtDataVencimentoReceita.getText().toString();
        String dataPaga = edtDataReceitaPaga.getText().toString();
        String valorPago = edtValorReceita.getText().toString();
        String descricao = edtDescricaoReceita.getText().toString();

        try {

            if (!"".equals(dataVencimento)) {
                entity.setDataVencimento(SimpleDateFormat.getDateInstance().parse(dataVencimento));
            }

            if (!"".equals(dataPaga)) {
                entity.setDataPaga(SimpleDateFormat.getDateInstance().parse(dataPaga));
            }

            if ("".equals(valorPago)) {
                edtValorReceita.setError("É necessário informar um valor");
            } else {
                if ("".equals(descricao)) {
                    edtDescricaoReceita.setError("É necessário informar a descrição");
                } else {
                    if (tipo == null) {
                        Toast.makeText(getActivity(), "É necessário informar o tipo", Toast
                                .LENGTH_LONG).show();
                    } else {
                        entity.setValorPago(Math.floor(Double.valueOf(edtValorReceita.getText().toString()) * 100) / 100);
                        entity.setDescricao(descricao);

                        entity.setTipo(tipo);

                        realm.commitTransaction();
                        getFragmentManager().popBackStack();
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Parser exception", e);
        }

    }

    @OnClick({R.id.edtDataVencimentoReceita, R.id.edtDataReceita})
    public void onClickDataVencimentoDespesa(View view) {
        edtData = (EditText) view;
        Calendar calendar = Calendar.getInstance();

        if (entity.getDataVencimento() != null) {
            calendar.setTime(entity.getDataVencimento());
        }

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = DatePickerDialog.newInstance(this, ano, mes, dia);
        dpd.setThemeDark(false);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int ano, int mes, int dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        edtData.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
    }

}
