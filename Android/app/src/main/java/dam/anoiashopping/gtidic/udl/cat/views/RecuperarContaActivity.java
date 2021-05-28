package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dam.anoiashopping.gtidic.udl.cat.R;

public class RecuperarContaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_conta);

        EditText txtEditCorreu = findViewById(R.id.txtEditCorreu);
        EditText txtEditCode = findViewById(R.id.txtEditRecoveryCode);
        EditText txtEditContrasenya1 = findViewById(R.id.txtEditNewPassword);
        EditText txtEditContrasenya2 = findViewById(R.id.txtEditNewPassword2);
        Button btEnviarCorreu = findViewById(R.id.bt_EnviarCorreu);Button btCanviarContrasenya = findViewById(R.id.bt_CanviarContrasenya);
        TextView txtViewCode = findViewById(R.id.txtInformacio2);TextView txtViewPass = findViewById(R.id.txtInformacio3);

        btCanviarContrasenya.setEnabled(false);txtViewCode.setEnabled(false);txtViewPass.setEnabled(false);txtEditContrasenya1.setEnabled(false);txtEditContrasenya2.setEnabled(false);txtEditCode.setEnabled(false);

        btEnviarCorreu.setOnClickListener(v -> {

            if(txtEditCorreu.getText().toString().matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")){
                btCanviarContrasenya.setEnabled(true);txtViewCode.setEnabled(true);txtViewPass.setEnabled(true);txtEditContrasenya1.setEnabled(true);txtEditContrasenya2.setEnabled(true);txtEditCode.setEnabled(true);


            }

        });


    }
}