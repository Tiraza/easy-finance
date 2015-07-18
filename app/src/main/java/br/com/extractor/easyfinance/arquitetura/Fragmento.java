package br.com.extractor.easyfinance.arquitetura;

import android.app.Fragment;

public class Fragmento {

    private final Fragment fragment;
    private final int title;

    public Fragmento(Fragment fragment, int title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public int getTitle() {
        return title;
    }

}
