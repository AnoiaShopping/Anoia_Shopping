package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityMainBinding;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView () {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView (this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner (this);
        activityMainBinding.setViewModel (mainViewModel);

        mainViewModel.getDeleteResponse().observe(this, s -> {
            if (s) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings_s:
                startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
                return true;
            case R.id.logout_s:
                mainViewModel.delete_token(PreferencesProvider.providePreferences().getString("token", ""));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed () {
        finishAffinity();
    }
}