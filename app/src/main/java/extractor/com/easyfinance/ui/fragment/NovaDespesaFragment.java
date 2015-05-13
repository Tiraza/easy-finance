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
    private MainActivity mainActivity;

    @Override
    public void onResume() {
        super.onResume();
        mainActivity = (MainActivity) getActivity();
        mainActivity.mToolbar.setTitle("Nova Despesa");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nova_despesa, container, false);

        imbData = (ImageButton) rootView.findViewById(R.id.imbData);
        edtData = (EditText) rootView.findViewById(R.id.edtData);
        edtDescricao = (EditText) rootView.findViewById(R.id.edtDescricao);
        edtValor = (EditText) rootView.findViewById(R.id.edtValor);
        btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);

        edtData.setEnabled(false);

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
                if(validaCampos()){
                    Despesa despesa = new Despesa();
                    despesa.setTipo(1);
                    despesa.setDescricao(edtDescricao.getText().toString());
                    despesa.setData(edtData.getText().toString());
                    despesa.setValor(Double.valueOf(edtValor.getText().toString()));

                    //EasyFinance.getDespesaDAO().inseir(despesa);

                    MainActivity activity = (MainActivity) getActivity();
                    activity.fragmentManager.popBackStackImmediate();
                }
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        edtData.setText(dateFormat.format(date.getTime()));
    }

    private Boolean validaCampos(){
        Boolean retorno = true;

        if(edtDescricao.getText().toString().equals("")){
            edtDescricao.setError(getActivity().getResources().getString(R.string.campo_obrigatorio));
            retorno = false;
        }
        if(edtData.getText().toString().equals("")){
            edtData.setError(getActivity().getResources().getString(R.string.campo_obrigatorio));
            retorno = false;
        }
        if(edtValor.getText().toString().equals("")){
            edtValor.setError(getActivity().getResources().getString(R.string.campo_obrigatorio));
            retorno = false;
        }

        return retorno;
    }
}
