package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.Validation;
import dam.anoiashopping.gtidic.udl.cat.utils.ValidationResultImpl;

public class SignUpViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData <String> Username  = new MutableLiveData <> ();
    public MutableLiveData <String> Name      = new MutableLiveData <> ();
    public MutableLiveData <String> Surname   = new MutableLiveData <> ();
    public MutableLiveData <String> Email     = new MutableLiveData <> ();
    public MutableLiveData <String> Password  = new MutableLiveData <> ();
    public MutableLiveData <String> Password2 = new MutableLiveData <> ();

    public MutableLiveData <ValidationResultImpl> FirstNameValidator = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> LastNameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> UsernameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> EmailValidator     = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> PasswordValidator  = new MutableLiveData <> ();


    public SignUpViewModel() {
        this.accountRepo = new AccountRepo();
    }

    private boolean isFormValid () {
        FirstNameValidator.setValue (Validation.checkFirstName (Name.getValue()));
        LastNameValidator.setValue  (Validation.checkLastName  (Surname.getValue()));
        UsernameValidator.setValue  (Validation.checkUsername  (Username.getValue()));
        EmailValidator.setValue     (Validation.checkEmail     (Email.getValue()));
        PasswordValidator.setValue  (Validation.checkPassword  (Password.getValue(), Password2.getValue()));

        return (FirstNameValidator.getValue().isValid() && LastNameValidator.getValue().isValid() &&
                UsernameValidator.getValue().isValid()  && EmailValidator.getValue().isValid()    &&
                PasswordValidator.getValue().isValid());
    }


    public void onRegister(){

        if (this.isFormValid()) {
            Account account = new Account ();
            this.accountRepo.registerAccount(account);
        } else {
            Log.d ("SignUpViewModel", "Invalid form");
        }
    }


}