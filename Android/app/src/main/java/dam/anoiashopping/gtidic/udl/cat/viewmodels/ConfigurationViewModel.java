package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class ConfigurationViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "ConfigurationVM";

    public MutableLiveData <String> Username  = new MutableLiveData <> ();
    public MutableLiveData <String> FirstName = new MutableLiveData <> ();
    public MutableLiveData <String> LastName  = new MutableLiveData <> ();
    public MutableLiveData <String> Email     = new MutableLiveData <> ();
    public MutableLiveData <String> Password  = new MutableLiveData <> ();

    public MutableLiveData <ResultImpl> getAccountResponse () {
        return this.accountRepo.getmResponseGetAccount();
    }

    public ConfigurationViewModel () {
        this.accountRepo = new AccountRepo();
    }

    public void getAccount () {

        this.accountRepo.getAccount(PreferencesProvider.providePreferences().getString("token", ""));

    }

    public void setAccount () {
        Account account = this.accountRepo.getAccount();

        Username.setValue  (account.getUsername());
        FirstName.setValue (account.getFirstname());
        LastName.setValue  (account.getLastname());
        Email.setValue     (account.getEmail());
        Password.setValue  (account.getPassword());

        Log.d (TAG, "Password: " + Password.getValue());
    }
}
