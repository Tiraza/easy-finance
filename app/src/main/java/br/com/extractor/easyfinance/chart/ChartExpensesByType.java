package br.com.extractor.easyfinance.chart;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;

import br.com.extractor.easyfinance.R;

public class ChartExpensesByType extends AbstractChart {

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.chart_incomes_by_type);
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
    protected MaterialDialog buildDialogSelectParams(final Context context, final Chart chart) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);

        builder.title(R.string.dialog_select_params);
        builder.cancelable(false);
        builder.customView(R.layout.dialog_chart_expenses_by_type, true);
        builder.positiveText(R.string.ok);
        builder.negativeText(R.string.cancel);
        builder.autoDismiss(false);
        builder.callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                try {
                    putParams(chart);
                    dialog.dismiss();
                } catch (ChartException e) {
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

        return dialog;
    }

}
