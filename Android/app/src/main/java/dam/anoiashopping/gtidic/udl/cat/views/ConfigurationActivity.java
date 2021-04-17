package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityConfigurationBinding;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    ConfigurationViewModel configurationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        initView();
    }

    private void initView () {

        configurationViewModel = new ViewModelProvider (this).get (ConfigurationViewModel.class);
        ActivityConfigurationBinding activityConfigurationBinding = DataBindingUtil.setContentView (this, R.layout.activity_configuration);
        activityConfigurationBinding.setLifecycleOwner (this);
        activityConfigurationBinding.setViewModel (configurationViewModel);

        configurationViewModel.getAccount();
    }
}