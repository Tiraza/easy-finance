package br.com.extractor.easyfinance.util;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

public class UtilPanel {

    public static LineDataSet buildLineDataSet(List<Entry> entries, String tag, int color) {
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
