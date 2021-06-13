package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ConfigurationViewModel;
import dam.anoiashopping.gtidic.udl.cat.views.Activities.MenuActivity;

public class ConfigurationFragment extends Fragment {

    private final String TAG = "MainFragment";

    FragmentConfigurationBinding fragmentConfigurationBinding;

    ConfigurationViewModel configurationViewModel;
    ImageView photoAccount;

    Button button;
    File imageFile;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        configurationViewModel = new ViewModelProvider(this).get(ConfigurationViewModel.class);
        fragmentConfigurationBinding = DataBindingUtil.inflate (inflater, R.layout.fragment_configuration, container, false);
        fragmentConfigurationBinding.setLifecycleOwner (this);
        fragmentConfigurationBinding.setViewModel (configurationViewModel);

        initView();

        return fragmentConfigurationBinding.getRoot();
    }

    private void initView () {
        configurationViewModel.getAccount();

        photoAccount = fragmentConfigurationBinding.getRoot().findViewById(R.id.im_profile);

        configurationViewModel.getAccountResponse().observe(getViewLifecycleOwner(), accountResponse -> {

            configurationViewModel.accountMutableLiveData.setValue(accountResponse);

            if (configurationViewModel.accountMutableLiveData.getValue().getPhotoURL() != null) {
                Picasso.get().load(configurationViewModel.accountMutableLiveData.getValue().getPhotoURL()).into(this.photoAccount);
            } else {
                photoAccount.setImageResource(R.drawable.user500);
            }
        });

        configurationViewModel.getAccountImageResponse().observe(getViewLifecycleOwner(), response -> {
            if (response.isValid()) {
                Toast.makeText(getActivity(), "La foto ha estat actualitzada correctament.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Ha hagut un error al canviar la foto.", Toast.LENGTH_SHORT).show();
            }
        });

        button = fragmentConfigurationBinding.getRoot().findViewById(R.id.b_updateImage);

        button.setOnClickListener(v -> {
            ((MenuActivity)getActivity()).userUpdate();
        });
    }
}