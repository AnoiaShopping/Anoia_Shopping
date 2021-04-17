  package dam.anoiashopping.gtidic.udl.cat.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityRegisterBinding;
import dam.anoiashopping.gtidic.udl.cat.utils.EULA;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivity";

    CheckBox Accept_Terms_conditions;
    final EULA eula_dialog = new EULA (this);

    public RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        initView();
    }

    private void initView () {

        registerViewModel = new ViewModelProvider (this).get (RegisterViewModel.class);
        ActivityRegisterBinding activityRegisterBinding = DataBindingUtil.setContentView (this, R.layout.activity_register);
        activityRegisterBinding.setLifecycleOwner (this);
        activityRegisterBinding.setViewModel (registerViewModel);

        registerViewModel.FirstNameValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iFirstName.setError(getString(validationResult.getMsgError()));
            }
        });

        registerViewModel.LastNameValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iLastName.setError(getString(validationResult.getMsgError()));
            }
        });

        registerViewModel.UsernameValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iUserName.setError(getString(validationResult.getMsgError()));
            }
        });

        registerViewModel.EmailValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iEmail.setError(getString(validationResult.getMsgError()));
            }
        });

        registerViewModel.PasswordValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iPassword1.setError(getString(validationResult.getMsgError()));
            }
        });

        registerViewModel.getRegisterResponse().observe(this, s -> {
            
            if (s.isValid()) {
                startActivity(new Intent(RegisterActivity.this, RegisterConfirmationActivity.class));
            }
        });

        Accept_Terms_conditions = findViewById (R.id.c_AcceptConditions);
        Accept_Terms_conditions.setOnCheckedChangeListener((buttonView, isChecked) -> {
            eula_dialog.show(R.id.c_AcceptConditions);
            registerViewModel.EULA_Check = Accept_Terms_conditions.isChecked();
        });
    }
    
}