package extractor.com.easyfinance.arquitetura;

import android.app.Fragment;

public class Fragmento {

    private final Fragment fragment;
    private final int title;

    public Fragmento(Fragment fragment, int title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragmento(Fragment fragment) {
        this(fragment, 0);
    }

    public Fragment getFragment() {
        return fragment;
    }

    public int getTitle() {
        return title;
    }

}
