package br.com.extractor.easyfinance.chart.charts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.ui.widget.CustomCheckBox;
import br.com.extractor.easyfinance.chart.PanelException;
import br.com.extractor.easyfinance.model.Tipo;
import io.realm.Realm;
import io.realm.RealmResults;

public class ChartExpensesByType extends AbstractChart implements CompoundButton.OnCheckedChangeListener {

    private List<Long> typesSelected;

    public ChartExpensesByType() {
        typesSelected = new ArrayList<>();
    }

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
    protected void putParams(Chart chart) throws PanelException {

    }

    @Override
    protected MaterialDialog buildDialogSelectParams(final Context context, final Chart chart) {
        Realm realm = Realm.getDefaultInstance();
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
                } catch (PanelException e) {
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
        ViewGroup viewGroup = (ViewGroup) customView.findViewById(R.id.choice_type_container);

        RealmResults<Tipo> resultSet = realm.where(Tipo.class).notEqualTo("categoria", 1).findAll();

        for (Tipo tipo : resultSet) {
            CustomCheckBox checkBox = new CustomCheckBox(context);
            checkBox.setText(tipo.getDescricao());
            checkBox.setData(tipo.getId());
            checkBox.setSelected(true);
            checkBox.setOnCheckedChangeListener(this);
            viewGroup.addView(checkBox);
        }

        realm.close();
        return dialog;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        CustomCheckBox customCheckBox = (CustomCheckBox) compoundButton;
        long typeID = (long) customCheckBox.getData();
        if (isChecked) {
            typesSelected.add(typeID);
        } else if (typesSelected.contains(typeID)) {
            typesSelected.remove(typeID);
        }
    }

}
