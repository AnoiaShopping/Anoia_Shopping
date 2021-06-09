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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MainViewModel;
import dam.anoiashopping.gtidic.udl.cat.views.Activities.MenuActivity;

public class ConfigurationFragment extends Fragment {

    private View root;
    private final String TAG = "MainFragment";

    PermissionManager permissionManager;
    private final int REQUEST_EXTERNAL_STORAGE = 13;
    private final int PICK_IMAGE_REQUEST = 14;
    private Button updateImageButton;
    private ImageView profileImage;

    ConfigurationViewModel configurationViewModel;
    private Button botoCrearConta;
    private Button botoActualitzarCompte;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_configuration, container, false);

        initView();

        botoActualitzarCompte = root.findViewById(R.id.bt_actualitzarcompte);
        botoActualitzarCompte.setEnabled(false);
        //botoActualitzarCompte.setBackgroundColor(getColor(android.R.color.darker_gray));
        //botoActualitzarCompte.setTextColor(getColor(R.color.white));

        return root;
    }

    private void initView () {

        permissionManager = new PermissionManager();
        configurationViewModel = new ViewModelProvider(this).get (ConfigurationViewModel.class);
        //ActivityConfigurationBinding activityConfigurationBinding = DataBindingUtil.setContentView (getActivity(), R.layout.fragment_configuration);
        //activityConfigurationBinding.setLifecycleOwner (this);
        //activityConfigurationBinding.setViewModel (configurationViewModel);

        configurationViewModel.getAccount();

        profileImage = root.findViewById (R.id.im_profile);
        updateImageButton = root.findViewById (R.id.b_update);

        configurationViewModel.getAccountResponse().observe(getViewLifecycleOwner(), accountResponse -> {
            if (accountResponse.isValid()) {
                configurationViewModel.setAccount();

                if (configurationViewModel.photoURL.getValue() != null) {
                    Picasso.get().load(configurationViewModel.photoURL.getValue()).into(this.profileImage);
                } else {
                    profileImage.setImageResource(R.drawable.user500);
                }
            }
        });

        updateImageButton.setOnClickListener(v -> {
            checkExternalStoragePermission();
        });

        configurationViewModel.getAccountImageResponse().observe(getViewLifecycleOwner(), response -> {
            if (response.isValid()) {
                Toast.makeText(getActivity(), "La foto ha estat actualitzada correctament.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Ha hagut un error al canviar la foto.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkExternalStoragePermission(){
        permissionManager.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionManager.PermissionAskListener(){

            @Override
            public void onNeedPermission() {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }

            @Override
            public void onPermissionPreviouslyDenied() {
                Log.d(TAG,"Permission denied");

                ActivityCompat.requestPermissions(getActivity(),
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

        if (resultCode == resultCode) {
            Uri path = data.getData();
            File image = new File(getRealPathFromURI(path, getActivity()));
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

}