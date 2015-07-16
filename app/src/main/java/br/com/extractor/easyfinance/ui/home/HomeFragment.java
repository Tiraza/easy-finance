package br.com.extractor.easyfinance.ui.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.Despesa;
import br.com.extractor.easyfinance.model.Receita;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class HomeFragment extends Fragment {

    @Bind(R.id.pie_chart_view)
    PieChartView pieChartView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);

        List<SliceValue> pieValues = new ArrayList<>();

        double valorTotalDespesas = Realm.getDefaultInstance().where(Despesa.class).sumDouble("valorPago");
        SliceValue valueDespesas = new SliceValue((float) valorTotalDespesas, getResources().getColor(R.color.gr_despesas));
        valueDespesas.setLabel(getString(R.string.despesas));
        pieValues.add(valueDespesas);

        double valorTotalReceitas = Realm.getDefaultInstance().where(Receita.class).sumDouble("valorPago");
        SliceValue valueReceitas = new SliceValue((float) valorTotalReceitas, getResources().getColor(R.color.gr_receitas));
        valueReceitas.setLabel(getString(R.string.receitas));
        pieValues.add(valueReceitas);

        PieChartData pieData = new PieChartData(pieValues);
        pieData.setHasLabels(true);
        pieData.setHasLabelsOnlyForSelected(false);
        pieData.setHasLabelsOutside(false);
        pieData.setHasCenterCircle(true);

        pieChartView.setPieChartData(pieData);

        return rootView;
    }
}
