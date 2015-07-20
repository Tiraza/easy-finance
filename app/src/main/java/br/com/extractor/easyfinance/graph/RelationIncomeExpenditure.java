package br.com.extractor.easyfinance.graph;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import io.realm.Realm;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;

public class RelationIncomeExpenditure implements Graph {

    public PieChartData getChartData(Context context) {

        List<SliceValue> pieValues = new ArrayList<>();

        double valorTotalDespesas = Realm.getDefaultInstance().where(Despesa.class).sumDouble("valorPago");
        double valorTotalReceitas = Realm.getDefaultInstance().where(Receita.class).sumDouble("valorPago");

        SliceValue valueDespesas = new SliceValue((float) valorTotalDespesas, context
                .getResources().getColor(R.color.gr_despesas));
        valueDespesas.setLabel(context.getString(R.string.expenses));
        pieValues.add(valueDespesas);

        SliceValue valueReceitas = new SliceValue((float) valorTotalReceitas, context
                .getResources().getColor(R.color.gr_receitas));
        valueReceitas.setLabel(context.getString(R.string.incomes));
        pieValues.add(valueReceitas);

        PieChartData pieData = new PieChartData(pieValues);
        pieData.setHasLabels(true);
        pieData.setHasLabelsOnlyForSelected(false);
        pieData.setHasLabelsOutside(false);
        pieData.setHasCenterCircle(true);

        return pieData;
    }

}
