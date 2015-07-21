package br.com.extractor.easyfinance.graph;

import android.content.Context;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;

import br.com.extractor.easyfinance.R;

public class RelationIncomeExpenditure extends AbstractChart {

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.graph_relation_income_expenditure);
    }

    @Override
    protected Chart buildChart(Context context) {
        return new PieChart(context);
    }

    @Override
    protected void putParams(Chart chart) throws ChartException {

    }

}
