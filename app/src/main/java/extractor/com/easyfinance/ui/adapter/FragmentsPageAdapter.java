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

    public final int HOME_FRAGMENT = 0;
    public final int RECEITAS_FRAGMENT = 1;
    public final int DESPESAS_FRAGMENT = 2;
    public final int CONFIGURACOES_FRAGMENT = 4;
    public final int SOBRE_FRAGMENT = 5;

    private Context context;

    public FragmentsPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case HOME_FRAGMENT:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                return homeFragment;

            case RECEITAS_FRAGMENT:
                if(receitasFragment == null){
                    receitasFragment = new ReceitasFragment();
                }
                return receitasFragment;

            case DESPESAS_FRAGMENT:
                if(despesasFragment == null){
                    despesasFragment = new DespesasFragment();
                }
                return despesasFragment;

            case CONFIGURACOES_FRAGMENT:
                if(configuracoesFragment == null){
                    configuracoesFragment = new ConfiguracoesFragment();
                }
                return configuracoesFragment;

            case SOBRE_FRAGMENT:
                if(sobreFragment == null){
                    sobreFragment = new SobreFragment();
                }
                return sobreFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }


}
