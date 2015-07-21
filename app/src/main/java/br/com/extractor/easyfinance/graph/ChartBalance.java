package br.com.extractor.easyfinance.graph;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import io.realm.Realm;

public class ChartBalance extends AbstractChart {

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.graph_balance);
    }

    @Override
    protected Chart buildChart(Context context) {
        return new LineChart(context);
    }

    @Override
    protected void putParams(Chart chart) throws ChartException {

        Realm realm = Realm.getDefaultInstance();

        //TODO: exibir diálogo que mostra os parâmetros para a exibição do gráfico

        LocalDate startDate = new LocalDate(normalizeDate(true, new Date()));
        LocalDate endDate = new LocalDate(normalizeDate(false, new Date()));

        if (startDate.isAfter(endDate)) {
            throw new ChartException(chart.getContext(), R.string.exception_graph_date_initial_invalid);
        }

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<LineDataSet> sets = new ArrayList<>();

        int xIndex = 0;

        List<Entry> valuesIncome = new ArrayList<>();
        List<Entry> valuesExpenditure = new ArrayList<>();

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            Date dateToCompare = date.toDateTimeAtStartOfDay().toDate();
            double expenditure = realm.where(Despesa.class).equalTo("dataPaga", dateToCompare).sumDouble
                    ("valorPago");
            double income = realm.where(Receita.class).equalTo("dataPaga", dateToCompare).sumDouble("valorPago");
            valuesIncome.add(new Entry((float) income, xIndex));
            valuesExpenditure.add(new Entry((float) expenditure, xIndex));
            xIndex++;
            xVals.add(SimpleDateFormat.getDateInstance().format(dateToCompare));
        }

        LineDataSet ldsIncome = buildLineDataSet(valuesIncome, chart.getContext().getString(R
                .string.incomes), Color.GREEN);
        LineDataSet ldsExpenditure = buildLineDataSet(valuesExpenditure, chart.getContext().getString(R
                .string.expenses), Color.RED);

        sets.add(ldsIncome);
        sets.add(ldsExpenditure);

        LineData lineData = new LineData(xVals, sets);
        chart.setData(lineData);
        chart.invalidate();
        chart.notifyDataSetChanged();

    }

}
