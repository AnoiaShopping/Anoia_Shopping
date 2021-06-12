package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.adapters.BusinessAdapter;
import dam.anoiashopping.gtidic.udl.cat.adapters.BusinessDiffCallback;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.BusinessListViewModel;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MainViewModel;

public class BusinessListFragment extends Fragment {

    private final String TAG = "MainFragment";
    private View root;
    private BusinessListViewModel businessListViewModel;
    private RecyclerView recyclerView;
    BusinessAdapter businessAdapter;
    FloatingActionButton floatingActionButton;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_business_list, container, false);

        floatingActionButton = root.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(BusinessListFragment.this)
                    .navigate(R.id.action_nav_business_to_createBusinessFragment);
        });

        initView();
        return root;
    }

    public void initView () {
        businessListViewModel = new ViewModelProvider(this).get(BusinessListViewModel.class);

        recyclerView = root.findViewById(R.id.ownBusinessList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        businessAdapter = new BusinessAdapter(new BusinessDiffCallback());
        recyclerView.setAdapter (businessAdapter);

        businessListViewModel.getOwnBusiness();

        businessListViewModel.returnBusiness().observe(getViewLifecycleOwner(), business -> {
            businessAdapter.submitList(business);
        });

        businessAdapter.businessListener(business -> {

            Bundle b = new Bundle();
            b.putParcelable("business", business);
            Log.d(TAG, business.getNom());

            //NavHostFragment.findNavController(BusinessListFragment.this).navigate(R.id.action_nav_home_to_businessFragment);
        });
    }
}