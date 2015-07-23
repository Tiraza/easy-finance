package br.com.extractor.easyfinance.chart;

import android.content.Context;

public class ChartException extends Exception {

    public ChartException() {

    }

    public ChartException(Throwable throwable, Context context, int resID) {
        super(context.getString(resID), throwable);
    }

    public ChartException(Context context, int resID) {
        super(context.getString(resID));
    }

    public ChartException(Throwable throwable) {
        super(throwable);
    }

}
