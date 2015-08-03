package br.com.extractor.easyfinance.ui.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.chart.AbstractChart;
import br.com.extractor.easyfinance.chart.PanelException;
import br.com.extractor.easyfinance.model.domain.PanelType;
import br.com.extractor.easyfinance.ui.FragmentCommunication;
import br.com.extractor.easyfinance.ui.adapter.ChartTypeAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements FragmentCommunication, AdapterView.OnItemSelectedListener {

    @Bind(R.id.chart_container)
    FrameLayout chartContainer;

    @Bind(R.id.type_chart)
    Spinner spnTypeChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);

        spnTypeChart.setAdapter(new ChartTypeAdapter(getActivity()));
        spnTypeChart.setOnItemSelectedListener(this);

        return rootView;
    }

    @Override
    public boolean hasPendencies() {
        return false;
    }

    @Override
    public void freePendencies() {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long key) {
        try {
            AbstractChart chart = PanelType.values()[position].getChart();
            if (chartContainer.getChildCount() > 0) {
                chartContainer.removeAllViews();
            }
            if (chart != null) {
                chartContainer.addView(chart.build(getActivity(), getFragmentManager()));
            }
        } catch (PanelException e) {
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.error)
                    .content(e.getMessage())
                    .neutralText(R.string.ok)
                    .cancelable(false)
                    .show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
