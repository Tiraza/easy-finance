package extractor.com.easyfinance.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.entities.Despesa;
import extractor.com.easyfinance.ui.activity.MainActivity;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class NovaDespesaFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private ImageButton imbData;
    private EditText edtData;
    private EditText edtDescricao;
    private EditText edtValor;
    private Button btnSalvar;
    private int mDia, mMes, mAno;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nova_despesa, container, false);

        imbData = (ImageButton) rootView.findViewById(R.id.imbData);
        edtData = (EditText) rootView.findViewById(R.id.edtData);
        edtDescricao = (EditText) rootView.findViewById(R.id.edtDescricao);
        edtValor = (EditText) rootView.findViewById(R.id.edtValor);
        btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);

        Calendar now = Calendar.getInstance();
        mAno = now.get(Calendar.YEAR);
        mMes = now.get(Calendar.MONTH);
        mDia = now.get(Calendar.DAY_OF_MONTH);

        imbData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        NovaDespesaFragment.this, mAno, mMes, mDia
                );
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Despesa despesa = new Despesa();
                despesa.setTipo(1);
                despesa.setDescricao(edtDescricao.getText().toString());
                despesa.setData(edtData.getText().toString());
                despesa.setValor(Double.valueOf(edtValor.getText().toString()));

               //EasyFinance.getDespesaDAO().inseir(despesa);

                MainActivity activity = (MainActivity) getActivity();
                activity.fragmentManager.popBackStackImmediate();
            }
        });

        return rootView;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int ano, int mes, int dia) {
        mAno = ano;
        mMes = mes;
        mDia = dia;

        Calendar date = Calendar.getInstance();
        date.set(mAno, mMes, mDia);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        edtData.setText(dateFormat.format(date.getTime()));
    }
}
