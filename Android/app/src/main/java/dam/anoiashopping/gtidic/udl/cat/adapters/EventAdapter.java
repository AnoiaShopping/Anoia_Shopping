package dam.anoiashopping.gtidic.udl.cat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Event;

public class EventAdapter extends ListAdapter <Event, EventAdapter.EventHolder> {

    private EventCommonHolder eventCommonHolder;

    public EventAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Event> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public EventAdapter.EventHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, null, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EventAdapter.EventHolder holder, int position) {

        Event event = getItem(position);
        eventCommonHolder.bindHolder(event);
    }

    public class EventHolder extends RecyclerView.ViewHolder {

        public EventHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            eventCommonHolder = new EventCommonHolder(itemView);
        }
    }

}
