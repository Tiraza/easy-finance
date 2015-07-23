package br.com.extractor.easyfinance.chart;

import android.app.FragmentManager;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.extractor.easyfinance.R;

public abstract class AbstractChart {

    private FragmentManager fragmentManager;

    public abstract String getDescription(Context context);

    protected abstract Chart buildChart(Context context);

    protected abstract void putParams(Chart chart) throws ChartException;

    protected abstract MaterialDialog buildDialogSelectParams(Context context, Chart chart);

    public Chart build(Context context, FragmentManager fragmentManager) throws ChartException {
        this.fragmentManager = fragmentManager;
        Chart chart = buildChart(context);
        MaterialDialog dialog = buildDialogSelectParams(context, chart);
        if (dialog != null) {
            dialog.show();
        } else {
            putParams(chart);
        }
        chart.setDescription(getDescription(context));
        chart.setHardwareAccelerationEnabled(true);
        chart.setDescriptionTextSize(13f);
        chart.setNoDataText(context.getString(R.string.exception_chart_no_data));
        chart.setHighlightEnabled(true);
        return chart;
    }

    public Date normalizeDate(boolean startOfDay, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (startOfDay) {
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        } else {
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
        }
        return calendar.getTime();
    }

    public LineDataSet buildLineDataSet(List<Entry> entries, String tag, int color) {
        LineDataSet lineDataSet = new LineDataSet(entries, tag);
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleSize(4f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(14f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setFillColor(color);
        return lineDataSet;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

}
