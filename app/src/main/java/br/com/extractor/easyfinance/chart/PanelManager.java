package br.com.extractor.easyfinance.chart;

import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.Chart;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.domain.PanelType;

public class PanelManager {

    private final Context context;

    public PanelManager(Context context) {
        this.context = context;
    }

    public void build(ViewGroup viewGroup, PanelType panelType, FragmentManager fragmentManager) {
        Panel panel = panelType.getPanel();
        View view = panel.buildView(context, viewGroup, fragmentManager);
        try {
            Chart chart = (Chart) view;
            initChart(panel, chart);
        } catch (ClassCastException e) {

        }
        panel.obtainParamsAndProcess(context, view);
    }

    private void initChart(Panel panel, Chart chart) {
        Context context = chart.getContext();
        chart.setDescription(panel.getDescription(context));
        chart.setHardwareAccelerationEnabled(true);
        chart.setDescriptionTextSize(13f);
        chart.setNoDataText(context.getString(R.string.exception_chart_no_data));
        chart.setHighlightEnabled(true);
    }

}
