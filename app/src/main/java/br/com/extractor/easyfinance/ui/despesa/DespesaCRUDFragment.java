package br.com.extractor.easyfinance.ui.despesa;

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
import br.com.extractor.easyfinance.arquitetura.controller.EntityCRUDFragment;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.ui.adapter.TipoSpinnerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;

public class DespesaCRUDFragment extends EntityCRUDFragment<Despesa> implements DatePickerDialog.OnDateSetListener {

    @Bind(R.id.spn_tipo_despesa)
    Spinner spnTipoDespesa;

    @Bind(R.id.edt_descricao_despesa)
    EditText edtDescricaoDespesa;

    @Bind(R.id.edt_valor_despesa)
    EditText edtValorDespesa;

    @Bind(R.id.edt_data_despesa)
    EditText edtDataDespesaPaga;

    @Bind(R.id.edt_data_vencimento_despesa)
    EditText edtDataVencimentoDespesa;

    private EditText edtData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.despesa_crud_fragment, container, false);
        ButterKnife.bind(this, rootView);

        RealmResults<Tipo> list = realm.where(Tipo.class).notEqualTo("categoria", 1).findAll();
        TipoSpinnerAdapter adapter = new TipoSpinnerAdapter(getActivity(), list);
        spnTipoDespesa.setAdapter(adapter);

        spnTipoDespesa.setSelection(list.lastIndexOf(entity.getTipo()) - 1, true);
        edtDescricaoDespesa.setText(entity.getDescricao());

        edtValorDespesa.setHint(getActivity().getResources().getString(R.string
                .spending_value) + " em (" + NumberFormat.getCurrencyInstance().getCurrency()
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

        return rootView;
    }

    @OnClick({R.id.edt_data_despesa, R.id.edt_data_vencimento_despesa})
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

    @OnClick(R.id.salvar_despesa)
    @Override
    public void salvar(View view) {
        Tipo tipo = (Tipo) spnTipoDespesa.getSelectedItem();
        String dataVencimento = edtDataVencimentoDespesa.getText().toString();
        String dataPaga = edtDataDespesaPaga.getText().toString();
        String valorPago = edtValorDespesa.getText().toString();
        String descricao = edtDescricaoDespesa.getText().toString();

        try {

            if (!"".equals(dataVencimento)) {
                entity.setDataVencimento(SimpleDateFormat.getDateInstance().parse(dataVencimento));
            }

            if (!"".equals(dataPaga)) {
                entity.setDataPaga(SimpleDateFormat.getDateInstance().parse(dataPaga));
            }

            if ("".equals(valorPago)) {
                edtValorDespesa.setError("É necessário informar um valor");
            } else {
                if ("".equals(descricao)) {
                    edtDescricaoDespesa.setError("É necessário informar a descrição");
                } else {
                    if (tipo == null) {
                        Toast.makeText(getActivity(), "É necessário informar o tipo", Toast
                                .LENGTH_LONG).show();
                    } else {
                        entity.setValorPago(Math.floor(Double.valueOf(edtValorDespesa.getText()
                                .toString()) * 100) / 100);
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

}
