package extractor.com.easyfinance.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class NovaDespesaFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private ImageButton imbData;
    private EditText edtData;
    private int mDia, mMes, mAno;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nova_despesa, container, false);

        imbData = (ImageButton) rootView.findViewById(R.id.imbData);
        edtData = (EditText) rootView.findViewById(R.id.edtData);

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

        return rootView;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int ano, int mes, int dia) {
        mAno = ano;
        mMes = mes;
        mDia = dia;
        edtData.setText(mDia + "/" + mMes + "/" + mAno);
    }
}
