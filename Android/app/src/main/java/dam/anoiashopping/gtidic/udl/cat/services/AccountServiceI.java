package dam.anoiashopping.gtidic.udl.cat.services;

import dam.anoiashopping.gtidic.udl.cat.models.Account;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface AccountServiceI {

    // POST CALLS

    @POST("/users/register")
    Call <ResponseBody> register (@Body Account account);

    @POST("/account/create_token")
    Call <ResponseBody> create_token (@Header ("Authorization") String auth_token);

    @POST("/account/delete_token")
    Call <ResponseBody> delete_token (@Header("Authorization") String token, @Body Token bodyToken);

    //@Multipart //TODO : Afegir uploadImage (Utilitzar @Multipart)
    //@POST("/account/profile/update_profile_image")
    //Call <ResponseBody> uploade_image ();

    // GET CALLS

    @GET("/account/profile")
    Call <Account> get_account (@Header("Authorization") String token);

    @GET("/users/show/{username}")
    Call <Account> show_account (@Header("Authorization") String token);

}
