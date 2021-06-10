package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dam.anoiashopping.gtidic.udl.cat.R;

public class BusinessListFragment extends Fragment {

    private View root;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_business_list, container, false);

        //NavHostFragment.findNavController(XXXXFragment.this) // TODO per anar a una altre fragment
        //        .navigate(R.id.X);

        floatingActionButton = root.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(BusinessListFragment.this)
                    .navigate(R.id.action_nav_business_to_createBusinessFragment);
        });

        return root;
    }
}