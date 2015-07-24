package br.com.extractor.easyfinance.chart;

import android.content.Context;

public class PanelException extends Exception {

    public PanelException() {

    }

    public PanelException(Throwable throwable, Context context, int resID) {
        super(context.getString(resID), throwable);
    }

    public PanelException(Context context, int resID) {
        super(context.getString(resID));
    }

    public PanelException(Throwable throwable) {
        super(throwable);
    }

}
