package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Business;

public class BusinessActivity extends AppCompatActivity {
    private ImageView imageBusiness;
    private TextView txtNomBusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        imageBusiness = findViewById(R.id.im_botiga);
        txtNomBusiness = findViewById(R.id.nom_empresa1);


        Business business = getIntent().getExtras().getParcelable("business");

        txtNomBusiness.setText(business.getNom());

        if (business.getPhotoURL() != null) {
            Picasso.get().load(business.getPhotoURL()).into(this.imageBusiness);
        } else {
            imageBusiness.setImageResource(R.drawable.user500);
        }


    }

    // Per el que reb el business
    //Bundle b = getArguments();
    //if (b != null){
    //    Business business = b.getParcelable("business");
    //}
}