package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.CreateBusinessViewModel;

public class CreateBusinessFragment extends Fragment {

    private View root;
    private final String TAG = "CreateBusinessFragment";

    private EditText txtEditNom;
    private EditText txtEditWeb;
    private EditText txtEditDefinicio;
    private EditText txtEditInstagram;
    private EditText txtEditFacebook;
    private EditText txtEditTwitter;
    private Button btCrearBotiga;
    Spinner spinner;
    private CreateBusinessViewModel createBusinessViewModel;

    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create_business, container, false);

        initView();

        return root;
    }

    private void initView () {
        createBusinessViewModel = new CreateBusinessViewModel();

        txtEditNom = root.findViewById(R.id.txtEditNomBotiga);
        txtEditWeb = root.findViewById(R.id.txtEditWebNegoci);
        txtEditDefinicio = root.findViewById(R.id.txtEditDefinicioBotiga);
        txtEditInstagram = root.findViewById(R.id.txtEditInstagram);
        txtEditFacebook  = root.findViewById(R.id.txtEditFacebook);
        txtEditTwitter = root.findViewById(R.id.txtEditTwitter);
        btCrearBotiga = root.findViewById(R.id.btCrearBotiga);

        spinner = (Spinner) root.findViewById(R.id.spinnerEditTipusBotiga);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.bens, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btCrearBotiga.setOnClickListener(v -> {
            if(validate()){
                register();
            }
        });

        createBusinessViewModel.createBusinessResponse().observe(getViewLifecycleOwner(), business1 -> {
            if (business1.isValid()) {
                Log.d (TAG, txtEditNom.getText().toString());
                Bundle b = new Bundle();
                b.putString("nom", txtEditNom.getText().toString());
                NavHostFragment.findNavController(CreateBusinessFragment.this).navigate(R.id.action_createBusinessFragment_to_updateImageFragment, b);
            }
        });

        createBusinessViewModel.uploadPhotoBusinessResponse().observe(getViewLifecycleOwner(), response -> {
            if (response.isValid()) {
                Toast.makeText(getActivity(), "Negoci registrat correctament", Toast.LENGTH_SHORT).show();
                //startActivity (new Intent(CreateBusinessActivity.this, MainActivity.class));
            }
        });
    }

    private boolean validate () {

        boolean validadorRegex = true;

        String provadorRegex = String.valueOf(txtEditNom.getText());
        if (!provadorRegex.matches("^{2,20}(.|\\s)*[a-zA-Z]+(.|\\s)*$")){

            txtEditNom.setError("Nom no vàlid, ha d'incoure lletres nomes i un tamany de màxim 25 caràcters i mínim 2");
            validadorRegex = false;

        }

        provadorRegex = String.valueOf(txtEditDefinicio.getText()); //qualsevol lletra, espais i enters permesos.
        if (!provadorRegex.matches("^(.|\\s)*[a-zA-Z]+(.|\\s)*$")){

            txtEditDefinicio.setError("Definició no valida. Ha d'incloure caracters");
            validadorRegex = false;

        }

        provadorRegex = String.valueOf(txtEditWeb.getText()); //Inici amb http o https
        if (!provadorRegex.matches("^https?:\\/\\/(.*)")){
            if(!provadorRegex.isEmpty()){
                validadorRegex = false;
                txtEditWeb.setError("Si no teniu web deixeu buit el camp, si en teniu, utilitza el format https://nomweb");
            }
        }

        provadorRegex = String.valueOf(txtEditInstagram.getText());  //https://www.instagram.com/username
        if (!provadorRegex.matches("^{2,25}[a-zA-Z._-]+\\.?")){
            if(!provadorRegex.isEmpty()){
                validadorRegex = false;
                txtEditInstagram.setError("Instagram incorrecte, has d'incloure només el nom d'usuari (lletres i guions/barres baixes");
            }
        }

        provadorRegex = String.valueOf(txtEditTwitter.getText()); // http://twitter.com/username
        if (!provadorRegex.matches("^{2,25}[a-zA-Z._-]+\\.?")){
            if(!provadorRegex.isEmpty()){
                validadorRegex = false;
                txtEditTwitter.setError("Twitter incorrecte, has d'incloure només el nom (lletres,guions i barres baixes");
            }
        }

        provadorRegex = String.valueOf(txtEditFacebook.getText());  //http://www.facebook.com/someusername
        if (!provadorRegex.matches("^{2,30}(.|\\s)*[a-zA-Z]+(.|\\s)*$")){
            if(!provadorRegex.isEmpty()){
                validadorRegex = false;
                txtEditFacebook.setError("Facebook incorrecte, has d'incloure el nom d'usuari incluint nomes lletres");                }
        }

        if (getBusinessType().equals("Selecciona el teu tipus de negoci …")) {
            Toast.makeText(getActivity(), "Has de seleccionar el tipus de negoci.", Toast.LENGTH_SHORT).show();
            validadorRegex = false;
        }

        return validadorRegex;
    }

    private void register () {
        Business business = new Business();
        business.setNom(txtEditNom.getText().toString());
        business.setDefinicio(txtEditDefinicio.getText().toString());
        business.setTipus(getBusinessType());

        String facebook = "";
        if (!txtEditFacebook.getText().toString().isEmpty()) {
            facebook = "https://www.facebook.com/" + txtEditFacebook.getText().toString();
        }
        business.setFacebook(facebook);

        String instagram = "";
        if (!txtEditInstagram.getText().toString().isEmpty()) {
            instagram = "https://www.instagram.com/" + txtEditInstagram.getText().toString();
        }
        business.setInstagram(instagram);

        String twitter = "";
        if (!txtEditTwitter.getText().toString().isEmpty()) {
            twitter = "https://www.twitter.com/" + txtEditTwitter.getText().toString();
        }
        business.setTwitter(twitter);

        String web = "";
        if (!txtEditWeb.getText().toString().isEmpty()) {
            web = txtEditWeb.getText().toString();
        }
        business.setWeb(web);

        createBusinessViewModel.createBusiness(business);
    }

    private String getBusinessType(){
        String text;
        text = ((Spinner) root.findViewById(R.id.spinnerEditTipusBotiga)).getSelectedItem().toString();
        return text;
    }
}