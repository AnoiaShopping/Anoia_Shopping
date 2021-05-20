package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.ViewModel;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;

public class BusinessViewModel extends ViewModel {
    private BusinessRepo businessRepo;

    public void createBusiness(Business business){
        this.businessRepo.createBusiness(business);
    }

    public BusinessViewModel() {
        this.businessRepo = new BusinessRepo();
    }
}
