package dam.anoiashopping.gtidic.udl.cat.models;

import com.google.gson.annotations.SerializedName;

public class Business {

    @SerializedName("name")
    String nom;
    @SerializedName("type")
    String tipus;
    @SerializedName("definition")
    String definicio;

    // TODO : afegir foto

    public Business(String nom, String tipus, String definicio) {
        this.nom = nom;
        this.tipus = tipus;
        this.definicio = definicio;
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
}
