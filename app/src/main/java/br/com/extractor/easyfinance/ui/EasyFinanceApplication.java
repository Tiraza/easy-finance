package br.com.extractor.easyfinance.ui;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class EasyFinanceApplication extends Application {

    public static Application application;
    public static GoogleAnalytics analytics;
    public static Tracker defaultTracker;

    @Override
    public void onCreate() {
        application = this;
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        defaultTracker = analytics.newTracker("UA-00000000-0");
        defaultTracker.enableExceptionReporting(true);
        defaultTracker.enableAdvertisingIdCollection(true);
        defaultTracker.enableAutoActivityTracking(true);
    }

}
