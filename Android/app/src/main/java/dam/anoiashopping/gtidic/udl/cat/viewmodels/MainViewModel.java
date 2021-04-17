package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class MainViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "MainVM";

    public MutableLiveData <ResultImpl> getDeleteResponse () {
        return this.accountRepo.getmResponseDeleteToken();
    }

    public MainViewModel () {
        this.accountRepo = new AccountRepo ();
    }

    public void delete_token (String s) {

        Token token = new Token();
        token.setToken(s);

        Log.d(TAG, "Eliminant token: " + s);

        this.accountRepo.deleteTokenUser(s, token);

    }
}
