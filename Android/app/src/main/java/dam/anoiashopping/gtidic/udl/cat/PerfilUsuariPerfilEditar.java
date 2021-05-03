package dam.anoiashopping.gtidic.udl.cat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class PerfilUsuariPerfilEditar extends AppCompatActivity {

    private CheckBox checkBoxBotiguer;
    private CheckBox checkBoxConsumidor;
    private Button buttonConfigurarPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari_perfil_editar);
        checkBoxBotiguer = findViewById(R.id.checkBoxBotiguer);
        checkBoxConsumidor = findViewById(R.id.checkBoxConsumidor);
        buttonConfigurarPerfil = findViewById(R.id.bt_ActivarOpcionsPerfil);


    buttonConfigurarPerfil.setOnClickListener(v -> {
        //FER ELS Opacity
    });

    }

}