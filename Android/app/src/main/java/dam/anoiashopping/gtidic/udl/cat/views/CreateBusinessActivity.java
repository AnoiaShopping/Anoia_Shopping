package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.BusinessViewModel;

public class CreateBusinessActivity extends AppCompatActivity {

    private EditText txtEditNom;
    private EditText txtEditWeb;
    private EditText txtEditDefinicio;
    private EditText txtEditInstagram;
    private EditText txtEditFacebook;
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
        txtEditFacebook  = findViewById(R.id.txtEditFacebook);
        txtEditTwitter = findViewById(R.id.txtEditTwitter);
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
            String provadorRegex = String.valueOf(txtEditNom.getText());

            int validadorRegex = 0;

            if (!provadorRegex.matches("^{2,20}(.|\\s)*[a-zA-Z]+(.|\\s)*$")){

                txtEditNom.setError("Nom no vàlid, ha d'incoure lletres nomes i un tamany de màxim 25 caràcters i mínim 2");

            }else{validadorRegex++;}

            provadorRegex = String.valueOf(txtEditDefinicio.getText()); //qualsevol lletra, espais i enters permesos.
            if (!provadorRegex.matches("^(.|\\s)*[a-zA-Z]+(.|\\s)*$")){

                txtEditDefinicio.setError("Definició no valida. Ha d'incloure caracters");

            }else{validadorRegex++;}

            provadorRegex = String.valueOf(txtEditWeb.getText()); //Inici amb http o https
            if (!provadorRegex.matches("^https?:\\/\\/(.*)")){
                if(provadorRegex.isEmpty()){
                    validadorRegex++;
                }else{
                    txtEditWeb.setError("Si no teniu web deixeu buit el camp, si en teniu, utilitza el format https://nomweb");
                }
            }else{validadorRegex++;}



            provadorRegex = String.valueOf(txtEditInstagram.getText());  //https://www.instagram.com/username
            if (!provadorRegex.matches("^{2,25}[a-zA-Z._-]+\\.?")){

                if(provadorRegex.isEmpty()){
                    validadorRegex++;
                }else{
                    txtEditInstagram.setError("Instagram incorrecte, has d'incloure només el nom d'usuari (lletres i guions/barres baixes");
                }
            }else{validadorRegex++;}

            provadorRegex = String.valueOf(txtEditTwitter.getText()); // http://twitter.com/username
            if (!provadorRegex.matches("^{2,25}[a-zA-Z._-]+\\.?")){

                if(provadorRegex.isEmpty()){
                    validadorRegex++;
                }else{
                    txtEditTwitter.setError("Twitter incorrecte, has d'incloure només el nom (lletres,guions i barres baixes");
                }
            }else{validadorRegex++;}

            provadorRegex = String.valueOf(txtEditFacebook.getText());  //http://www.facebook.com/someusername
            if (!provadorRegex.matches("^{2,30}(.|\\s)*[a-zA-Z]+(.|\\s)*$")){
                if(provadorRegex.isEmpty()){
                    validadorRegex++;
                }else{
                    txtEditFacebook.setError("Facebook incorrecte, has d'incloure el nom d'usuari incluint nomes lletres");                }
            }else{validadorRegex++;}



            if(validadorRegex ==6){
                Business business = new Business();
                business.setNom(txtEditNom.getText().toString());
                business.setDefinicio(txtEditDefinicio.getText().toString());
                business.setTipus(agafarTipusNegoci());

                String facebook = "http://www.facebook.com/" + txtEditFacebook.getText().toString();
                System.out.print(facebook);
                business.setFacebook(facebook);
                String instagram = "http://www.instagram.com/" + txtEditInstagram.getText().toString();
                System.out.print(instagram);
                business.setInstagram(txtEditInstagram.getText().toString());
                String twitter = "http://www.twitter.com/" + txtEditTwitter.getText().toString();
                System.out.print(twitter);
                business.setTwitter(txtEditTwitter.getText().toString());

                businessViewModel.createBusiness(business);

                Context context = getApplicationContext();
                CharSequence text = "BOTIGA REGISTRADA";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
            }
        });
    }

    private String agafarTipusNegoci(){
        String text;
        text = ((Spinner) findViewById(R.id.spinnerEditTipusBotiga)).getSelectedItem().toString();
        return text;
    }
}