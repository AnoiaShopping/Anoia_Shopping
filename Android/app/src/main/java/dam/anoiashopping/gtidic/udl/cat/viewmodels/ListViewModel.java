package dam.anoiashopping.gtidic.udl.cat.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Event;
import dam.anoiashopping.gtidic.udl.cat.repositories.EventsRepo;

public class ListViewModel extends ViewModel {

    private EventsRepo eventsRepo;

    public ListViewModel () {
        eventsRepo = new EventsRepo();
    }

    public void getEvents() {
        eventsRepo.getEvents();
    }

    public MutableLiveData<List<Event>> returnEvents() {
        return this.eventsRepo.getmResponseEvents();
    }

}
