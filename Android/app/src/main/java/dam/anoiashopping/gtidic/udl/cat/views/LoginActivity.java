package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dam.anoiashopping.gtidic.udl.cat.R;

public class LoginActivity extends AppCompatActivity {

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Android);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.b_Registre);
        register.setOnClickListener(v -> startActivity (new Intent (LoginActivity.this, RegisterActivity.class)));

        initView();
    }

    private void initView () {



    }

}