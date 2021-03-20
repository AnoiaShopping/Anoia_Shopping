package dam.anoiashopping.gtidic.udl.cat.views.register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.utils.Login_Utils;

public class RegisterActivity extends AppCompatActivity {

    private EditText First_Name;
    private EditText Last_Name;
    private EditText User_name;
    private EditText Email;
    private EditText Password1;
    private EditText Password2;
    private Button Register_Button;
    private Login_Utils Login_Utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        First_Name = findViewById(R.id.i_FirstName);
        Last_Name = findViewById(R.id.i_LastName);
        User_name = findViewById(R.id.i_UserName);
        Email = findViewById(R.id.i_Email);
        Password1 = findViewById(R.id.i_Password1);
        Password2 = findViewById(R.id.i_Password2);
        Register_Button = findViewById(R.id.bt_registrarse);
        Login_Utils = new Login_Utils();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Register_Button.setOnClickListener(v -> {
            System.out.println(Login_Utils.isValidEmailAddress(Email.getText().toString()));
            System.out.println(Login_Utils.isValidPassword(Password1.getText().toString()));
            System.out.println(Login_Utils.isValidPassword(Password2.getText().toString()));
            //ENLLOC DE FER ELS SYSYTEM OUT POSAREM UN IF TRUE O FALSE DIR QUE S'HA REGISTRAT O NO
        });
    }
}