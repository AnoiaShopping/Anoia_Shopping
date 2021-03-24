package dam.anoiashopping.gtidic.udl.cat.views.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.utils.EULA;
import static dam.anoiashopping.gtidic.udl.cat.utils.RegisterCheck.CheckAll;

public class RegisterActivity extends AppCompatActivity {

    protected EditText First_Name;
    protected EditText Last_Name;
    protected EditText User_Name;
    protected EditText Email;
    protected EditText Password1;
    protected EditText Password2;
    protected Button Register_Button;
    protected CheckBox Accept_Terms_conditions;

    final EULA eula_dialog = new EULA (this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        First_Name = findViewById (R.id.i_FirstName);
        Last_Name = findViewById (R.id.i_LastName);
        User_Name = findViewById (R.id.i_UserName);
        Email = findViewById (R.id.i_Email);
        Password1 = findViewById (R.id.i_Password1);
        Password2 = findViewById (R.id.i_Password2);
        Register_Button = findViewById (R.id.b_registrarse);
        Accept_Terms_conditions = findViewById (R.id.c_AcceptConditions);
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


        Register_Button.setOnClickListener(v -> {

            if (CheckAll(First_Name, Last_Name, User_Name, Email, Password1, Password2, Accept_Terms_conditions)) {
                startActivity(new Intent(RegisterActivity.this, RegisterConfirmationActivity.class));
            }
        });
    }
    
}