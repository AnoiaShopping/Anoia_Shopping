package dam.anoiashopping.gtidic.udl.cat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Business implements Parcelable {

    @SerializedName("name")
    String nom;
    @SerializedName("type")
    String tipus;
    @SerializedName("definition")
    String definicio;
    @SerializedName("facebook")
    String facebook;
    @SerializedName("instagram")
    String instagram;
    @SerializedName("twitter")
    String twitter;
    @SerializedName("web")
    String web;
    @SerializedName("photo")
    String photoURL;
    @SerializedName("id")
    String id;
  
    public Business(String id, String nom, String tipus, String definicio, String facebook, String instagram, String twitter, String web, String photoURL) {
        
        // TODO : afegir foto
        this.id = id;
        this.nom = nom;
        this.tipus = tipus;
        this.definicio = definicio;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;
        this.web = web;
        this.photoURL = photoURL;

    }

    public Business() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getDefinicio() {
        return definicio;
    }

    public void setDefinicio(String definicio) {
        this.definicio = definicio;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @NotNull
    @Override
    public String toString(){
        return "name:"+nom+" "+" definicio:"+definicio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.write
        dest.writeString(nom);
        dest.writeString(tipus);
        dest.writeString(definicio);
        dest.writeString(facebook);
        dest.writeString(instagram);
        dest.writeString(twitter);
        dest.writeString(id);
    }

    protected Business(Parcel in) {
        nom = in.readString();
        tipus = in.readString();
        definicio = in.readString();
        facebook = in.readString();
        instagram = in.readString();
        twitter = in.readString();
        id = in.readString();
    }

    public static final Creator<Business> CREATOR = new Creator<Business>() {
        @Override
        public Business createFromParcel(Parcel in) {
            return new Business(in);
        }

        @Override
        public Business[] newArray(int size) {
            return new Business[size];
        }
    };
}
