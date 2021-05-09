package dam.anoiashopping.gtidic.udl.cat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class PerfilUsuariPerfilEditarActivity extends AppCompatActivity {

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
        //INICIEM TOT EN DISABLED
        ////////////////////////////////////////////////////////////////////////////
        Spinner spinner = (Spinner) findViewById(R.id.spinnerBotigues);
        String tipusBotiga = "";

        //Creem l'adaptador que necessita el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        //Especifiquem la llista amb la seguent linea
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apliquem l'adaptador
        spinner.setAdapter(adapter);
        spinner.setEnabled(true);

        checkBoxBotiguer.setOnClickListener(v -> {
            agafarTipusNegoci();
        });

    //NO FUNCIONA LA PART D'ASSOBRE DEL CLICK LISTENER DEL CHECKBOX PER AGAFAR EL SPINNER DONAT
    buttonConfigurarPerfil.setOnClickListener(v -> {
        //FER ELS Opacity
    });

    }
    private void agafarTipusNegoci(){
        String text;
        System.out.println(text = ((Spinner) findViewById(R.id.spinnerBotigues)).getSelectedItem().toString());
    }
}