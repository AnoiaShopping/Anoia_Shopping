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

    private EditText txtEditNom;
    private EditText txtEditWeb;
    private EditText txtEditDefinicio;
    private EditText txtEditInstagram;
    private EditText TxtEditFacebook;
    private EditText txtEditTwitter;
    private Button btCrearBotiga;
    private BusinessViewModel businessViewModel;


    @SuppressLint({"Range", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);

        txtEditNom = findViewById(R.id.txtEditNomBotiga);
        txtEditWeb = findViewById(R.id.txtEditWebNegoci);
        txtEditDefinicio = findViewById(R.id.txtEditDefinicioBotiga);
        txtEditInstagram = findViewById(R.id.txtEditInstagram);
        TxtEditFacebook  = findViewById(R.id.txtEditFacebook);
        txtEditTwitter = findViewById(R.id.txtEditInstagram);
        Button btCrearBotiga = findViewById(R.id.btCrearBotiga);
        businessViewModel = new BusinessViewModel();

        //PREPAREM SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEditTipusBotiga);
        String tipusBotiga = "";
        //Creem l'adaptador que necessita el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        //Especifiquem la llista amb la seguent linea
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apliquem l'adaptador
        spinner.setAdapter(adapter);

        btCrearBotiga.setOnClickListener(v -> {
            //implementar regex
            Business business = new Business();
            business.setNom(txtEditNom.getText().toString());
            business.setDefinicio(txtEditDefinicio.getText().toString());
            business.setTipus(agafarTipusNegoci());

            businessViewModel.createBusiness(business);

            Context context = getApplicationContext();
            CharSequence text = "BOTIGA REGISTRADA";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        });
    }

    private String agafarTipusNegoci(){
        String text;
        text = ((Spinner) findViewById(R.id.spinnerEditTipusBotiga)).getSelectedItem().toString();
        return text;
    }
}