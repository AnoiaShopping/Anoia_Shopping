package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.os.Build;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.nio.charset.StandardCharsets;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class LoginViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private final String TAG = "SignUpVM";

    public MutableLiveData <String> email    = new MutableLiveData <> ();
    public MutableLiveData <String> password = new MutableLiveData <> ();

    public MutableLiveData <ResultImpl> emailValidator = new MutableLiveData <> ();
    public MutableLiveData <ResultImpl> passwordValidator = new MutableLiveData <> ();

    public MutableLiveData <Boolean> registerClick = new MutableLiveData <> ();

    public LoginViewModel() {
        this.accountRepo = new AccountRepo();
    }

    public MutableLiveData <Boolean> getLoginResponse () {
        return this.accountRepo.getCorrectLogin();
    }

    public boolean isFormValid () {

        if (email.getValue() == null) {
            emailValidator.setValue(new ResultImpl(R.string.emptyEmail, false));
        } else {
            emailValidator.setValue(new ResultImpl(0, true));
        }

        if (password.getValue() == null) {
            passwordValidator.setValue(new ResultImpl(R.string.emptyPassword, false));
        } else {
            passwordValidator.setValue(new ResultImpl(0, true));
        }

        return emailValidator.getValue().isValid() && passwordValidator.getValue().isValid();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onLogin (){

        if (isFormValid()) {

            Log.d (TAG, "Valid form");

            String auth_token = email.getValue() + ":" + password.getValue();
            byte[] data = auth_token.getBytes(StandardCharsets.UTF_8);
            auth_token = Base64.encodeToString(data, Base64.DEFAULT);
            auth_token = ("Authentication " + auth_token).trim();

            // Ha de ser commit, ens hem d'assegurar que i són per l'interceptor.
            // De moment aquest PreferencesProvider no es necessari
            //PreferencesProvider.providePreferences().edit().putString("token", auth_token).commit();

            this.accountRepo.createTokenUser(auth_token);

        } else {

            Log.d (TAG, "Invalid form");
        }
    }

    public void onRegisterClick () {
        this.registerClick.setValue(true);
    }

}
