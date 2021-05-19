package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityConfigurationBinding;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    ConfigurationViewModel configurationViewModel;
    private Button botoCrearConta;
    private Button botoActualitzarCompte;
    /*private EditText txtNomUser;
    private EditText txtCognom;
    private EditText txtNomReal;
    private EditText txtCorreu;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        initView();
        botoActualitzarCompte = findViewById(R.id.bt_actualitzarcompte);
        botoActualitzarCompte.setEnabled(false);

        botoCrearConta = findViewById(R.id.btAnarCrearBotiga);
        botoCrearConta.setOnClickListener(v -> {
            startActivity(new Intent(ConfigurationActivity.this, CreateBusinessActivity.class));
        });
        /*txtNomReal.findViewById(R.id.t_firstname);
        txtCognom.findViewById(R.id.t_lastname);
        txtNomUser.findViewById(R.id.t_username);
        txtCorreu.findViewById(R.id.t_email);
        txtChanger(txtNomReal);
        txtChanger(txtCognom);
        txtChanger(txtCorreu);
        txtChanger(txtNomUser);*/

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

    /*public void txtChanger(EditText edit){
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                botoActualitzarCompte.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/

}