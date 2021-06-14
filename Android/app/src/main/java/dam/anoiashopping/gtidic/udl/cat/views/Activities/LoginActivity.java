package dam.anoiashopping.gtidic.udl.cat.views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityLoginBinding;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    public LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (!PreferencesProvider.providePreferences().getString("token", "").equals("")) {
            Log.d (TAG, "L'usuari ja té token: " + PreferencesProvider.providePreferences().getString("token", "") + ". Iniciant pantalla principal.");
            startActivity (new Intent (LoginActivity.this, MenuActivity.class));
            setTheme(R.style.AppTheme);
        } else {
            Log.d (TAG, "L'usuari no té token. Iniciant pantalla Inici de sessió.");
            setTheme(R.style.AppTheme);
            setContentView(R.layout.activity_login);
            initView();
        }
    }

    private void initView () {

        loginViewModel = new ViewModelProvider (this).get(LoginViewModel.class);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner (this);
        activityLoginBinding.setViewModel (loginViewModel);

        TextView b_lostpassword = findViewById(R.id.b_lostpassword);

        loginViewModel.registerClick.observe(this, registerClick -> startActivity (new Intent (LoginActivity.this, RegisterActivity.class)));
        b_lostpassword.setOnClickListener(v -> {
            startActivity (new Intent (LoginActivity.this, RecoveryCodeActivity.class));
        });

        loginViewModel.getLoginResponse().observe(this, loginResponse -> {

            if (loginResponse.isValid()) {

                Log.d (TAG, "Login correcte");
                Toast.makeText(getApplicationContext(), R.string.OkLogIn, Toast.LENGTH_SHORT).show();
                startActivity (new Intent (LoginActivity.this, MenuActivity.class));

            } else {

                Log.d (TAG, "Login incorrecte");
                Toast.makeText(getApplicationContext(), R.string.FailLogIn, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed () {
        finishAffinity();
    }
}