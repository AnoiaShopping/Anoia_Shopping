package dam.anoiashopping.gtidic.udl.cat.services;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.network.RetrofitClientInstance;
import okhttp3.MultipartBody;
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

    @Override
    public Call<ResponseBody> upload_image(MultipartBody.Part image, String token) {
        return  retrofit.create(AccountServiceI.class).upload_image(image, token);
    }

    @Override
    public Call<ResponseBody> recovery_password(String email) {
        return retrofit.create(AccountServiceI.class).recovery_password(email);
    }

    @Override
    public Call<ResponseBody> update_password(String email, String password, String code) {
        return retrofit.create(AccountServiceI.class).update_password(email,password,code);
    }

    // GET CALLS

    @Override
    public Call<Account> get_account (String token) {
        return retrofit.create(AccountServiceI.class).get_account(token);
    }

    // PUT CALLS

    @Override
    public  Call <ResponseBody> update_account (String token, Account account) {
        return retrofit.create(AccountServiceI.class).update_account(token, account);
    }
}