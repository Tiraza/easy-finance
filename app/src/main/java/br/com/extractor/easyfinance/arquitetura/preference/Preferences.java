package br.com.extractor.easyfinance.arquitetura.preference;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.extractor.easyfinance.R;

public class Preferences {

    public static final String USERNAME = "username";

    private static SharedPreferences sharedPreferences;
    private static Context context;

    public static void init(Context context) {
        Preferences.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static String getString(String key, int defValue) {
        String defaultValue = context.getResources().getString(defValue);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void putString(String key, int value) {
        String defaultValue = context.getResources().getString(value);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, defaultValue);
        editor.apply();
    }

    public static int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}
