package dam.anoiashopping.gtidic.udl.cat.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.adapters.EventAdapter;
import dam.anoiashopping.gtidic.udl.cat.adapters.EventDiffCallback;
import dam.anoiashopping.gtidic.udl.cat.models.Event;
import dam.anoiashopping.gtidic.udl.cat.viewmodels.ListViewModel;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ListViewModel listViewModel;
    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.eventslist);

        recyclerView.setLayoutManager(new LinearLayoutManager (this));

        eventAdapter = new EventAdapter(new EventDiffCallback());
        recyclerView.setAdapter (eventAdapter);

        initView();
    }

    public void initView () {
        listViewModel = new ListViewModel();
        listViewModel.getEvents();
        listViewModel.returnEvents().observe(this, events -> {
            eventAdapter.submitList(events);
            listViewModel.getEvents();
        });
    }
}