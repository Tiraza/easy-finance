package br.com.extractor.easyfinance.chart;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;

import br.com.extractor.easyfinance.R;

public class ChartIncomesByType extends AbstractChart {

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.chart_expenses_by_type);
    }

    @Override
    protected Chart buildChart(Context context) {
        BarChart chart = new BarChart(context);
        chart.setDrawBarShadow(true);
        chart.setDrawValueAboveBar(true);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(true);
        return chart;
    }

    @Override
    protected void putParams(Chart chart) throws ChartException {

    }

    @Override
    protected MaterialDialog buildDialogSelectParams(Context context, Chart chart) {
        return null;
    }

}