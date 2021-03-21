package dam.anoiashopping.gtidic.udl.cat.views.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.utils.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText First_Name;
    private EditText Last_Name;
    private EditText User_name;
    private EditText Email;
    private EditText Password1;
    private EditText Password2;
    private Button Register_Button;
    private CheckBox Accept_Terms_conditions;
    private Login_Utils Login_Utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        First_Name = findViewById (R.id.i_FirstName);
        Last_Name = findViewById (R.id.i_LastName);
        User_name = findViewById (R.id.i_UserName);
        Email = findViewById (R.id.i_Email);
        Password1 = findViewById (R.id.i_Password1);
        Password2 = findViewById (R.id.i_Password2);
        Register_Button = findViewById (R.id.b_registrarse);
        Accept_Terms_conditions = findViewById (R.id.c_AcceptConditions);
        Login_Utils = new Login_Utils();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Mostra EULA
        /*Accept_Terms_conditions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });*/


        Register_Button.setOnClickListener(v -> {
            if (CheckAll()) {
                startActivity(new Intent(RegisterActivity.this, RegisterConfirmationActivity.class));
            }
        });
    }

    // Comprobem que totes les dades estiguin plenes per registrar-se
    protected boolean CheckAll () {
        int i = 0;

        if (First_Name.getText().toString().isEmpty()) {
            First_Name.setError("Escriu el teu Nom");
            i++;
        }

        if (Last_Name.getText().toString().isEmpty()) {
            Last_Name.setError("Escriu el teu Cognom");
            i++;
        }

        if (User_name.getText().toString().isEmpty()) {
            User_name.setError("Escriu el teu Nom d'Usuari");
            i++;
        }

        if (Email.getText().toString().isEmpty()) {
            Email.setError("Escriu el teu Correu");
            i++;
        } else if (!Login_Utils.isValidEmailAddress(Email.getText().toString())) {
            Email.setError("Correu Invàlid");
            i++;
        }

        if (Password1.getText().toString().isEmpty()) {
            Password1.setError("Escriu una Contrassenya");
            i++;
        } else if (!Login_Utils.isValidPassword(Password1.getText().toString())) {
            Password1.setError("Contrassenya Invàlida");
            i++;
        }

        if (Password2.getText().toString().isEmpty()) {
            Password2.setError("Escriu una Contrassenya");
            i++;
        } else if (!Login_Utils.isValidPassword(Password2.getText().toString())) {
            Password2.setError("Contrassenya Invàlida");
            i++;
        } else if (!Password1.getText().toString().equals(Password2.getText().toString())) {
            Password2.setError("Les Contrassenyes han de coincidir");
            i++;
        }

        if (!Accept_Terms_conditions.isChecked()) {
            Accept_Terms_conditions.setError("Has d'acceptar els Termes i Condicions per registrar-te");
            i++;
        }
        return i == 0;
    }
}