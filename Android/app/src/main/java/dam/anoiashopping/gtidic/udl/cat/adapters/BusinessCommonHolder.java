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
    }

    public void bindHolder(Business b) {
        this.businessName.setText(b.getNom());
        this.businessDescription.setText(b.getDefinicio());
        Picasso.get().load(b.getPhotoURL()).into(this.businessPhoto);
    }

}
