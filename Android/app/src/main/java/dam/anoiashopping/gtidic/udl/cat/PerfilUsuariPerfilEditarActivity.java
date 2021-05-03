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
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setEnabled(true);
        AdapterView.OnItemSelectedListener prova = spinner.getOnItemSelectedListener();
        checkBoxBotiguer.setOnClickListener(v -> {
            System.out.println(prova);

        });

    //NO FUNCIONA LA PART D'ASSOBRE DEL CLICK LISTENER DEL CHECKBOX PER AGAFAR EL SPINNER DONAT
    buttonConfigurarPerfil.setOnClickListener(v -> {
        //FER ELS Opacity
    });

    }

}