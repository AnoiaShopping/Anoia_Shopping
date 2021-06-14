package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class ConfigurationViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "ConfigurationVM";

    public MutableLiveData <Account> accountMutableLiveData = new MutableLiveData<>();

    public MutableLiveData <Account> getAccountResponse () {
        return this.accountRepo.getmResponseGetAccount();
    }

    public MutableLiveData <ResultImpl> getUpdateAccountResponse() {
        return this.accountRepo.getmResponseUpdateAccount();
    }

    public ConfigurationViewModel () {
        this.accountRepo = new AccountRepo();
    }

    public void getAccount () {
        this.accountRepo.getAccount(PreferencesProvider.providePreferences().getString("token", ""));
    }

    public MutableLiveData<ResultImpl> getAccountImageResponse () {
        return accountRepo.getmResponseUploadImage();
    }

    public void uploadAccountImage(File imageFile) {
        Log.d("VM", "uploading image... using repo");
        this.accountRepo.uploadImage(PreferencesProvider.providePreferences().getString("token", ""), imageFile);
    }

    public void updateAccount () {
        this.accountRepo.updateAccount(PreferencesProvider.providePreferences().getString("token", ""), accountMutableLiveData.getValue());
    }
}
