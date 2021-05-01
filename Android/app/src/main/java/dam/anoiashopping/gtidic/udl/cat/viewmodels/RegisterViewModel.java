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

    public MutableLiveData <String> Username  = new MutableLiveData <> ();
    public MutableLiveData <String> FirstName = new MutableLiveData <> ();
    public MutableLiveData <String> LastName  = new MutableLiveData <> ();
    public MutableLiveData <String> Email     = new MutableLiveData <> ();
    public MutableLiveData <String> Password  = new MutableLiveData <> ();
    public MutableLiveData <String> Password2 = new MutableLiveData <> ();

    public boolean EULA_Check = false;

    public MutableLiveData <ResultImpl> FirstNameValidator = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> LastNameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> UsernameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> EmailValidator     = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> PasswordValidator  = new MutableLiveData <> ();


    public MutableLiveData <ResultImpl> getRegisterResponse () {
        return this.accountRepo.getmResponseRegister();
    }

    public RegisterViewModel() {
        this.accountRepo = new AccountRepo();
    }

    public boolean isFormValid () {

        FirstNameValidator.setValue (Validation.checkFirstName (FirstName.getValue()));
        LastNameValidator.setValue  (Validation.checkLastName  (LastName.getValue()));
        UsernameValidator.setValue  (Validation.checkUsername  (Username.getValue()));
        EmailValidator.setValue     (Validation.checkEmail     (Email.getValue()));
        PasswordValidator.setValue  (Validation.checkPasswords (Password.getValue(), Password2.getValue()));



        return (FirstNameValidator.getValue().isValid() && LastNameValidator.getValue().isValid() &&
                UsernameValidator.getValue().isValid()  && EmailValidator.getValue().isValid()    &&
                PasswordValidator.getValue().isValid()) && EULA_Check;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRegister () {

        if (this.isFormValid()) {

            Account account = new Account ();

            account.setUsername(Username.getValue());
            account.setFirstname(FirstName.getValue());
            account.setLastname(LastName.getValue());
            account.setEmail(Email.getValue());
            account.setPassword(Password.getValue());

            this.accountRepo.registerAccount(account);

            Log.d (TAG, "Valid Form");

        } else {

            Log.d (TAG, "Invalid form");
        }
    }


}