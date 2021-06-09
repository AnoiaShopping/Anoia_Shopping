package dam.anoiashopping.gtidic.udl.cat.views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import dam.anoiashopping.gtidic.udl.cat.R;

public class RegisterConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirmation);

        Button LogIn =  findViewById(R.id.b_LogIn2);

        LogIn.setOnClickListener(v -> startActivity (new Intent(RegisterConfirmationActivity.this, LoginActivity.class)));
    }
}