package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class BusinessViewModel extends ViewModel {
    private BusinessRepo businessRepo;
    private final String TAG = "BusinessVM";

    public MutableLiveData <Business> businessMutableLiveData = new MutableLiveData<>();

    public BusinessViewModel() {
        businessRepo = new BusinessRepo();
    }

    public void updateBusiness () {
        businessRepo.updateBusiness(PreferencesProvider.providePreferences().getString("token", ""), businessMutableLiveData.getValue());
    }

    public MutableLiveData <ResultImpl> getUpdateBusinessResponse () {
        return this.businessRepo.getmResponseUpdateBusiness();
    }
}
