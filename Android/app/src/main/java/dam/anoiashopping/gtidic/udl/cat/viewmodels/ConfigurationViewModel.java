package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;

public class ConfigurationViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "ConfigurationVM";

    public MutableLiveData <String> Username  = new MutableLiveData <> ();
    public MutableLiveData <String> FirstName = new MutableLiveData <> ();
    public MutableLiveData <String> LastName  = new MutableLiveData <> ();
    public MutableLiveData <String> Email     = new MutableLiveData <> ();
    public MutableLiveData <String> Password  = new MutableLiveData <> ();

    public ConfigurationViewModel () {
        this.accountRepo = new AccountRepo();
    }

    public void getAccount () {

        this.accountRepo.getAccount(PreferencesProvider.providePreferences().getString("token", ""));

        if (this.accountRepo.getmResponseGetAccount().getValue().isValid()) {
            Account account = this.accountRepo.getAccount();

            this.Username.setValue(account.getUsername());
            this.FirstName.setValue(account.getFirstname());
            this.LastName.setValue(account.getLastname());
            this.Email.setValue(account.getEmail());
            this.Password.setValue(account.getPassword());
        } else {
            // TODO
        }
    }
}
