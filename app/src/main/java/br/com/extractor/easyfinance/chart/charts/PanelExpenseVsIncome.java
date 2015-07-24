package br.com.extractor.easyfinance.chart.charts;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.chart.Panel;
import br.com.extractor.easyfinance.chart.PanelException;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import io.realm.Realm;

public class PanelExpenseVsIncome implements Panel {

    @Override
    public View buildView(Context context, ViewGroup viewGroup, FragmentManager fragmentManager) {
        PieChart chart = new PieChart(context);
        chart.setUsePercentValues(true);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColorTransparent(true);
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        return chart;
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.panel_income_vs_expense);
    }

    @Override
    public void obtainParamsAndProcess(Context context, View view) {
        processParams(view);
    }

    @Override
    public Object[] validateParams(View view, Object... params) throws PanelException {
        return new Object[0];
    }

    @Override
    public void processParams(View view, Object... params) {

        Chart chart = (Chart) view;

        List<Entry> yVals1 = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        double totalDespesa = realm.where(Despesa.class).sumDouble("valorPago");
        double totalReceita = realm.where(Receita.class).sumDouble("valorPago");

        if (totalDespesa + totalReceita > 0) {
            yVals1.add(new Entry((float) totalDespesa, 0));
            yVals1.add(new Entry((float) totalReceita, 1));

            xVals.add(chart.getContext().getString(R.string.expenses));
            xVals.add(chart.getContext().getString(R.string.incomes));

            PieDataSet dataSet = new PieDataSet(yVals1, "");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);

            ArrayList<Integer> colors = new ArrayList<>();

            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);

            PieData data = new PieData(xVals, dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(14f);
            data.setValueTextColor(Color.BLACK);
            chart.setData(data);

            chart.highlightValues(null);

            chart.invalidate();
        }

        realm.close();
    }

}
