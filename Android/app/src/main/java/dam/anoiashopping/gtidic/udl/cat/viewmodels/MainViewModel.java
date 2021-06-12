package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Business;
import dam.anoiashopping.gtidic.udl.cat.models.Token;
import dam.anoiashopping.gtidic.udl.cat.repositories.AccountRepo;
import dam.anoiashopping.gtidic.udl.cat.repositories.BusinessRepo;
import dam.anoiashopping.gtidic.udl.cat.utils.ResultImpl;

public class MainViewModel extends ViewModel {

    private AccountRepo accountRepo;
    private BusinessRepo businessRepo;
    private final String TAG = "MainVM";

    public MainViewModel () {
        this.accountRepo = new AccountRepo ();
        this.businessRepo = new BusinessRepo ();
    }

    public void getBusiness () {
        this.businessRepo.getBusiness();
    }

    public MutableLiveData<List<Business>> returnBusiness(){
        return this.businessRepo.getmResponseBusinessList();
    }
}
