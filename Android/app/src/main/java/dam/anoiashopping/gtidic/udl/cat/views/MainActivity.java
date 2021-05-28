package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.adapters.BusinessAdapter;
import dam.anoiashopping.gtidic.udl.cat.adapters.BusinessDiffCallback;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityMainBinding;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    MainViewModel mainViewModel;

    private RecyclerView recyclerView;
    BusinessAdapter businessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Android);
        //setContentView(R.layout.activity_main);

        if (!PreferencesProvider.providePreferences().getString("token", "").equals("")) {
            Log.d (TAG, "L'usuari ja té token: " + PreferencesProvider.providePreferences().getString("token", "") + ". Iniciant pantalla principal.");
            initView();
        } else {
            Log.d (TAG, "L'usuari no té token. Iniciant pantalla Inici de sessió.");
            startActivity (new Intent (MainActivity.this, LoginActivity.class));
        }
    }

    public void initView () {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView (this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner (this);
        activityMainBinding.setViewModel (mainViewModel);

        recyclerView = findViewById(R.id.eventslist);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        businessAdapter = new BusinessAdapter(new BusinessDiffCallback());
        recyclerView.setAdapter (businessAdapter);

        mainViewModel.getDeleteResponse().observe(this, deleteResponse -> {
            if (deleteResponse.isValid()) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mainViewModel.getBusiness();

        mainViewModel.returnBusiness().observe(this, business -> {
            businessAdapter.submitList(business);
        });

        businessAdapter.businessListener(business -> {
             // Fer nova activitat per veure info
             Bundle b = new Bundle();
             b.putParcelable("business", business);
             startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
        });


        // Per el que reb el business
        //Bundle b = getArguments();
        //if (b != null){
        //    Jocs joc = b.getParcelable("joc");
        //    displayReceivedGame(joc);
        //}
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search_s:
                startActivity (new Intent (MainActivity.this, SearchUserActivity.class));
                return true;
            case R.id.settings_s:
                startActivity (new Intent(MainActivity.this, ConfigurationActivity.class));
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