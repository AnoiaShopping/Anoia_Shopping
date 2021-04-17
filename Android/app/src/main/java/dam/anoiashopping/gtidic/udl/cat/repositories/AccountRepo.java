package dam.anoiashopping.gtidic.udl.cat.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.services.AccountServiceI;
import dam.anoiashopping.gtidic.udl.cat.services.AccountServiceImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepo {

    private final String TAG = "AccountRepo";

    private final AccountServiceI accountService;
    private final MutableLiveData <String> mResponseRegister;
    private final MutableLiveData <String> mResponseLogin;
    private final MutableLiveData <Boolean> correctLogin;
    private final MutableLiveData <Boolean> correctToken;

    public AccountRepo() {
        this.accountService = new AccountServiceImpl ();
        this.mResponseRegister = new MutableLiveData <> ();
        this.mResponseLogin = new MutableLiveData <> ();
        this.correctLogin = new MutableLiveData <> ();
        this.correctToken = new MutableLiveData <> ();
    }

    public MutableLiveData <String> getmResponseRegister () {
        return mResponseRegister;
    }

    public MutableLiveData <String> getmResponseLogin () {
        return mResponseLogin;
    }

    public MutableLiveData <Boolean> getCorrectLogin () { return correctLogin; }

    public MutableLiveData <Boolean> getCorrectDeletedToken () { return correctToken; }

    public void registerAccount(Account account){

        accountService.register(account).enqueue(new Callback <ResponseBody> () {
            @Override
            public void onResponse(Call <ResponseBody> call, Response <ResponseBody> response) {

                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  return_code);

                if (return_code == 200){
                    mResponseRegister.setValue("El registre s'ha fet correctament!!!!");
                }else{
                    String error_msg = "Error: " + response.errorBody();
                    // TODO : ValidationResultImpl
                    mResponseRegister.setValue(error_msg);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                mResponseRegister.setValue(error_msg);
            }
        });

    }


    public void createTokenUser (String s){

        accountService.create_token(s).enqueue(new Callback <ResponseBody> () {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int code = response.code();
                Log.d(TAG,  "createTokenUser() -> ha rebut del backend un codi:  " + code);

                if (code == 200) {

                    correctLogin.setValue(true);

                    try {
                        String authToken = response.body().string().split(":")[1];
                        authToken=authToken.substring(2,authToken.length()-2);
                        Log.d(TAG,  "createTokenUser() -> ha rebut el token:  " + authToken);
                        mResponseLogin.setValue(authToken);
                        PreferencesProvider.providePreferences().edit().
                                putString("token", authToken).apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{

                    correctLogin.setValue(false);

                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "createTokenUser() -> ha rebut l'error:  " + error_msg);
                        PreferencesProvider.providePreferences().edit().remove("token").apply();
                        mResponseLogin.setValue(error_msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "createTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
                PreferencesProvider.providePreferences().edit().remove("token").apply();
                mResponseLogin.setValue(error_msg);
            }

        });
    }

    public void deleteTokenUser (String s, Token token) {

        accountService.delete_token(s, token).enqueue(new Callback <ResponseBody> () {

            @Override
            public void onResponse (Call<ResponseBody> call, Response<ResponseBody> response) {

                int code = response.code();
                Log.d(TAG,  "deleteTokenUser() -> ha rebut del backend un codi:  " + code);

                if (code == 200){

                    correctToken.setValue(true);

                    Log.d(TAG, "El token s'ha eliminat correctament.");
                    PreferencesProvider.providePreferences().edit().remove("token").apply();

                }else{

                    correctToken.setValue(false);

                    String error_msg = "Error: " + response.errorBody();
                    Log.d (TAG, error_msg);
                    // TODO : ValidationResultImpl
                }
            }

            @Override
            public void onFailure (Call<ResponseBody> call, Throwable t) {

                correctToken.setValue(false);

                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "deleteTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
            }
        });
    }

}