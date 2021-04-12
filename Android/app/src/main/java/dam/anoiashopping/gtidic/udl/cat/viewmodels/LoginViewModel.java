package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.nio.charset.StandardCharsets;

import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.Validation;
import dam.anoiashopping.gtidic.udl.cat.utils.ValidationResultImpl;

public class LoginViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData <String> Email    = new MutableLiveData <> ();
    public MutableLiveData <String> Password = new MutableLiveData <> ();

    public MutableLiveData <ValidationResultImpl> EmailValidator = new MutableLiveData <> ();
    public MutableLiveData <ValidationResultImpl> PasswordValidator = new MutableLiveData <> ();

    public LoginViewModel() {
        this.accountRepo = new AccountRepo();
    }

    public boolean isFormValid () {
        EmailValidator.setValue    (Validation.checkEmail(Email.getValue()));
        PasswordValidator.setValue (Validation.checkPassword(Password.getValue()));

        return EmailValidator.getValue().isValid() && PasswordValidator.getValue().isValid();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void login(String email, String password){

        String auth_token = email + ":" + password;
        byte[] data = auth_token.getBytes(StandardCharsets.UTF_8);
        auth_token = Base64.encodeToString(data, Base64.DEFAULT);
        auth_token = ("Authentication " + auth_token).trim();

        // Ha de ser commit, ens hem d'assegurar que i són per l'interceptor.
        PreferencesProvider.providePreferences().edit().putString("token", auth_token).commit();

        this.accountRepo.createTokenUser(auth_token);
    }


}
