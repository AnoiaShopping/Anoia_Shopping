package dam.anoiashopping.gtidic.udl.cat.services;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AccountServiceI {

    ////////// POST CALLS

    @POST ("/users/register")
    Call <ResponseBody> register (@Body Account account);

    @POST ("/account/create_token")
    Call <ResponseBody> create_token (@Header ("Authorization") String auth_token);

    @POST ("/account/delete_token")
    Call <ResponseBody> delete_token (@Header ("Authorization") String token, @Body Token bodyToken);

    @Multipart
    @POST ("/account/profile/update_profile_image")
    Call <ResponseBody> upload_image (@Part MultipartBody.Part image, @Header ("Authorization") String token);

    ////////// GET CALLS

    @GET ("/account/profile")
    Call <Account> get_account (@Header ("Authorization") String token);

    // TODO: Acabar de fer la implementació
    @GET ("/users/show/{username}")
    Call <Account> show_account (@Header ("Authorization") String token);

    // TODO: Fer recuperació de contrassenya
    @FormUrlEncoded
    @POST ("account/recovery")
    Call<ResponseBody> recovery_password(@Field("email") String email);

    @FormUrlEncoded
    @POST ("account/update_password")
    Call<ResponseBody> update_password(@Field("email") String email, @Field("password") String password, @Field("code") String code);
    

}
