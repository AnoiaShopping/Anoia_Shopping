package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityConfigurationBinding;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    ConfigurationViewModel configurationViewModel;
    private Button botoPerfils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        initView();

        botoPerfils = findViewById(R.id.b_actualize);
        botoPerfils.setOnClickListener(v -> {
            startActivity(new Intent(ConfigurationActivity.this, UserEditActivity.class));
        });

    }

    private void initView () {



        configurationViewModel = new ViewModelProvider (this).get (ConfigurationViewModel.class);
        ActivityConfigurationBinding activityConfigurationBinding = DataBindingUtil.setContentView (this, R.layout.activity_configuration);
        activityConfigurationBinding.setLifecycleOwner (this);
        activityConfigurationBinding.setViewModel (configurationViewModel);

        configurationViewModel.getAccount();

        configurationViewModel.getAccountResponse().observe(this, accountResponse -> {
            if (accountResponse.isValid()) {
                configurationViewModel.setAccount();
            }
        });
    }
}