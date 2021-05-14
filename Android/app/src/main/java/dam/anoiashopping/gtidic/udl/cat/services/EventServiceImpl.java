package dam.anoiashopping.gtidic.udl.cat.services;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Event;
import dam.anoiashopping.gtidic.udl.cat.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class EventServiceImpl implements EventService{

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<List<Event>> getEvents() {
        return retrofit.create(EventService.class).getEvents();
    }
}
