package extractor.com.easyfinance.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import extractor.com.easyfinance.ui.fragment.ConfiguracoesFragment;
import extractor.com.easyfinance.ui.fragment.DespesasFragment;
import extractor.com.easyfinance.ui.fragment.HomeFragment;
import extractor.com.easyfinance.ui.fragment.ReceitasFragment;
import extractor.com.easyfinance.ui.fragment.SobreFragment;

/**
 * @author Muryllo Tiraza
 */
public class FragmentsPageAdapter extends FragmentPagerAdapter {

    private HomeFragment homeFragment;
    private DespesasFragment despesasFragment;
    private ReceitasFragment receitasFragment;
    private ConfiguracoesFragment configuracoesFragment;
    private SobreFragment sobreFragment;

    private Context context;

    public FragmentsPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }


}
