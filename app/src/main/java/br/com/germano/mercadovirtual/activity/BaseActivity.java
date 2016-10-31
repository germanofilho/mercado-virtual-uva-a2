package br.com.germano.mercadovirtual.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by germano on 25/10/16.
 */

public class BaseActivity extends Activity {
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
