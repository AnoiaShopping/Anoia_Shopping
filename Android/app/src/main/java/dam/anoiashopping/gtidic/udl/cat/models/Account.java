package dam.anoiashopping.gtidic.udl.cat.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import dam.anoiashopping.gtidic.udl.cat.utils.Utils;

public class Account {

    @SerializedName("username")
    private String username;
    @SerializedName("name")
    private String firstname;
    @SerializedName("surname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public Account() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setPassword(String password) {
        this.password = Utils.encode(password, "16", 29000);
    }

}