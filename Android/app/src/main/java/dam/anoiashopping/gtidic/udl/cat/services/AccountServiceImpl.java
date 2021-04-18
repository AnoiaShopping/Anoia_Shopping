package dam.anoiashopping.gtidic.udl.cat.services;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AccountServiceImpl implements AccountServiceI {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    // POST CALLS

    @Override
    public Call<ResponseBody> register(Account account) {
        return retrofit.create(AccountServiceI.class).register(account);
    }

    @Override
    public Call<ResponseBody> create_token(String auth_token) {
        return retrofit.create(AccountServiceI.class).create_token(auth_token);
    }

    @Override
    public Call<ResponseBody> delete_token(String token, Token tokenBody) {
        return retrofit.create(AccountServiceI.class).delete_token(token, tokenBody);
    }

    // GET CALLS

    @Override
    public Call <Account> get_account (String token) {
        return retrofit.create(AccountServiceI.class).get_account(token);
    }

    @Override
    public Call <Account> show_account (String token) {
        return retrofit.create(AccountServiceI.class).show_account(token);
    }
}