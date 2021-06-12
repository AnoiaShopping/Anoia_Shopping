package dam.anoiashopping.gtidic.udl.cat.views.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MenuViewModel;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private final String TAG = "MenuActivity";

    MenuViewModel menuViewModel;

    PermissionManager permissionManager;
    private final int REQUEST_EXTERNAL_STORAGE = 13;
    private final int PICK_IMAGE_REQUEST = 14;
    Button imAccountUpdate;
    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
        initToolbar();
        //initAccount();
    }

    public void init () {

        menuViewModel = new MenuViewModel ();

        permissionManager = new PermissionManager();
    }

    public void initToolbar () {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_chats,   R.id.nav_business,
                R.id.nav_cart, R.id.nav_profile, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void checkExternalStoragePermission(){
        permissionManager.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionManager.PermissionAskListener(){

            @Override
            public void onNeedPermission() {
                ActivityCompat.requestPermissions(MenuActivity.this,
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }

            @Override
            public void onPermissionPreviouslyDenied() {
                Log.d(TAG,"Permission denied");

                ActivityCompat.requestPermissions(MenuActivity.this,
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
            //File image = new File(getRealPathFromURI(path, this));
            //profileImage.setImageURI(path); // TODO adaptar a variable
            //configurationViewModel.uploadAccountImage(image);
            imageFile = new File(getRealPathFromURI(path, this));
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