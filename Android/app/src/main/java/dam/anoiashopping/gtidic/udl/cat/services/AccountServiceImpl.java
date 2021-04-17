package dam.anoiashopping.gtidic.udl.cat.services;

import org.json.JSONObject;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class AccountServiceImpl implements AccountServiceI {

    private Retrofit refrotit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<ResponseBody> register(Account account) {
        return refrotit.create(AccountServiceI.class).register(account);
    }

    @Override
    public Call <ResponseBody> create_token (String s) {
        return refrotit.create(AccountServiceI.class).create_token(s);
    }

    @Override
    public Call <ResponseBody> delete_token (String s, Token token) {
        return refrotit.create(AccountServiceI.class).delete_token(s, token);
    }

    @Override
    public Call <Account> get_account (String s) {
        return refrotit.create(AccountServiceI.class).get_account(s);
    }
}