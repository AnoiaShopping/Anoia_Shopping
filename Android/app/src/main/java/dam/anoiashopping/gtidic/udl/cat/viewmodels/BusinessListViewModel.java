package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;

public class BusinessListViewModel extends ViewModel {

    private BusinessRepo businessRepo;
    private final String TAG = "MainVM";

    public BusinessListViewModel () {
        this.businessRepo = new BusinessRepo ();
    }

    public void getOwnBusiness () {
        this.businessRepo.getOwnBusiness();
    }

    public MutableLiveData<List<Business>> returnBusiness(){
        return this.businessRepo.getmResponseBusinessOwnList();
    }
}
