package br.com.extractor.easyfinance.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.repository.Configuration;
import br.com.extractor.easyfinance.mock.Mock;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Configuration(this).configure();
        new Mock().mockApplication();

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }
}
