package extractor.com.easyfinance.ui.activity;

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

import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class MainActivity extends ActionBarActivity {
    private Toolbar mToolbar;

    private AccountHeader.Result headerResult;
    private Drawer.Result drawerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_finance);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Easy Finance");
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);

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
                    Toast.makeText(getApplicationContext(), "Menu: " + position, Toast.LENGTH_SHORT).show();
                }
            })
            .build();
    }


}
