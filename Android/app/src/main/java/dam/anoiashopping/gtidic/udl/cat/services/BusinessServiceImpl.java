package dam.anoiashopping.gtidic.udl.cat.services;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class BusinessServiceImpl implements BusinessServiceI {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<ResponseBody> create_business(String token, Business business) {
        return retrofit.create(BusinessServiceI.class).create_business(token, business);
    }

    @Override
    public Call<List<Business>> get_business (String token) {
        return retrofit.create(BusinessServiceI.class).get_business(token);
    }
}
