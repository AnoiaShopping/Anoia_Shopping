package dam.anoiashopping.gtidic.udl.cat.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Business;

public class BusinessCommonHolder {

    private static final String TAG = "EventCommonHolder";
    private TextView businessName;
    private TextView businessDescription;
    private ImageView businessPhoto;

    public BusinessCommonHolder(@NonNull View itemView) {

        businessName = itemView.findViewById(R.id.nom_empresa);
        businessDescription = itemView.findViewById(R.id.description);
        businessPhoto = itemView.findViewById(R.id.im_empresa);

        //businessName.setText("sdsfsdfsfd");
    }

    public void bindHolder(Business b) {


        this.businessName.setText(b.getNom());
        this.businessDescription.setText(b.getDefinicio());
        Picasso.get().load(b.getPhotoURL()).into(this.businessPhoto);
        //Log.d(TAG, "bindHolder() -> Busssines: " + b);

        //Log.d(TAG, "onBindViewHolder() -> cEvent: " + e.getPoster_url());
        //Picasso.get().load(e.getPoster_url()).into(this.eventPoster);
    }

}
