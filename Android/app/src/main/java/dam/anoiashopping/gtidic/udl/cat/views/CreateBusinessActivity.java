package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import dam.anoiashopping.gtidic.udl.cat.R;

public class CreateBusinessActivity extends AppCompatActivity {

    private EditText txtEditNom = findViewById(R.id.txtEditNomBotiga);
    private EditText txtEditWeb = findViewById(R.id.txtEditWebNegoci);
    private EditText txtEditDefinicio = findViewById(R.id.txtEditDefinicioBotiga);
    private EditText txtEditInstagram = findViewById(R.id.txtEditInstagram);
    private EditText TxtEditFacebook  = findViewById(R.id.txtEditFacebook);
    private EditText txtEditTwitter = findViewById(R.id.txtEditInstagram);
    private Button btCrearBotiga = findViewById(R.id.btCrearBotiga);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
        //PREPAREM SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEditTipusBotiga);
        String tipusBotiga = "";
        //Creem l'adaptador que necessita el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        //Especifiquem la llista amb la seguent linea
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apliquem l'adaptador
        spinner.setAdapter(adapter);

        //CREEM TOTS ELS BT/TXT...



    btCrearBotiga.setOnClickListener(v -> {
        //implementar regex

        Context context = getApplicationContext();
        CharSequence text = "BOTIGA REGISTRADA";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    });

    }

    private void agafarTipusNegoci(){
        String text;
        System.out.println(text = ((Spinner) findViewById(R.id.spinnerEditTipusBotiga)).getSelectedItem().toString());
    }
}