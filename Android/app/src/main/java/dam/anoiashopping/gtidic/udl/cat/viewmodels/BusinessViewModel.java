package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class BusinessViewModel extends ViewModel {
    private BusinessRepo businessRepo;

    public void createBusiness(Business business){
        this.businessRepo.createBusiness(business);
    }

    public MutableLiveData<ResultImpl> createBusinessResponse () {return this.businessRepo.getmResponseCreateBusiness();}

    public BusinessViewModel() {
        this.businessRepo = new BusinessRepo();
    }
}
