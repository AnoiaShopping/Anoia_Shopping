package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;

public class MainViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "MainVM";

    public MutableLiveData<Boolean> getDeleteResponse () {
        return this.accountRepo.getCorrectDeletedToken();
    }

    public MainViewModel () {
        this.accountRepo = new AccountRepo ();
    }

    public void delete_token (String s) {

        Token token = new Token();
        token.setToken(s);

        Log.d(TAG, s);

        this.accountRepo.deleteTokenUser(s, token);

    }
}
