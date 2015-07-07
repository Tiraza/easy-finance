package br.com.extractor.easyfinance.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.Fragmento;
import br.com.extractor.easyfinance.ui.configuracoes.ConfiguracoesFragment;
import br.com.extractor.easyfinance.ui.despesa.DespesaListFragment;
import br.com.extractor.easyfinance.ui.home.HomeFragment;
import br.com.extractor.easyfinance.ui.receita.ReceitaListFragment;
import br.com.extractor.easyfinance.ui.sobre.SobreFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SparseArray<Fragmento> mapFragmento;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        ButterKnife.bind(this);
        initMapFragmentos();
        setupToolbar();
        setupDrawerContent();
        changeFragment(R.id.menu_home);
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
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void initMapFragmentos() {
        if (mapFragmento == null) {
            mapFragmento = new SparseArray<>();
            mapFragmento.put(R.id.menu_home, new Fragmento(new HomeFragment(), R.string.home));
            mapFragmento.put(R.id.menu_receitas, new Fragmento(new ReceitaListFragment(), R.string
                    .receitas));
            mapFragmento.put(R.id.menu_despesas, new Fragmento(new DespesaListFragment(), R.string
                    .despesas));
            mapFragmento.put(R.id.menu_configuracoes, new Fragmento(new ConfiguracoesFragment(),
                    R.string.configuracoes));
            mapFragmento.put(R.id.menu_sobre, new Fragmento(new SobreFragment(), R.string.sobre));
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
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragmento.getFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(fragmento.getTitle());
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 2) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
