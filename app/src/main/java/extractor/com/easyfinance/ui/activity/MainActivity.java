package extractor.com.easyfinance.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import extractor.com.easyfinance.ui.fragment.ConfiguracoesFragment;
import extractor.com.easyfinance.ui.fragment.DespesasFragment;
import extractor.com.easyfinance.ui.fragment.HomeFragment;
import extractor.com.easyfinance.ui.fragment.ReceitasFragment;
import extractor.com.easyfinance.ui.fragment.SobreFragment;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class MainActivity extends ActionBarActivity {
    public Toolbar mToolbar;
    public AccountHeader.Result headerResult;
    public Drawer.Result drawerResult;

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

    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_finance);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Easy Finance");
        setSupportActionBar(mToolbar);

        fragmentManager = getSupportFragmentManager();

        headerResult = new AccountHeader()
            .withActivity(this)
            .withCompactStyle(true)
            .withHeaderBackground(R.drawable.gradient_app)
            .build();

        drawerResult = new Drawer()
            .withActivity(this)
            .withToolbar(mToolbar)
            .withAccountHeader(headerResult)
            .withActionBarDrawerToggleAnimated(true)
            .addDrawerItems(
                    new PrimaryDrawerItem().withName("Home"),
                    new PrimaryDrawerItem().withName("Receitas"),
                    new PrimaryDrawerItem().withName("Despesas"),
                    new DividerDrawerItem(),
                    new SecondaryDrawerItem().withName("Configurações"),
                    new SecondaryDrawerItem().withName("Sobre")
            )
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                    replaceFragment(getFragment(position));
                }
            })
            .build();
    }

    private void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(android.R.id.content, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    private Fragment getFragment(int position) {
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

}
