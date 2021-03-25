package dam.anoiashopping.gtidic.udl.cat.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityRegisterBinding;
import dam.anoiashopping.gtidic.udl.cat.utils.EULA;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.SignUpViewModel;

import static dam.anoiashopping.gtidic.udl.cat.utils.RegisterCheck.CheckAll;

public class RegisterActivity extends AppCompatActivity {

    protected CheckBox Accept_Terms_conditions;

    final EULA eula_dialog = new EULA (this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        initView();
    }

    private void initView () {
        SignUpViewModel signUpViewModel = new ViewModelProvider (this).get(SignUpViewModel.class);
        ActivityRegisterBinding activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activitySignupBinding.setLifecycleOwner(this);
        activitySignupBinding.setViewModel (signUpViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Mostra EULA
        Accept_Terms_conditions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eula_dialog.show(R.id.c_AcceptConditions);
            }
        });

    }
    
}