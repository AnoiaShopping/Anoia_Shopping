package dam.anoiashopping.gtidic.udl.cat.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dam.anoiashopping.gtidic.udl.cat.models.Event;

public class EventDiffCallback extends DiffUtil.ItemCallback<Event> {
    @Override
    public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.equals(newItem);
    }
}
