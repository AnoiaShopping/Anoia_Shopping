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

public class UserEditActivity extends AppCompatActivity {

    private CheckBox checkBoxBotiguer;
    private CheckBox checkBoxConsumidor;
    private Button buttonConfigurarPerfil;
    private Button buttonActualitzar;



    @SuppressLint({"Range", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        checkBoxBotiguer = findViewById(R.id.checkBoxBotiguer);
        checkBoxConsumidor = findViewById(R.id.checkBoxConsumidor);
        buttonConfigurarPerfil = findViewById(R.id.bt_ActivarOpcionsPerfil);
        TextView txtTipusBotiga = findViewById(R.id.txtTipusBotiga);
        TextView txtDefinicioBotiga = findViewById(R.id.txtDefinicioBotiga);
        EditText editDefinicioBotiga = findViewById(R.id.txtEditNomBotiga);
        buttonActualitzar = findViewById(R.id.bt_actualitzar);


        txtTipusBotiga.setVisibility(View.GONE); // FER DESAPAREIXER
        txtTipusBotiga.setVisibility(View.GONE);
        checkBoxBotiguer.setVisibility(View.GONE);
        checkBoxConsumidor.setVisibility(View.GONE);
        txtTipusBotiga.setVisibility(View.GONE); //FER TRANSPARENT INCLICABLE
        txtDefinicioBotiga.setVisibility(View.GONE);
        editDefinicioBotiga.setVisibility(View.GONE);
        buttonActualitzar.setVisibility(View.GONE);



        editDefinicioBotiga.setEnabled(false);

        buttonActualitzar = findViewById(R.id.bt_actualitzar);
        //PREPAREM SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEditTipusBotiga);
        String tipusBotiga = "";
        //Creem l'adaptador que necessita el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        //Especifiquem la llista amb la seguent linea
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apliquem l'adaptador
        spinner.setAdapter(adapter);
        //INICIEM TOT EN DISABLED
        checkBoxBotiguer.setEnabled(false);
        checkBoxConsumidor.setEnabled(false);
        txtTipusBotiga.setEnabled(false); //FER TRANSPARENT INCLICABLE
        txtDefinicioBotiga.setEnabled(false);
        spinner.setEnabled(false);
        editDefinicioBotiga.setEnabled(false);
        spinner.setVisibility(View.GONE);

        //Seguents 3 linies pel toast
        Context context = getApplicationContext();
        CharSequence text = "EPS! NOMÉS POTS TENIR UNA CONTA PER ROL";
        int duration = Toast.LENGTH_SHORT;

        checkBoxBotiguer.setOnClickListener(v -> {
            if(checkBoxConsumidor.isChecked()){
                checkBoxBotiguer.setChecked(false);
                //toast informatiu
                Toast.makeText(context, text, duration).show();
            }else if(checkBoxBotiguer.isChecked()) {
                txtDefinicioBotiga.setVisibility(View.VISIBLE);
                txtTipusBotiga.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                editDefinicioBotiga.setVisibility(View.VISIBLE);
                buttonActualitzar.setVisibility(View.VISIBLE);
                txtDefinicioBotiga.setEnabled(true);
                txtTipusBotiga.setEnabled(true);
                spinner.setEnabled(true);
                editDefinicioBotiga.setEnabled(true);
                buttonActualitzar.setEnabled(true);

            }else{
                txtDefinicioBotiga.setVisibility(View.GONE);
                txtTipusBotiga.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                editDefinicioBotiga.setVisibility(View.GONE);
                buttonActualitzar.setVisibility(View.GONE);
            }
        });

        checkBoxConsumidor.setOnClickListener(v -> {
            //falta crear els botons i opcions dels clients
            if(checkBoxBotiguer.isChecked()){
                checkBoxConsumidor.setChecked(false);
                Toast.makeText(context, text, duration).show();
            }else{
                //BUIDEM LA POSSIBLE OPCIO DELS BOTIGUERS
                txtDefinicioBotiga.setVisibility(View.GONE);
                txtTipusBotiga.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                editDefinicioBotiga.setVisibility(View.GONE);
                buttonActualitzar.setVisibility(View.GONE);

            }
        });


    buttonConfigurarPerfil.setOnClickListener(v -> {
        //txtTipusBotiga.setVisibility(View.GONE); // FER DESAPAREIXER
        //txtTipusBotiga.setVisibility(View.VISIBLE); // FER APAREIXER
        checkBoxBotiguer.setVisibility(View.VISIBLE);
        checkBoxConsumidor.setVisibility(View.VISIBLE);
        checkBoxBotiguer.setEnabled(true);
        checkBoxConsumidor.setEnabled(true);
    });

    buttonActualitzar.setOnClickListener(v -> {
        //ACTUALITZAR BASE DE DADES
        if(checkBoxConsumidor.isChecked()){

        }else{ //Botiguer
            agafarTipusNegoci();
            String definicio = String.valueOf(editDefinicioBotiga.getText());
            System.out.println(definicio);
        }
    });
    }
    private void agafarTipusNegoci(){
        String text;
        System.out.println(text = ((Spinner) findViewById(R.id.spinnerEditTipusBotiga)).getSelectedItem().toString());
    }
}