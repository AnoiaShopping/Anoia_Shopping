package dam.anoiashopping.gtidic.udl.cat.models;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Business {

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
  
    public Business(String nom, String tipus, String definicio, String facebook, String instagram, String twitter) {
        
        // TODO : afegir foto

        this.nom = nom;
        this.tipus = tipus;
        this.definicio = definicio;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;

    }

    public Business() {
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
  
    @NotNull
    @Override
    public String toString(){
        return "name:"+nom+" "+" definicio:"+definicio;
    }
}
