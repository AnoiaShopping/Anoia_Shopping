package dam.anoiashopping.gtidic.udl.cat.services;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BusinessServiceI {

    @POST("/business/create")
    Call<ResponseBody> create_business (@Header("Authorization") String token, @Body Business business);

    @GET("/business")
    Call<List<Business>> get_business (@Header("Authorization") String token);

}
