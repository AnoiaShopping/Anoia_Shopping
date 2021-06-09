package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.adapters.BusinessAdapter;
import dam.anoiashopping.gtidic.udl.cat.adapters.BusinessDiffCallback;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.MainViewModel;

public class MainFragment extends Fragment {

    private View root;
    private MainViewModel mainViewModel;
    private final String TAG = "MainFragment";

    private RecyclerView recyclerView;
    BusinessAdapter businessAdapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        return root;
    }

    public void initView () {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        recyclerView = root.findViewById(R.id.eventslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        businessAdapter = new BusinessAdapter(new BusinessDiffCallback());
        recyclerView.setAdapter (businessAdapter);

        mainViewModel.getBusiness();

        mainViewModel.returnBusiness().observe(getViewLifecycleOwner(), business -> {
            businessAdapter.submitList(business);
        });

        businessAdapter.businessListener(business -> {
            // Fer nova activitat per veure info
            //Intent intent = new Intent(MainActivity.this, BusinessActivity.class);
            //intent.putExtra("business", business);
            Bundle b = new Bundle();
            b.putParcelable("business", business);
            //startActivity(intent);
        });
    }
}