package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.bouncycastle.util.Strings;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.Utils;
import dam.anoiashopping.gtidic.udl.cat.utils.Validation;

public class RecoveryCodeActivity extends AppCompatActivity {
    private AccountRepo account = new AccountRepo();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_conta);

        EditText txtEditCorreu = findViewById(R.id.txtEditCorreu);
        EditText txtEditCode = findViewById(R.id.txtEditRecoveryCode);
        EditText txtEditContrasenya1 = findViewById(R.id.txtEditNewPassword);
        EditText txtEditContrasenya2 = findViewById(R.id.txtEditNewPassword2);
        Button btEnviarCorreu = findViewById(R.id.bt_EnviarCorreu);
        Button btCanviarContrasenya = findViewById(R.id.bt_CanviarContrasenya);
        TextView txtViewCode = findViewById(R.id.txtInformacio2);
        TextView txtViewPass = findViewById(R.id.txtInformacio3);

        btCanviarContrasenya.setEnabled(false);txtViewCode.setEnabled(false);txtViewPass.setEnabled(false);txtEditContrasenya1.setEnabled(false);txtEditContrasenya2.setEnabled(false);txtEditCode.setEnabled(false);

        btEnviarCorreu.setOnClickListener(v -> {

            if(txtEditCorreu.getText().toString().matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")){
                btCanviarContrasenya.setEnabled(true);txtViewCode.setEnabled(true);txtViewPass.setEnabled(true);txtEditContrasenya1.setEnabled(true);txtEditContrasenya2.setEnabled(true);txtEditCode.setEnabled(true);
                account.recovery_password(txtEditCorreu.getText().toString()); //envio
            }

        });

        btCanviarContrasenya.setOnClickListener(v -> {
            String contra1 = txtEditContrasenya1.getText().toString();
            String contra2 = txtEditContrasenya2.getText().toString();

            if(contra1.equals(contra2)) {
                if(contra1.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")){
                    account.update_password(txtEditCorreu.getText().toString(), Utils.encode(txtEditContrasenya1.getText().toString(), "16", 29000), txtEditCode.getText().toString());
                    startActivity(new Intent(RecoveryCodeActivity.this, LoginActivity.class));
                }else{
                    txtEditContrasenya1.setError(getString(R.string.wrongPassword));
                }
            }else{
                txtEditContrasenya2.setError(getString(R.string.samePassword));
            }

        });


    }
}