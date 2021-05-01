package dam.anoiashopping.gtidic.udl.cat.models;

import com.google.gson.annotations.SerializedName;

public class AccountShow {

    @SerializedName("username")
    private String username;
    @SerializedName("genere")
    private String genere;

    public AccountShow() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
