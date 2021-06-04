package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.adapters.RvProductsAdapter;
import dam.anoiashopping.gtidic.udl.cat.models.Business;

public class BusinessActivity extends AppCompatActivity {
    private ImageView imageBusiness;
    private TextView txtNomBusiness;
    private TextView txtDefinitionBusiness;
    private ImageView imInstagram;
    private TextView txtTipusBusiness;
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        recyclerView = findViewById(R.id.rv_products);
        imageBusiness = findViewById(R.id.im_botiga);
        txtNomBusiness = findViewById(R.id.nom_empresa1);
        txtDefinitionBusiness = findViewById(R.id.txtDefinicio);
        imInstagram = findViewById(R.id.imInstagram);
        txtTipusBusiness = findViewById(R.id.txtTipusBusiness);

        Business business = getIntent().getExtras().getParcelable("business");

        txtNomBusiness.setText(business.getNom());
        txtDefinitionBusiness.setText(business.getDefinicio());
        txtTipusBusiness.setText(business.getTipus());

        if (business.getPhotoURL() != null) {
            Picasso.get().load(business.getPhotoURL()).into(this.imageBusiness);
        } else {
            imageBusiness.setImageResource(R.drawable.user500);
        }


        imInstagram.setOnClickListener(v -> {
            if(business.getInstagram().matches("")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(business.getInstagram()));
                startActivity(intent);
            }else{
                Intent intent = new Intent(BusinessActivity.this, BusinessActivity.class);
            }
        });


    //RECYCLERVIEW
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        List<String> listItems = new ArrayList<>();
        listItems.add("Item1");
        listItems.add("Item2");
        listItems.add("Item3");
        listItems.add("Item4");
        listItems.add("Item5");
        listItems.add("Item6");
        listItems.add("Item7");
        listItems.add("Item8");
        listItems.add(business.getNom());

        recyclerView.setAdapter(new RvProductsAdapter(listItems));

    }

    // Per el que reb el business
    //Bundle b = getArguments();
    //if (b != null){
    //    Business business = b.getParcelable("business");
    //}
}