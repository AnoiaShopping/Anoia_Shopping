package dam.anoiashopping.gtidic.udl.cat.services;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountServiceI {

    @POST("/users/register")
    Call<ResponseBody> register (@Body Account account);

}
