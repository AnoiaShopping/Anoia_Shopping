package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;

public class SignUpViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";
    public MutableLiveData<String> Username = new MutableLiveData<>();
    public MutableLiveData<String> Name = new MutableLiveData<>();
    public MutableLiveData<String> Surname = new MutableLiveData<>();
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    public SignUpViewModel() {
        this.accountRepo = new AccountRepo();
    }


    public void onRegister(){

            Account account = new Account();
            account.setUsername(Username.getValue());
            account.setFirstname(Name.getValue());
            account.setLastname(Surname.getValue());
            account.setPassword(Password.getValue());
            account.setEmail(Email.getValue());
        if (0 == 0) {
            Log.d(TAG, account.toString());
            this.accountRepo.registerAccount(account);
            //RegisterActivity.validRegistration();
        }
    }


}