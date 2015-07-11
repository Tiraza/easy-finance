package br.com.extractor.easyfinance.ui.despesa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.EntityCRUDFragment;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.ui.adapter.TipoRealmAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class DespesaEntityCRUDFragment extends EntityCRUDFragment<Despesa> implements DatePickerDialog.OnDateSetListener {

    @Bind(R.id.spnTipoDespesa)
    Spinner spnTipoDespesa;

    @Bind(R.id.edtDescricaoDespesa)
    EditText edtDescricaoDespesa;

    @Bind(R.id.edtValorDespesa)
    EditText edtValorDespesa;

    @Bind(R.id.edtDataDespesa)
    EditText edtDataDespesaPaga;

    @Bind(R.id.edtDataVencimentoDespesa)
    EditText edtDataVencimentoDespesa;

    private EditText edtData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.despesa_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);

        RealmResults<Tipo> list = realm.where(Tipo.class).notEqualTo("tipo", 1).findAll();
        TipoRealmAdapter adapter = new TipoRealmAdapter(getActivity(), list);
        spnTipoDespesa.setAdapter(adapter);

        if (entity != null) {
            spnTipoDespesa.setSelection(list.lastIndexOf(entity.getTipo()) - 1, true);
            edtDescricaoDespesa.setText(entity.getDescricao());

            edtValorDespesa.setHint(getActivity().getResources().getString(R.string
                    .valor_despesa) + " em (" + NumberFormat.getCurrencyInstance().getCurrency()
                    .getSymbol() + ")");

            edtValorDespesa.setText(String.valueOf(entity.getValorPago()));

            Date dataPaga = entity.getDataPaga();
            Date dataVencimento = entity.getDataVencimento();

            if (dataPaga == null) {
                dataPaga = new Date();
            }

            if (dataVencimento == null) {
                dataVencimento = new Date();
            }

            edtDataDespesaPaga.setText(SimpleDateFormat.getDateInstance().format(dataPaga));
            edtDataVencimentoDespesa.setText(SimpleDateFormat.getDateInstance().format(dataVencimento));

        } else {
            entity = new Despesa();
        }

        return rootView;
    }

    @OnClick(R.id.salvar_despesa)
    public void onClickSalvarDespesa() {
        try {

            if (!"".equals(edtDataVencimentoDespesa.getText().toString())) {
                entity.setDataVencimento(SimpleDateFormat.getDateInstance().parse
                        (edtDataVencimentoDespesa.getText().toString()));
            }

            if (!"".equals(edtDataDespesaPaga.getText().toString())) {
                entity.setDataPaga(SimpleDateFormat.getDateInstance().parse(edtDataDespesaPaga.getText()
                        .toString()));
            }

            entity.setValorPago(Math.floor(Double.valueOf(edtValorDespesa.getText().toString()) * 100)
                    / 100);
            entity.setDescricao(edtDescricaoDespesa.getText().toString());
            entity.setTipo((Tipo) spnTipoDespesa.getSelectedItem());

            realm.commitTransaction();
            getFragmentManager().popBackStack();
        } catch (Exception e) {
            throw new RuntimeException("Parser exception", e);
        }
    }

    @OnClick({R.id.edtDataDespesa, R.id.edtDataVencimentoDespesa})
    public void onClickDataDespesa(View view) {
        edtData = (EditText) view;
        Calendar calendar = Calendar.getInstance();

        if (entity.getDataVencimento() != null) {
            calendar.setTime(entity.getDataVencimento());
        }

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = DatePickerDialog.newInstance(this, ano, mes, dia);

        dpd.setThemeDark(true);
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
