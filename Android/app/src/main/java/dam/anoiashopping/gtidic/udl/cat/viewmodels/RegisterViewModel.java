package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.Validation;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class RegisterViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData <String> username  = new MutableLiveData <> ();
    public MutableLiveData <String> firstName = new MutableLiveData <> ();
    public MutableLiveData <String> lastName  = new MutableLiveData <> ();
    public MutableLiveData <String> email     = new MutableLiveData <> ();
    public MutableLiveData <String> password  = new MutableLiveData <> ();
    public MutableLiveData <String> password2 = new MutableLiveData <> ();

    public boolean EULA_Check = false;

    public MutableLiveData <ResultImpl> usernameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> firstNameValidator = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> lastNameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> emailValidator     = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> passwordValidator  = new MutableLiveData <> ();
    //public MutableLiveData <ResultImpl> isEULAAccepted     = new MutableLiveData <> ();

    public MutableLiveData <ResultImpl> getRegisterResponse () {
        return this.accountRepo.getmResponseRegister();
    }

    public RegisterViewModel () {
        this.accountRepo = new AccountRepo();
    }

    public boolean isFormValid () {

        firstNameValidator.setValue (Validation.checkFirstName (firstName.getValue()));
        lastNameValidator.setValue  (Validation.checkLastName  (lastName.getValue()));
        usernameValidator.setValue  (Validation.checkUsername  (username.getValue()));
        emailValidator.setValue     (Validation.checkEmail     (email.getValue()));
        passwordValidator.setValue  (Validation.checkPasswords (password.getValue(), password2.getValue()));

        return (firstNameValidator.getValue().isValid() && lastNameValidator.getValue().isValid() &&
                usernameValidator.getValue().isValid()  && emailValidator.getValue().isValid()    &&
                passwordValidator.getValue().isValid()) && EULA_Check;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRegister () {

        if (this.isFormValid()) {

            Account account = new Account ();

            account.setUsername  (username.getValue());
            account.setFirstname (firstName.getValue());
            account.setLastname  (lastName.getValue());
            account.setEmail     (email.getValue());
            account.setPassword  (password.getValue());

            this.accountRepo.registerAccount(account);

            Log.d (TAG, "Valid Form");

        } else {
            Log.d (TAG, "Invalid form");
        }
    }
}