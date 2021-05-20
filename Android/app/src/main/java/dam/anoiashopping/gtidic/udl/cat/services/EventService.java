package dam.anoiashopping.gtidic.udl.cat.services;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.models.Event;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {

    @GET("events")
    Call<List<Event>> getEvents();

}
