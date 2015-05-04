package extractor.com.easyfinance.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class MainActivity extends ActionBarActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_finance);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Easy Finance");
        setSupportActionBar(mToolbar);

        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        new Drawer()
            .withActivity(this)
            .withToolbar(mToolbar)
            .addDrawerItems(
                    new ProfileDrawerItem().withName("Muryllo Tiraza"),
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
                    Toast.makeText(getApplicationContext(), "Menu: " + position, Toast.LENGTH_SHORT).show();
                }
            })
            .build();
    }


}
