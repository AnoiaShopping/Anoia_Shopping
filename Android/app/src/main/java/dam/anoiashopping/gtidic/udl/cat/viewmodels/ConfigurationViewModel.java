package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
}
