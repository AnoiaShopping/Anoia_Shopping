package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dam.anoiashopping.gtidic.udl.cat.R;

public class BusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
    }

    // Per el que reb el business
    //Bundle b = getArguments();
    //if (b != null){
    //    Business business = b.getParcelable("business");
    //}
}