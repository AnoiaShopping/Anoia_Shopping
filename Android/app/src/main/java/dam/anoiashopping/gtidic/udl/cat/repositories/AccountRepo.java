package dam.anoiashopping.gtidic.udl.cat.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.services.AccountServiceI;
import dam.anoiashopping.gtidic.udl.cat.services.AccountServiceImpl;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepo {

    //TODO: implementar missatges d'error

    private final String TAG = "AccountRepo";

    private final AccountServiceI accountService;

    private final MutableLiveData <ResultImpl> mResponseRegister;
    private final MutableLiveData <ResultImpl> mResponseGetAccount;
    private final MutableLiveData <ResultImpl> mResponseCreateToken;
    private final MutableLiveData <ResultImpl> mResponseDeleteToken;
    private Account account;

    public AccountRepo() {
        this.accountService       = new AccountServiceImpl ();
        this.mResponseRegister    = new MutableLiveData <> ();
        this.mResponseGetAccount  = new MutableLiveData <> ();
        this.account              = new Account();
        this.mResponseCreateToken = new MutableLiveData <> ();
        this.mResponseDeleteToken = new MutableLiveData <> ();
    }

    public MutableLiveData <ResultImpl> getmResponseRegister() {
        return mResponseRegister;
    }

    public Account getAccount() {
        return account;
    }

    public MutableLiveData <ResultImpl> getmResponseCreateToken() {
        return mResponseCreateToken;
    }

    public MutableLiveData <ResultImpl> getmResponseDeleteToken() {
        return mResponseDeleteToken;
    }

    public MutableLiveData<ResultImpl> getmResponseGetAccount() {
        return mResponseGetAccount;
    }

    public void registerAccount(Account account){

        accountService.register(account).enqueue(new Callback <ResponseBody> () {
            @Override
            public void onResponse(Call <ResponseBody> call, Response <ResponseBody> response) {

                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"registerAccount() -> ha rebut el codi: " +  return_code);

                if (return_code == 200){
                    mResponseRegister.setValue (new ResultImpl(0, true));
                }else{

                    String error_msg = "Error: " + response.errorBody();
                    Log.d (TAG, error_msg);

                    mResponseRegister.setValue (new ResultImpl(0, false));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d (TAG, error_msg);

                mResponseRegister.setValue (new ResultImpl(0, false));
            }
        });

    }

    public void getAccount (String token) {
        accountService.get_account(token).enqueue(new Callback <Account> () {

            @Override
            public void onResponse (Call <Account> call, Response<Account> response) {

                int code = response.code();
                Log.d(TAG,  "getAccount() -> ha rebut el codi:  " + code);

                if (code == 200) {

                    //account  = call; // TODO: Error, falla el get

                    mResponseGetAccount.setValue (new ResultImpl (0, true));

                } else {

                    String error_msg = "Error: " + response.errorBody();
                    Log.d (TAG, "getAccount() -> ha rebut l'error:" + error_msg);

                    mResponseGetAccount.setValue (new ResultImpl (0, false));

                }
            }

            @Override
            public void onFailure (Call<Account> call, Throwable t) {

                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "getAccount() onFailure() -> ha rebut el missatge:  " + error_msg);

                mResponseCreateToken.setValue (new ResultImpl(0, false));

            }
        });
    }

    public void createTokenUser (String auth_token) {

        accountService.create_token(auth_token).enqueue(new Callback <ResponseBody> () {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                int code = response.code();
                Log.d(TAG,  "createTokenUser() -> ha rebut el codi:  " + code);

                if (code == 200) {
                    try {

                        String authToken = response.body().string().split(":")[1];
                        authToken=authToken.substring(2,authToken.length()-2);
                        Log.d(TAG,  "createTokenUser() -> ha rebut el token:  " + authToken);
                        PreferencesProvider.providePreferences().edit().putString("token", authToken).apply();

                        mResponseCreateToken.setValue (new ResultImpl(0, true));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {

                    try {

                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "createTokenUser() -> ha rebut l'error: " + error_msg);
                        PreferencesProvider.providePreferences().edit().remove("token").apply();

                        mResponseCreateToken.setValue (new ResultImpl(0, false));

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

                mResponseCreateToken.setValue (new ResultImpl(0, false));
            }

        });
    }

    public void deleteTokenUser (String token, Token tokenBody) {

        accountService.delete_token(token, tokenBody).enqueue(new Callback <ResponseBody> () {

            @Override
            public void onResponse (Call<ResponseBody> call, Response<ResponseBody> response) {

                int code = response.code();
                Log.d(TAG,  "deleteTokenUser() -> ha rebut el codi:  " + code);

                if (code == 200){

                    PreferencesProvider.providePreferences().edit().remove("token").apply();
                    Log.d(TAG,  "deleteTokenUser() -> Token eliminat correctament.");

                    mResponseDeleteToken.setValue(new ResultImpl(0, true));

                }else if (code == 401){

                    String error_msg = "Error: " + response.errorBody();
                    Log.d(TAG,  "deleteTokenUser() -> ha rebut l'error:  " + error_msg);

                    Log.d (TAG, "deleteTokenUser() -> S'eliminarà el token local.");
                    PreferencesProvider.providePreferences().edit().remove("token").apply();

                    mResponseDeleteToken.setValue (new ResultImpl(-1, true));
                    // TODO: Canviar a un missatge d'avís al usuari

                } else {

                    String error_msg = "Error: " + response.errorBody();
                    Log.d(TAG,  "deleteTokenUser() -> ha rebut l'error:  " + error_msg);

                    mResponseCreateToken.setValue (new ResultImpl(0, false));
                }
            }

            @Override
            public void onFailure (Call<ResponseBody> call, Throwable t) {

                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "deleteTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);

                mResponseDeleteToken.setValue (new ResultImpl(0, false));
            }
        });
    }

}