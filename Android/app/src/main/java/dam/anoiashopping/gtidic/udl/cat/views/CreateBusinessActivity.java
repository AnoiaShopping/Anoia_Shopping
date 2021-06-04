package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.CreateBusinessViewModel;

public class CreateBusinessActivity extends AppCompatActivity {

    private static final String TAG = "CreateBusinessActivity";

    PermissionManager permissionManager;
    private final int REQUEST_EXTERNAL_STORAGE = 13;
    private final int PICK_IMAGE_REQUEST = 14;
    ImageView photopreview;
    File photo;
    Button selectPhoto;

    private EditText txtEditNom;
    private EditText txtEditWeb;
    private EditText txtEditDefinicio;
    private EditText txtEditInstagram;
    private EditText txtEditFacebook;
    private EditText txtEditTwitter;
    private Button btCrearBotiga;
    Spinner spinner;
    private CreateBusinessViewModel createBusinessViewModel;

    @SuppressLint({"Range", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
        permissionManager = new PermissionManager();

        txtEditNom = findViewById(R.id.txtEditNomBotiga);
        txtEditWeb = findViewById(R.id.txtEditWebNegoci);
        txtEditDefinicio = findViewById(R.id.txtEditDefinicioBotiga);
        txtEditInstagram = findViewById(R.id.txtEditInstagram);
        txtEditFacebook  = findViewById(R.id.txtEditFacebook);
        txtEditTwitter = findViewById(R.id.txtEditTwitter);
        btCrearBotiga = findViewById(R.id.btCrearBotiga);
        photopreview = findViewById(R.id.im_businessphotopreview);
        selectPhoto = findViewById(R.id.b_chooseimage);

        createBusinessViewModel = new CreateBusinessViewModel();

        spinner();

        selectPhoto.setOnClickListener(v -> {
            checkExternalStoragePermission();
        });

        btCrearBotiga.setOnClickListener(v -> {
            if(validate()){
                register();
            }
        });

        createBusinessViewModel.createBusinessResponse().observe(this, business1 -> {
            if (business1.isValid()) {
                Log.d (TAG, txtEditNom.getText().toString());
                createBusinessViewModel.uploadBusinessPhoto(photo, txtEditNom.getText().toString());
            }
        });

        createBusinessViewModel.uploadPhotoBusinessResponse().observe(this, response -> {
            if (response.isValid()) {
                Toast.makeText(getApplicationContext(), "Negoci registrat correctament", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(CreateBusinessActivity.this, MainActivity.class));
            }
        });
    }

    private void spinner () {
        //PREPAREM SPINNER
        spinner = (Spinner) findViewById(R.id.spinnerEditTipusBotiga);
        //Creem l'adaptador que necessita el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipusDeBotigues, android.R.layout.simple_spinner_item);
        //Especifiquem la llista amb la seguent linea
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apliquem l'adaptador
        spinner.setAdapter(adapter);
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

        if (photo == null) {
            Toast.makeText(getApplicationContext(), "Has de seleccionar la foto del negoci.", Toast.LENGTH_SHORT).show();
            validadorRegex = false;
        }

        return validadorRegex;
    }

    private void register () {
        Business business = new Business();
        business.setNom(txtEditNom.getText().toString());
        business.setDefinicio(txtEditDefinicio.getText().toString());
        business.setTipus(agafarTipusNegoci());

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

    private String agafarTipusNegoci(){
        String text;
        text = ((Spinner) findViewById(R.id.spinnerEditTipusBotiga)).getSelectedItem().toString();
        return text;
    }

    public void checkExternalStoragePermission(){
        permissionManager.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionManager.PermissionAskListener(){

            @Override
            public void onNeedPermission() {
                ActivityCompat.requestPermissions(CreateBusinessActivity.this,
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }

            @Override
            public void onPermissionPreviouslyDenied() {
                Log.d(TAG,"Permission denied");

                ActivityCompat.requestPermissions(CreateBusinessActivity.this,
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }

            @Override
            public void onPermissionPreviouslyDeniedWithNeverAskAgain() {

                Log.d(TAG,"Permission denied never ask again");
            }

            @Override
            public void onPermissionGranted() {
                Log.d(TAG,"Permission granted");
                pick();
            }
        });

    }

    public void pick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Dialog result: " + resultCode);

        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            File image = new File(getRealPathFromURI(path, this));
            photopreview.setImageURI(path);
            photo = image;
        }
    }

    public String getRealPathFromURI(Uri uri, Activity activity) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressLint("Recycle") Cursor cursor = activity.getContentResolver().query(uri,
                projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
}