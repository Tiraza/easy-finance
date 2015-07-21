package br.com.extractor.easyfinance.graph;

import android.content.Context;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.extractor.easyfinance.R;

public abstract class AbstractChart {

    public abstract String getDescription(Context context);

    protected abstract Chart buildChart(Context context);

    protected abstract void putParams(Chart chart) throws ChartException;

    public Chart build(Context context) throws ChartException {
        Chart chart = buildChart(context);
        putParams(chart);
        chart.setDescription(getDescription(context));
        chart.setNoDataTextDescription(context.getString(R.string.exception_no_data));
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

}
