package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class MenuViewModel extends ViewModel {
    private AccountRepo accountRepo;
    private final String TAG = "MainVM";

    public MenuViewModel () {
        this.accountRepo = new AccountRepo ();
    }

    public void delete_token (String token) {
        Token tokenBody = new Token();
        tokenBody.setToken(token);
        Log.d(TAG, "Eliminant token: " + token);
        this.accountRepo.deleteTokenUser(token, tokenBody);
    }

    public MutableLiveData<ResultImpl> getDeleteResponse () {
        return this.accountRepo.getmResponseDeleteToken();
    }

    public void getAccount (String token) {
        accountRepo.getAccount(token);
    }

    public MutableLiveData <Account> getAccountResponse () {
        return accountRepo.getmResponseGetAccount();
    }

    public MutableLiveData<ResultImpl> getAccountImageResponse () {
        return accountRepo.getmResponseUploadImage();
    }

    public void uploadAccountImage(File imageFile) {
        Log.d("VM", "uploading image... using repo");
        this.accountRepo.uploadImage(PreferencesProvider.providePreferences().getString("token", ""), imageFile);
    }

}
