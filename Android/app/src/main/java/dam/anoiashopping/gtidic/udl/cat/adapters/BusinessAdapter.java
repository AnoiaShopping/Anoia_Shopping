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
import dam.anoiashopping.gtidic.udl.cat.models.Business;

public class BusinessAdapter extends ListAdapter <Business, BusinessAdapter.BusinessHolder> {

    private BusinessCommonHolder businessCommonHolder;
    private OnItemClickListener businessItemListener;

    public BusinessAdapter(@NonNull @NotNull DiffUtil.ItemCallback<Business> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @NotNull
    @Override
    public BusinessAdapter.BusinessHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclerview_business, null, false);
        return new BusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BusinessAdapter.BusinessHolder holder, int position) {
        Business business = getItem(position);
        businessCommonHolder.bindHolder(business);
    }

    public class BusinessHolder extends RecyclerView.ViewHolder {
        public BusinessHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            businessCommonHolder = new BusinessCommonHolder(itemView);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (businessItemListener != null && position != RecyclerView.NO_POSITION) {
                    businessItemListener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Business business);
    }

    public void businessListener(OnItemClickListener listener) {
        this.businessItemListener = listener;
    }
}
