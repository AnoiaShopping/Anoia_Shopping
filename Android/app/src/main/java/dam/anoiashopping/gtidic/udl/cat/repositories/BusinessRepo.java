package dam.anoiashopping.gtidic.udl.cat.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.services.BusinessServiceI;
import dam.anoiashopping.gtidic.udl.cat.services.BusinessServiceImpl;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessRepo {

    private final String TAG = "BusinessRepo";
    private final MutableLiveData<ResultImpl> mResponseCreateBusiness;
    //private MutableLiveData<ResultImpl> mResponseGetBusiness;
    private final MutableLiveData<ResultImpl> mResponseUploadPhoto;
    private final MutableLiveData<List<Business>> mResponseBusinessList;
    private final BusinessServiceI businessService;


    public MutableLiveData<ResultImpl> getmResponseCreateBusiness() {
        return mResponseCreateBusiness;
    }

    public MutableLiveData<List<Business>> getmResponseBusinessList() {
        return mResponseBusinessList;
    }

    public MutableLiveData<ResultImpl> getmResponseUploadPhoto() {
        return mResponseUploadPhoto;
    }

    public BusinessRepo() {
        this.businessService = new BusinessServiceImpl();
        this.mResponseCreateBusiness = new MutableLiveData<>();
        this.mResponseBusinessList = new MutableLiveData<>();
        this.mResponseUploadPhoto = new MutableLiveData<>();
    }

    public void createBusiness(Business business){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        this.businessService.create_business(token, business).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"CreateBusiness() -> ha rebut el codi: " +  return_code);

                if (return_code == 200){
                    mResponseCreateBusiness.setValue (new ResultImpl(0, true));
                }else{

                    String error_msg = "Error: " + response.errorBody();
                    Log.d (TAG, error_msg);

                    mResponseCreateBusiness.setValue (new ResultImpl(0, false));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getBusiness(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        this.businessService.get_business(token).enqueue(new Callback<List<Business>>() {
            @Override
            public void onResponse(Call<List<Business>> call, Response<List<Business>> response) {
                int return_code = response.code();  //200, 404, 401,...
                Log.d(TAG,"CreateBusiness() -> ha rebut el codi: " +  return_code);

                if (return_code == 200){
                    //mResponseGetBusiness.setValue (new ResultImpl(0, true));
                    mResponseBusinessList.setValue(response.body());
                }else{

                    String error_msg = "Error: " + response.errorBody();
                    Log.d (TAG, error_msg);

                    //mResponseGetBusiness.setValue (new ResultImpl(0, false));
                }
            }

            @Override
            public void onFailure(Call<List<Business>> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "createTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
            }
        });
    }

    public void uploadBusinessPhoto (File image, String name) {
        String token = PreferencesProvider.providePreferences().getString("token", "");

        RequestBody reqBody = RequestBody.create(image, MediaType.parse("image/*"));
        //MultipartBody.Part multipart = MultipartBody.Part.createFormData("image_file", image.getName(), reqBody);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("image_file", image.getName(),reqBody);
        builder.addFormDataPart("name", name);
        RequestBody requestBody = builder.build();

        this.businessService.upload_business_photo(requestBody, token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                Log.d(TAG,"uploadPhoto() -> ha rebut el codi: " +  code);

                if (code == 200) {
                    mResponseUploadPhoto.setValue(new ResultImpl(0, true));
                } else {
                    mResponseUploadPhoto.setValue(new ResultImpl(0, false));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "createTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
            }
        });
    }
}
