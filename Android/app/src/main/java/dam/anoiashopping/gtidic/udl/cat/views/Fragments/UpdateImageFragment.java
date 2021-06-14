package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.views.Activities.MenuActivity;

public class UpdateImageFragment extends Fragment {

    private View root;
    private final String TAG = "BusinessFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_update_image, container, false);

        final Bundle b = getArguments();
        String nom = b.getString("nom");

        Button button = root.findViewById(R.id.b_chooseimage);

        button.setOnClickListener(v -> {
            ((MenuActivity)getActivity()).businessCreate(nom);
        });

        return root;
    }
}