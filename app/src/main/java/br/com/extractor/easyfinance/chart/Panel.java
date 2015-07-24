package br.com.extractor.easyfinance.chart;

import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface Panel {

    View buildView(Context context, ViewGroup viewGroup, FragmentManager fragmentManager);

    String getDescription(Context context);

    void obtainParamsAndProcess(Context context, View view);

    Object[] validateParams(View view, Object... params) throws PanelException;

    void processParams(View view, Object... params);

}
