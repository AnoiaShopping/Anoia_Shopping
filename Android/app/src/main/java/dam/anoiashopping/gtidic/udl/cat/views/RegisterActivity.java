  package dam.anoiashopping.gtidic.udl.cat.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityRegisterBinding;
import dam.anoiashopping.gtidic.udl.cat.utils.EULA;
import dam.anoiashopping.gtidic.udl.cat.utils.ValidationResultImpl;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.SignUpViewModel;

  public class RegisterActivity extends AppCompatActivity {

    CheckBox Accept_Terms_conditions;
    final EULA eula_dialog = new EULA (this);
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        initView();
    }

    private void initView () {

        SignUpViewModel signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        ActivityRegisterBinding activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setLifecycleOwner(this);
        activityRegisterBinding.setViewModel (signUpViewModel);

        // Fixa't que observes les modificacions:
        signUpViewModel.FirstNameValidator.observe(this,
                validationResultI -> activityRegisterBinding.iFirstName.setError(getString(validationResultI.getMsgError())));

        Accept_Terms_conditions = findViewById (R.id.c_AcceptConditions);
        Accept_Terms_conditions.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eula_dialog.show(R.id.c_AcceptConditions);
            }
        });

        //register = findViewById (R.id.b_registrarse);
        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Aqui posava el que m'havies posat al correu a la part del activity
                // No cal les modificacions les fa el viewModel.
            }
        });*/


    }
}