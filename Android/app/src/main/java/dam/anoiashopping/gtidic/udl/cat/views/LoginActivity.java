package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityLoginBinding;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView register;

    public LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PreferencesProvider.init(this);

        if (!PreferencesProvider.providePreferences().getString("token", "").equals("")) {

            startActivity(new Intent (LoginActivity.this, MainActivity.class));
        }

        setTheme(R.style.Theme_Android);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.b_Registre);
        register.setOnClickListener(v -> startActivity (new Intent (LoginActivity.this, RegisterActivity.class)));

        initView();
    }

    private void initView () {

        loginViewModel = new ViewModelProvider (this).get(LoginViewModel.class);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner (this);
        activityLoginBinding.setViewModel (loginViewModel);

        loginViewModel.getLoginResponse().observe(this, s -> {
            if (s) {
                startActivity (new Intent (LoginActivity.this, MainActivity.class));
            }
        });


    }

}