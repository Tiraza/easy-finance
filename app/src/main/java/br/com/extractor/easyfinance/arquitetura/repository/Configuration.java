package br.com.extractor.easyfinance.arquitetura.repository;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Configuration {

    private final Context context;

    public Configuration(Context context) {
        this.context = context;
    }

    public void configure() {
        try {
            Realm.getDefaultInstance();
        } catch (Exception e) {
            RealmConfiguration config = new RealmConfiguration.Builder(context)
                    .build();
            Realm.setDefaultConfiguration(config);
        }
    }

}
