  package dam.anoiashopping.gtidic.udl.cat.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityRegisterBinding;
import dam.anoiashopping.gtidic.udl.cat.utils.EULA;
import dam.anoiashopping.gtidic.udl.cat.utils.ValidationResultImpl;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.SignUpViewModel;

public class RegisterActivity extends AppCompatActivity {

    CheckBox Accept_Terms_conditions;
    final EULA eula_dialog = new EULA (this);

    public SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        initView();
    }

    private void initView () {

        signUpViewModel = new ViewModelProvider (this).get (SignUpViewModel.class);
        ActivityRegisterBinding activityRegisterBinding = DataBindingUtil.setContentView (this, R.layout.activity_register);
        activityRegisterBinding.setLifecycleOwner (this);
        activityRegisterBinding.setViewModel (signUpViewModel);

        signUpViewModel.FirstNameValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iFirstName.setError(getString(validationResult.getMsgError()));
            }
        });

        signUpViewModel.LastNameValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iLastName.setError(getString(validationResult.getMsgError()));
            }
        });

        signUpViewModel.UsernameValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iUserName.setError(getString(validationResult.getMsgError()));
            }
        });

        signUpViewModel.EmailValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iEmail.setError(getString(validationResult.getMsgError()));
            }
        });

        signUpViewModel.PasswordValidator.observe(this, validationResult -> {
            if (!validationResult.isValid()) {
                activityRegisterBinding.iPassword1.setError(getString(validationResult.getMsgError()));
            }
        });

        signUpViewModel.getRegisterResponse().observe(this, s -> {
            Log.d("RegisterActivity", s);
            if (s.equals("El registre s'ha fet correctament!!!!")) {
                startActivity (new Intent (RegisterActivity.this, RegisterConfirmationActivity.class));
            }
        });

        Accept_Terms_conditions = findViewById (R.id.c_AcceptConditions);
        Accept_Terms_conditions.setOnCheckedChangeListener((buttonView, isChecked) -> {
            eula_dialog.show(R.id.c_AcceptConditions);
            signUpViewModel.EULAcheck = Accept_Terms_conditions.isChecked();
        });

    }
    
}