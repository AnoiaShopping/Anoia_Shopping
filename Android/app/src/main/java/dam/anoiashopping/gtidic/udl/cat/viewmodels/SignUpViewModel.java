package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Checkable;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.Utils;
import dam.anoiashopping.gtidic.udl.cat.utils.Validation;
import dam.anoiashopping.gtidic.udl.cat.utils.ValidationResultImpl;
import dam.anoiashopping.gtidic.udl.cat.views.RegisterActivity;
import dam.anoiashopping.gtidic.udl.cat.views.RegisterConfirmationActivity;

public class SignUpViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData <String> Username  = new MutableLiveData <> ();
    public MutableLiveData <String> FirstName = new MutableLiveData <> ();
    public MutableLiveData <String> LastName  = new MutableLiveData <> ();
    public MutableLiveData <String> Email     = new MutableLiveData <> ();
    public MutableLiveData <String> Password  = new MutableLiveData <> ();
    public MutableLiveData <String> Password2 = new MutableLiveData <> ();

    public boolean EULAcheck = false;

    public MutableLiveData <ValidationResultImpl> FirstNameValidator = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> LastNameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> UsernameValidator  = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> EmailValidator     = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> PasswordValidator  = new MutableLiveData <> ();


    public MutableLiveData <String> getRegisterResponse () {
        return this.accountRepo.getmResponseRegister();
    }

    public SignUpViewModel() {
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
                PasswordValidator.getValue().isValid()) && EULAcheck;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRegister(){

        if (this.isFormValid()) {

            Account account = new Account ();

            account.setUsername(Username.getValue());
            account.setFirstname(FirstName.getValue());
            account.setLastname(LastName.getValue());
            account.setEmail(Email.getValue());
            account.setPassword(Password.getValue());

            this.accountRepo.registerAccount(account);

            Log.d ("SignUpViewModel", "Valid Form");

        } else {
            Log.d ("SignUpViewModel", "Invalid form");
        }
    }


}