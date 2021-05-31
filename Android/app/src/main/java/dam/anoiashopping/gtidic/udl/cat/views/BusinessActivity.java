package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Business;

public class BusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        Business business = getIntent().getExtras().getParcelable("business");

        String nom = business.getNom();
        Toast.makeText(getApplicationContext(), nom, Toast.LENGTH_SHORT).show();

    }

    // Per el que reb el business
    //Bundle b = getArguments();
    //if (b != null){
    //    Business business = b.getParcelable("business");
    //}
}