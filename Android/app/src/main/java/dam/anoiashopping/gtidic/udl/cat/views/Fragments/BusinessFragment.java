package dam.anoiashopping.gtidic.udl.cat.views.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.adapters.RvProductsAdapter;
import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.models.Products;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;


public class BusinessFragment extends Fragment {

    private View root;
    private final String TAG = "BusinessFragment";

    private ImageView imageBusiness;
    private TextView txtNomBusiness;
    private TextView txtDefinitionBusiness;
    private ImageView imInstagram;
    private TextView txtTipusBusiness;
    RecyclerView recyclerView;
    private BusinessRepo businessRepo;
    private ImageView businessphoto;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_business, container, false);

        businessRepo = new BusinessRepo();

        recyclerView = root.findViewById(R.id.rv_products);
        imageBusiness = root.findViewById(R.id.im_botiga);
        txtNomBusiness = root.findViewById(R.id.nom_empresa1);
        txtDefinitionBusiness = root.findViewById(R.id.txtDefinicio);
        imInstagram = root.findViewById(R.id.imInstagram);
        txtTipusBusiness = root.findViewById(R.id.txtTipusBusiness);

        //Business business = getIntent().getExtras().getParcelable("business");

        final Bundle b = getArguments();
        Business business = new Business();
        business = b.getParcelable("business");

        Log.d(TAG, business.getNom());

        //businessRepo.getProductList(Integer.parseInt(business.getId()));

        /*businessRepo.getmResponseProductList().observe(getViewLifecycleOwner(), products -> {

            //recyclerView.setAdapter(new RvProductsAdapter(products));
            List<String> listItems = new ArrayList<>();

            for(Products p: products){

                System.out.println("Name: " + p.getNom());
                System.out.println("URL: " + p.getPhotoURL());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.setHasFixedSize(true);

                listItems.add(p.getNom());

                recyclerView.setAdapter(new RvProductsAdapter(listItems));
            }


        });*/

        //txtNomBusiness.setText(business.getNom());
        //txtDefinitionBusiness.setText(business.getDefinicio());
        //txtTipusBusiness.setText(business.getTipus());

        //Picasso.get().load(business.getPhotoURL()).into(this.imageBusiness);
        //Log.d (TAG, business.getPhotoURL());

        /*if (business.getPhotoURL() != null) {
            Picasso.get().load(business.getPhotoURL()).into(this.imageBusiness);
        } else {
            imageBusiness.setImageResource(R.drawable.smallbusiness500);
        }*/

        //Business finalBusiness = business;
        //imInstagram.setOnClickListener(v -> {
        //    if(!finalBusiness.getInstagram().matches("")){
        //        Intent intent = new Intent(Intent.ACTION_VIEW);
        //        intent.setData(Uri.parse(finalBusiness.getInstagram()));
        //        startActivity(intent);
        //    }
        //});

        //RECYCLERVIEW
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        //recyclerView.setLayoutManager(linearLayoutManager);

        //recyclerView.setHasFixedSize(true);

        //List<String> listItems = new ArrayList<>();
        //listItems.add("Item8");
        //listItems.add(business.getNom());

        //recyclerView.setAdapter(new RvProductsAdapter(listItems));

        // Per el que reb el business
        //Bundle b = getArguments();
        //if (b != null){
        //    Business business = b.getParcelable("business");
        //}

        return root;
    }

}