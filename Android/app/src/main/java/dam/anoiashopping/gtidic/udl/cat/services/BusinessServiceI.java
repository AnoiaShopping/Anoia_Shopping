package dam.anoiashopping.gtidic.udl.cat.services;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.models.Products;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface BusinessServiceI {

    // POST CALLS

    @POST("/business/create")
    Call<ResponseBody> create_business (@Header("Authorization") String token, @Body Business business);

    @POST ("/business/uploadphoto")
    Call <ResponseBody> upload_business_photo (@Body RequestBody builder, @Header ("Authorization") String token);

    // GET CALLS

    @GET("/product")
    Call<List<Products>> get_productList (@Query("business_id") int id);

    @GET("/business")
    Call<List<Business>> get_business (@Header("Authorization") String token);

    @GET("/business/own_business")
    Call<List<Business>> get_own_business (@Header("Authorization") String token);

    // PUT CALLS

    @PUT("/business/update")
    Call <ResponseBody> update_business (@Header("Authorization") String token, @Body Business business);

}
