package dam.anoiashopping.gtidic.udl.cat.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dam.anoiashopping.gtidic.udl.cat.models.Business;

public class BusinessDiffCallback extends DiffUtil.ItemCallback<Business> {
    @Override
    public boolean areItemsTheSame(@NonNull Business oldItem, @NonNull Business newItem) {
        return oldItem.getNom().equals(newItem.getNom());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Business oldItem, @NonNull Business newItem) {
        return false;
    }
}
