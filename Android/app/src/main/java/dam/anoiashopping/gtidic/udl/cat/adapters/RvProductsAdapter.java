package dam.anoiashopping.gtidic.udl.cat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dam.anoiashopping.gtidic.udl.cat.R;

public class RvProductsAdapter extends RecyclerView.Adapter<RvProductsAdapter.myViewHolder>{
    List<String> listItems;

    public RvProductsAdapter(List<String> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclerview_items, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position) {
        holder.tvItem.setText(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem;
        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }
}
