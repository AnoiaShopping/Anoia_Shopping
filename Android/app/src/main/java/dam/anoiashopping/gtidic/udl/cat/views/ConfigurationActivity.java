package dam.anoiashopping.gtidic.udl.cat.views;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.ActivityConfigurationBinding;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    // upload Image
    PermissionManager permissionManager;
    private final int REQUEST_EXTERNAL_STORAGE = 13;
    private final int PICK_IMAGE_REQUEST = 14;
    private Button updateImageButton;
    private ImageView profileImage;

    private final String TAG = "MainActivity";
    ConfigurationViewModel configurationViewModel;
    private Button botoCrearConta;
    private Button botoActualitzarCompte;
    //private EditText txtNomUser;
    //private EditText txtCognom;
    //private EditText txtNomReal;
    //private EditText txtCorreu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        initView();
        botoActualitzarCompte = findViewById(R.id.bt_actualitzarcompte);
        botoActualitzarCompte.setEnabled(false);

        botoCrearConta = findViewById(R.id.btAnarCrearBotiga);
        botoCrearConta.setOnClickListener(v -> {
            startActivity(new Intent(ConfigurationActivity.this, CreateBusinessActivity.class));
        });

    }

    private void initView () {

        permissionManager = new PermissionManager();
        configurationViewModel = new ViewModelProvider (this).get (ConfigurationViewModel.class);
        ActivityConfigurationBinding activityConfigurationBinding = DataBindingUtil.setContentView (this, R.layout.activity_configuration);
        activityConfigurationBinding.setLifecycleOwner (this);
        activityConfigurationBinding.setViewModel (configurationViewModel);

        configurationViewModel.getAccount();

        configurationViewModel.getAccountResponse().observe(this, accountResponse -> {
            if (accountResponse.isValid()) {
                configurationViewModel.setAccount();
                Picasso.get().load(configurationViewModel.photoURL.getValue()).into(this.profileImage);
            }
        });

        profileImage = findViewById (R.id.im_profile);
        updateImageButton = findViewById (R.id.b_update);

        updateImageButton.setOnClickListener(v -> {
            checkExternalStoragePermission();
        });
    }

    public void checkExternalStoragePermission(){
        permissionManager.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionManager.PermissionAskListener(){

            @Override
            public void onNeedPermission() {
                ActivityCompat.requestPermissions(ConfigurationActivity.this,
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }

            @Override
            public void onPermissionPreviouslyDenied() {
                        Log.d(TAG,"Permission denied");
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
            profileImage.setImageURI(path);
            configurationViewModel.uploadAccountImage(image);
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

    /*public void txtChanger(EditText edit){
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                botoActualitzarCompte.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/
}