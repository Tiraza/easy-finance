package br.com.extractor.easyfinance.arquitetura.repository;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Configuration {

    private final Context context;

    public Configuration(Context context) {
        this.context = context;
    }

    public void configure() {
        try {
            Log.d("DB", "Verifying database ... ");
            Realm.getDefaultInstance();
        } catch (Exception e) {
            Log.d("DB", "Configuring database ... ");
            RealmConfiguration config = new RealmConfiguration.Builder(context)
                    .build();
            Realm.setDefaultConfiguration(config);
        }
        Log.d("DB", "Database configured!");
    }

}
