package br.com.extractor.easyfinance.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.analytics.Tracker;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.Fragmento;
import br.com.extractor.easyfinance.arquitetura.preference.Preferences;
import br.com.extractor.easyfinance.ui.configuracoes.ConfiguracoesFragment;
import br.com.extractor.easyfinance.ui.despesa.DespesaListFragment;
import br.com.extractor.easyfinance.ui.home.HomeFragment;
import br.com.extractor.easyfinance.ui.receita.ReceitaListFragment;
import br.com.extractor.easyfinance.ui.sobre.SobreFragment;
import br.com.extractor.easyfinance.ui.tipo.TipoListFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ActivityCommunication {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.txt_username)
    TextView txtUsername;

    private ActionBarDrawerToggle drawerToggle;
    private SparseArray<Fragmento> mapFragmento;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;
    private FragmentCommunication fragmentCommunication;
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        ButterKnife.bind(this);
        initMapFragmentos();
        setupToolbar();
        setupDrawerContent();
        changeFragment(R.id.menu_dashboard);
        tracker = EasyFinanceApplication.defaultTracker;
        updateValues();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        actionBar = getSupportActionBar();
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_drawer_open, R
                .string.nav_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initMapFragmentos() {
        if (mapFragmento == null) {
            mapFragmento = new SparseArray<>();
            mapFragmento.put(R.id.menu_dashboard, new Fragmento(new HomeFragment(), R.string.dashboard));
            mapFragmento.put(R.id.menu_receitas, new Fragmento(new ReceitaListFragment(), R.string
                    .incomes));
            mapFragmento.put(R.id.menu_despesas, new Fragmento(new DespesaListFragment(), R.string
                    .expenses));
            mapFragmento.put(R.id.menu_tipo, new Fragmento(new TipoListFragment(), R.string
                    .type));
            mapFragmento.put(R.id.menu_configuracoes, new Fragmento(new ConfiguracoesFragment(),
                    R.string.settings));
            mapFragmento.put(R.id.menu_sobre, new Fragmento(new SobreFragment(), R.string.about));
        }
    }

    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        changeFragment(menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void changeFragment(int itemId) {
        Fragmento fragmento = mapFragmento.get(itemId);
        Fragment fragment = fragmento.getFragment();
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.commit();
            actionBar.setTitle(fragmento.getTitle());
            fragmentCommunication = (FragmentCommunication) fragment;
        } catch (ClassCastException e) {
            throw new RuntimeException(fragment.getClass().getSimpleName() + " must implement " +
                    "FragmentCommunication ");
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                if (fragmentCommunication.hasPendencies()) {
                    fragmentCommunication.freePendencies();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void updateValues() {
        txtUsername.setText(Preferences.getString(Preferences.USERNAME, R.string.username));
    }

}
