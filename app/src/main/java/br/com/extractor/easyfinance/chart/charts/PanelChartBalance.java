package br.com.extractor.easyfinance.chart.charts;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.chart.Panel;
import br.com.extractor.easyfinance.chart.PanelException;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import br.com.extractor.easyfinance.util.UtilDate;
import br.com.extractor.easyfinance.util.UtilPanel;
import io.realm.Realm;

public class PanelChartBalance implements Panel, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText edtGenericDate;

    private EditText edtInitialDate;
    private EditText edtFinalDate;
    private FragmentManager fragmentManager;

    @Override
    public View buildView(Context context, ViewGroup viewGroup, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        LineChart chart = new LineChart(context);
        chart.setDescription(getDescription(context));
        chart.setHighlightEnabled(true);
        chart.setTouchEnabled(true);
        chart.setDragDecelerationFrictionCoef(0.9f);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);
        chart.setPinchZoom(true);
        chart.animateX(2500);
        chart.animateY(2500);
        viewGroup.addView(chart);
        return chart;
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.panel_chart_balance);
    }

    @Override
    public void obtainParamsAndProcess(final Context context, final View view) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(R.string.dialog_select_params);
        builder.cancelable(false);
        builder.customView(R.layout.dialog_chart_balance, true);
        builder.positiveText(R.string.ok);
        builder.negativeText(R.string.cancel);
        builder.autoDismiss(false);
        builder.callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                try {
                    Object[] params = validateParams(view, edtInitialDate.getText().toString(), edtFinalDate
                            .getText().toString());
                    processParams(view, params);
                    dialog.dismiss();
                } catch (PanelException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                dialog.dismiss();
            }
        });

        MaterialDialog dialog = builder.build();
        View customView = dialog.getView();

        edtInitialDate = (EditText) customView.findViewById(R.id.edt_data_inicial);
        edtFinalDate = (EditText) customView.findViewById(R.id.edt_data_final);
        edtInitialDate.setOnClickListener(this);
        edtFinalDate.setOnClickListener(this);

        dialog.show();
    }

    @Override
    public Object[] validateParams(View view, Object... params) throws PanelException {
        String sInitialDate = edtInitialDate.getText().toString();
        String sFinalDate = edtFinalDate.getText().toString();

        if ("".equals(sInitialDate)) {
            throw new PanelException(view.getContext(), R.string
                    .exception_chart_initial_date_invalid);
        }

        if ("".equals(sFinalDate)) {
            throw new PanelException(view.getContext(), R.string
                    .exception_chart_final_date_invalid);
        }

        Date iDate;
        Date fDate;

        try {
            iDate = SimpleDateFormat.getDateInstance().parse(sInitialDate);
            fDate = SimpleDateFormat.getDateInstance().parse(sFinalDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        LocalDate startDate = new LocalDate(UtilDate.getStartOfDay(iDate));
        LocalDate endDate = new LocalDate(UtilDate.getEndOfDay(fDate));

        if (startDate.isAfter(endDate)) {
            throw new PanelException(view.getContext(), R.string.exception_chart_initial_date_less_or_equal);
        }

        return new Object[]{startDate, endDate};

    }

    @Override
    public void processParams(View view, Object... params) {

        Realm realm = Realm.getDefaultInstance();
        Chart chart = (Chart) view;

        LocalDate startDate = (LocalDate) params[0];
        LocalDate endDate = (LocalDate) params[1];

        Date iDate = startDate.toDateTimeAtStartOfDay().toDate();
        Date fDate = endDate.toDateTimeAtStartOfDay().toDate();

        boolean hasRegistries = (realm.where(Despesa.class).between("dataPaga", iDate, fDate)
                .count() + realm.where(Receita.class).between("dataPaga", iDate, fDate).count()) > 0;

        if (hasRegistries) {

            ArrayList<String> xVals = new ArrayList<>();
            ArrayList<LineDataSet> sets = new ArrayList<>();

            int xIndex = 0;

            List<Entry> valuesIncome = new ArrayList<>();
            List<Entry> valuesExpenditure = new ArrayList<>();
            List<Entry> valuesBalance = new ArrayList<>();

            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                Date dateToCompare = date.toDateTimeAtStartOfDay().toDate();
                double expenditure = realm.where(Despesa.class).equalTo("dataPaga", dateToCompare).sumDouble
                        ("valorPago");
                double income = realm.where(Receita.class).equalTo("dataPaga", dateToCompare).sumDouble("valorPago");
                valuesIncome.add(new Entry((float) income, xIndex));
                valuesExpenditure.add(new Entry((float) expenditure, xIndex));
                valuesBalance.add(new Entry((float) (income - expenditure), xIndex));
                xIndex++;
                xVals.add(SimpleDateFormat.getDateInstance().format(dateToCompare));
            }

            LineDataSet ldsIncome = UtilPanel.buildLineDataSet(valuesIncome, chart.getContext()
                    .getString(R
                            .string.incomes), Color.BLUE);
            LineDataSet ldsExpenditure = UtilPanel.buildLineDataSet(valuesExpenditure, chart.getContext().getString(R
                    .string.expenses), Color.RED);
            LineDataSet ldsBalance = UtilPanel.buildLineDataSet(valuesBalance, chart.getContext()
                    .getString(R.string.final_value), Color.GREEN);

            sets.add(ldsIncome);
            sets.add(ldsExpenditure);
            sets.add(ldsBalance);

            LineData lineData = new LineData(xVals, sets);
            chart.setData(lineData);
            chart.invalidate();
            chart.notifyDataSetChanged();
        }

        realm.close();

    }

    @Override
    public void onClick(View view) {
        edtGenericDate = (EditText) view;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = DatePickerDialog.newInstance(this, ano, mes, dia);
        dpd.setThemeDark(false);
        dpd.show(fragmentManager, "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        edtGenericDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
    }

}
