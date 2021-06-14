package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.os.Bundle;

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

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.databinding.FragmentEditBusinessBinding;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.BusinessViewModel;
import dam.anoiashopping.gtidic.udl.cat.views.Activities.MenuActivity;

public class EditBusinessFragment extends Fragment {

    private final String TAG = "MainFragment";

    BusinessViewModel businessViewModel;
    FragmentEditBusinessBinding fragmentEditBusinessBinding;

    ImageView foto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        businessViewModel = new ViewModelProvider(this).get(BusinessViewModel.class);
        fragmentEditBusinessBinding = DataBindingUtil.inflate (inflater, R.layout.fragment_edit_business, container, false);
        fragmentEditBusinessBinding.setLifecycleOwner (this);
        fragmentEditBusinessBinding.setViewModel(businessViewModel);

        final Bundle b = getArguments();
        businessViewModel.businessMutableLiveData.setValue(b.getParcelable("business"));

        foto = fragmentEditBusinessBinding.getRoot().findViewById(R.id.imatgenegoci);
        Picasso.get().load(businessViewModel.businessMutableLiveData.getValue().getPhotoURL()).into(this.foto);

        businessViewModel.getUpdateBusinessResponse().observe(getViewLifecycleOwner(), response -> {
            if (response.isValid()) {
                Toast.makeText(getActivity(), "El negoci ha estat actualitzat correctament.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Ha hagut un error al actualitzar les dades.", Toast.LENGTH_SHORT).show();
            }
        });

        Button button = fragmentEditBusinessBinding.getRoot().findViewById(R.id.canviarfoto);
        button.setOnClickListener(v -> {
            ((MenuActivity)getActivity()).businessUpdate(businessViewModel.businessMutableLiveData.getValue().getNom());
        });

        return fragmentEditBusinessBinding.getRoot();
    }
}