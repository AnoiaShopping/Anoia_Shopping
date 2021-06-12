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
import dam.anoiashopping.gtidic.udl.cat.databinding.FragmentConfigurationBinding;
import dam.anoiashopping.gtidic.udl.cat.manager.PermissionManager;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MainViewModel;
import dam.anoiashopping.gtidic.udl.cat.views.Activities.MenuActivity;

public class ConfigurationFragment extends Fragment {

    private View root;
    private final String TAG = "MainFragment";


    private Button updateImageButton;
    //private ImageView profileImage;

    ConfigurationViewModel configurationViewModel;
    private Button botoActualitzarCompte;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_configuration, container, false);

        configurationViewModel = new ViewModelProvider(this).get (ConfigurationViewModel.class);
        FragmentConfigurationBinding fragmentConfigurationBinding = DataBindingUtil.inflate (inflater, R.layout.fragment_configuration, container, false);
        fragmentConfigurationBinding.setLifecycleOwner (this);
        fragmentConfigurationBinding.setViewModel (configurationViewModel);

        initView();

        botoActualitzarCompte = root.findViewById(R.id.bt_actualitzarcompte);
        botoActualitzarCompte.setEnabled(false);
        //botoActualitzarCompte.setBackgroundColor(getActivity().getColor(android.R.color.darker_gray));
        //botoActualitzarCompte.setTextColor(getColor(R.color.white));

        return fragmentConfigurationBinding.getRoot();
    }

    private void initView () {

        configurationViewModel.getAccount();

        //profileImage = root.findViewById (R.id.im_profile);
        updateImageButton = root.findViewById (R.id.b_update);

        configurationViewModel.getAccountResponse().observe(getViewLifecycleOwner(), accountResponse -> {

            configurationViewModel.accountMutableLiveData.setValue(accountResponse);

            Log.d(TAG, configurationViewModel.accountMutableLiveData.getValue().getEmail());

            //if (configurationViewModel.accountMutableLiveData.getValue().getPhotoURL() != null) {
            //    Picasso.get().load(configurationViewModel.accountMutableLiveData.getValue().getPhotoURL()).into(this.profileImage);
            //} else {
            //    profileImage.setImageResource(R.drawable.user500);
            //}
        });

        updateImageButton.setOnClickListener(v -> {
            // MenuActivity.checkExternalStoragePermission();
        });

        configurationViewModel.getAccountImageResponse().observe(getViewLifecycleOwner(), response -> {
            if (response.isValid()) {
                Toast.makeText(getActivity(), "La foto ha estat actualitzada correctament.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Ha hagut un error al canviar la foto.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}