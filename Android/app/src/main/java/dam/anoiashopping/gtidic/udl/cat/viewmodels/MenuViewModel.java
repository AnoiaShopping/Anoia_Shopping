package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;

public class MenuViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private BusinessRepo businessRepo;
    private final String TAG = "MenuVM";

    public MenuViewModel () {
        accountRepo = new AccountRepo();
        businessRepo = new BusinessRepo();
    }

    public void uploadAccountImage(File imageFile){
        Log.d("VM", "uploading image...");
        this.accountRepo.uploadImage(PreferencesProvider.providePreferences().getString("token", ""), imageFile);
    }
}
