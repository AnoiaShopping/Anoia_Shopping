package dam.anoiashopping.gtidic.udl.cat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Products implements Parcelable {

    @SerializedName("name")
    String nom;
    @SerializedName("definition")
    String definition;
    @SerializedName("photo")
    String photoURL;

    public Products(String nom, String definition, String photoURL){
        this.nom = nom;
        this.definition = definition;
        this.photoURL = photoURL;

    }

    public Products(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    protected Products(Parcel in) {
        nom = in.readString();
        definition = in.readString();
        photoURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(definition);
        dest.writeString(photoURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
