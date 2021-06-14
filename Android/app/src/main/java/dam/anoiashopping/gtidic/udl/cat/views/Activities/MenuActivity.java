package dam.anoiashopping.gtidic.udl.cat.views.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.NavHeaderBinding;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MenuViewModel;
import dam.anoiashopping.gtidic.udl.cat.views.Fragments.ConfigurationFragment;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private final String TAG = "MenuActivity";

    MenuViewModel menuViewModel;
    NavigationView navigationView;

    PermissionManager permissionManager;
    private final int REQUEST_EXTERNAL_STORAGE = 13;
    private final int PICK_IMAGE_REQUEST = 14;

    NavController navController;
    DrawerLayout drawer;

    MutableLiveData <File> imageFile = new MutableLiveData<>();
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
    }

    public void init () {
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        permissionManager = new PermissionManager();

        navigationView = findViewById(R.id.nav_view);
        NavHeaderBinding navHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header, navigationView, true);
        navHeaderBinding.setLifecycleOwner(this);

        menuViewModel.getDeleteResponse().observe(this, deleteResponse -> {
            if (deleteResponse.isValid()) {
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            }
        });


        imageView = navHeaderBinding.getRoot().findViewById(R.id.fotomenu);
        menuViewModel.getAccount(PreferencesProvider.providePreferences().getString("token", ""));
        menuViewModel.getAccountResponse().observe(this, account -> {
            navHeaderBinding.setAccount(account);
            Log.d (TAG, account.getEmail());
            initToolbar();

            if (account.getPhotoURL() != null) {
                Picasso.get().load(account.getPhotoURL()).into(this.imageView);
            }
        });
    }

    public void initToolbar () {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_chats,   R.id.nav_business,
                R.id.nav_cart, R.id.nav_profile, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                navController.navigate(R.id.nav_home);
                drawer.closeDrawers();
                break;
            case R.id.nav_chats:
                navController.navigate(R.id.nav_chats);
                drawer.closeDrawers();
                break;
            case R.id.nav_business:
                navController.navigate(R.id.nav_business);
                drawer.closeDrawers();
                break;
            case R.id.nav_profile:
                navController.navigate(R.id.nav_profile);
                drawer.closeDrawers();
                break;
            case R.id.nav_logout:
                menuViewModel.delete_token(PreferencesProvider.providePreferences().getString("token", ""));
                break;
            default:
                break;
        }
        return false;
    }

    public void userUpdate () {
        checkExternalStoragePermission();

        imageFile.observe(this, file -> {
            menuViewModel.uploadAccountImage(file);
        });

        menuViewModel.getAccountImageResponse().observe(this, response -> {
            if (response.isValid()){
                Toast.makeText(this, "Foto pujada Correctament", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void businessCreate (String name) {
        checkExternalStoragePermission();

        imageFile.observe(this, file -> {
            menuViewModel.uploadBusinessImage(file, name);
        });

        menuViewModel.getBusinessImageResponse().observe(this, response -> {
            if (response.isValid()){
                Toast.makeText(this, "Foto pujada Correctament, ja tens el teu negoci creat correctament.", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_business);
            }
        });
    }

    public void businessUpdate (String name) {
        checkExternalStoragePermission();

        imageFile.observe(this, file -> {
            menuViewModel.uploadBusinessImage(file, name);
        });

        menuViewModel.getBusinessImageResponse().observe(this, response -> {
            if (response.isValid()){
                Toast.makeText(this, "Foto pujada Correctament.", Toast.LENGTH_SHORT).show();
            }
        });
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
            imageFile.setValue(new File(getRealPathFromURI(path, this)));
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