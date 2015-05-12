package extractor.com.easyfinance.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
                    new PrimaryDrawerItem().withName(R.string.home),
                    new PrimaryDrawerItem().withName(R.string.receitas),
                    new PrimaryDrawerItem().withName(R.string.despesas),
                    new DividerDrawerItem(),
                    new SecondaryDrawerItem().withName(R.string.configuracoes),
                    new SecondaryDrawerItem().withName(R.string.sobre)
            )
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                    /*for (int count = 0, tam = 2; count <= tam; count++) {
                        PrimaryDrawerItem aux = (PrimaryDrawerItem) drawerResult.getDrawerItems().get(count);
                        //aux.setIcon(getResources().getDrawable(getIcon(count, false)));
                        aux.setIconColor(getResources().getColor(R.color.black));
                    }

                    if (position <= 2) {
                        PrimaryDrawerItem aux = (PrimaryDrawerItem) drawerResult.getDrawerItems().get(position);
                        //aux.setIcon(getResources().getDrawable(getIcon(position, true)));
                        aux.setIconColor(getResources().getColor(R.color.colorPrimaryDark));
                    }*/

                    replaceFragment(getFragment(position));
                }
            })
            .build();

        replaceFragment(getFragment(HOME_FRAGMENT));
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        }
        super.onBackPressed();
    }

    public void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame_container, fragment);
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

    private int getIcon(int position, Boolean isSelected){
        switch (position){
            case HOME_FRAGMENT:
                return (isSelected ? R.mipmap.ic_home_dark_color : R.mipmap.ic_home);

            case RECEITAS_FRAGMENT:
                return (isSelected ? R.mipmap.ic_receita_dark_color : R.mipmap.ic_receita);

            case DESPESAS_FRAGMENT:
                return (isSelected ? R.mipmap.ic_despesa_dark_color : R.mipmap.ic_despesa);

            default:
                return 0;
        }
    }

}
