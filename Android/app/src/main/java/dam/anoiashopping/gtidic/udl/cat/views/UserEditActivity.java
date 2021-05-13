package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        EditText editDefinicioBotiga = findViewById(R.id.editDefinicioBotiga);
        buttonActualitzar = findViewById(R.id.bt_actualitzar);
        //INICIEM TOT EN DISABLED
        ////////////////////////////////////////////////////////////////////////////
        Spinner spinner = (Spinner) findViewById(R.id.editSpinnerBotigues);
        String tipusBotiga = "";
        //Creem l'adaptador que necessita el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        //Especifiquem la llista amb la seguent linea
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apliquem l'adaptador
        spinner.setAdapter(adapter);
        checkBoxBotiguer.setEnabled(false);
        checkBoxConsumidor.setEnabled(false);
        txtTipusBotiga.setEnabled(false); //FER TRANSPARENT INCLICABLE
        txtDefinicioBotiga.setEnabled(false);
        spinner.setEnabled(false);
        editDefinicioBotiga.setEnabled(false);

        //Seguents 3 linies pel toast
        Context context = getApplicationContext();
        CharSequence text = "EPS! Només pots tenir una conta per rol.";
        int duration = Toast.LENGTH_SHORT;

        checkBoxBotiguer.setOnClickListener(v -> {
            if(checkBoxConsumidor.isChecked()){
                checkBoxBotiguer.setChecked(false);
                //toast informatiu
                Toast.makeText(context, text, duration).show();
            }else{
                txtDefinicioBotiga.setEnabled(true);
                txtTipusBotiga.setEnabled(true);
                spinner.setEnabled(true);
                editDefinicioBotiga.setEnabled(true);
            }
        });

        checkBoxConsumidor.setOnClickListener(v -> {
            //falta crear els botons i opcions dels clients
            if(checkBoxBotiguer.isChecked()){
                checkBoxConsumidor.setChecked(false);
                Toast.makeText(context, text, duration).show();
            }else{

            }
        });


    buttonConfigurarPerfil.setOnClickListener(v -> {
        //txtTipusBotiga.setVisibility(View.GONE); // FER DESAPAREIXER
        //txtTipusBotiga.setVisibility(View.VISIBLE); // FER APAREIXER
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
        System.out.println(text = ((Spinner) findViewById(R.id.editSpinnerBotigues)).getSelectedItem().toString());
    }
}