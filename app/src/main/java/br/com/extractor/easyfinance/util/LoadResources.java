package br.com.extractor.easyfinance.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadResources {

    private static String license;

    public static String loadLicense(Context context) {

        if (license == null) {

            Log.d("Resources", "Trying to load license resource");
            InputStream raw = null;

            try {

                raw = context.getResources().openRawResource(
                        context.getResources().getIdentifier("raw/license",
                                "raw", context.getPackageName()));

                BufferedReader br = new BufferedReader(new InputStreamReader(raw, "UTF8"));
                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                license = sb.toString();

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (raw != null) {
                        raw.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            Log.d("Resources", "License resource loaded");

        }

        return license;
    }

}
