package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class CreateBusinessViewModel extends ViewModel {

    private BusinessRepo businessRepo;

    public CreateBusinessViewModel() {
        this.businessRepo = new BusinessRepo();
    }

    public void createBusiness(Business business){
        this.businessRepo.createBusiness(business);
    }

    public void uploadBusinessPhoto (File image, String name) {this.businessRepo.uploadBusinessPhoto(image, name);}

    public MutableLiveData<ResultImpl> createBusinessResponse () {return this.businessRepo.getmResponseCreateBusiness();}

    public MutableLiveData<ResultImpl> uploadPhotoBusinessResponse () {return this.businessRepo.getmResponseUploadPhoto();}
}
