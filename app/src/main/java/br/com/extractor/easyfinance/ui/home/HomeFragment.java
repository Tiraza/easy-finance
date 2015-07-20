package br.com.extractor.easyfinance.ui.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.graph.RelationIncomeExpenditure;
import br.com.extractor.easyfinance.ui.FragmentCommunication;
import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.view.PieChartView;

public class HomeFragment extends Fragment implements FragmentCommunication {

    @Bind(R.id.pie_chart_view)
    PieChartView pieChartView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);

        pieChartView.setPieChartData(new RelationIncomeExpenditure().getChartData(getActivity()));

        return rootView;
    }

    @Override
    public boolean hasPendencies() {
        return false;
    }

    @Override
    public void freePendencies() {

    }

}
