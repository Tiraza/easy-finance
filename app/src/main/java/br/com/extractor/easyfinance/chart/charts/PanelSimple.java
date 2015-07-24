package br.com.extractor.easyfinance.chart.charts;

import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.chart.Panel;
import br.com.extractor.easyfinance.chart.PanelException;

public class PanelSimple implements Panel {

    @Override
    public View buildView(Context context, ViewGroup viewGroup, FragmentManager fragmentManager) {
        return View.inflate(context, R.layout.info_dashboard, viewGroup);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.panel_simple);
    }

    @Override
    public void obtainParamsAndProcess(Context context, View view) {
        try {
            validateParams(view);
            processParams(view);
        } catch (PanelException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Object[] validateParams(View view, Object... params) throws PanelException {
        return null;
    }

    @Override
    public void processParams(View view, Object... params) {

    }

}
