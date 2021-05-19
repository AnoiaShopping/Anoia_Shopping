package dam.anoiashopping.gtidic.udl.cat.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.services.BusinessServiceI;
import dam.anoiashopping.gtidic.udl.cat.services.BusinessServiceImpl;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessRepo {

    private MutableLiveData<ResultImpl> mResponseRegister;
    private final String TAG = "BusinessRepo";

    private final BusinessServiceI businessService;


    public BusinessRepo() {
        this.businessService = new BusinessServiceImpl();
        this.mResponseRegister = new MutableLiveData<>();
    }

    public void createBusiness(Business business){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        this.businessService.create_business(token, business).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"CreateBusiness() -> ha rebut el codi: " +  return_code);

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

            }
        });
    }
}
