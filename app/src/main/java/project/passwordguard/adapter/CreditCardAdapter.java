package project.passwordguard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import project.passwordguard.R;
import project.passwordguard.databinding.CreditCardItemBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ViewHolder> implements Filterable {

    public static final int VIEW_LIST_EMPTY = 0;
    public static final int VIEW_LIST_POPULATED = 1;
    private Context context;
    private List<CreditCardEntity> creditCardEntities;
    private List<CreditCardEntity> creditCardEntitiesSearched;

    public CreditCardAdapter(Context context) {
        this.context = context;
        this.creditCardEntities = new ArrayList<>();
        this.creditCardEntitiesSearched = new ArrayList<>(creditCardEntities);
    }

    @Override
    public int getItemViewType(int position) {
        if (creditCardEntities == null || creditCardEntities.isEmpty()) {
            return VIEW_LIST_EMPTY;
        } else {
            return VIEW_LIST_POPULATED;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int resourceID = 0;

        switch (viewType) {
            case VIEW_LIST_EMPTY:
                resourceID = R.layout.credit_card_item;
                break;
            case VIEW_LIST_POPULATED:
                resourceID = R.layout.credit_card_item;
                break;
        }

        CreditCardItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                resourceID,
                parent,
                false);

        return new CreditCardAdapter.ViewHolder(itemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.setCreditCard(creditCardEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return creditCardEntities.size();
    }

    public void setCreditCardEntities(List<CreditCardEntity> entities) {
        this.creditCardEntities = entities;
        this.creditCardEntitiesSearched = new ArrayList<>(this.creditCardEntities);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return creditCardFilter;
    }

    private Filter creditCardFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CreditCardEntity> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(creditCardEntitiesSearched);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CreditCardEntity entity : creditCardEntitiesSearched) {
                    if (entity.getCardName().toLowerCase().contains(filterPattern)
                            || entity.getBankName().toLowerCase().contains(filterPattern)
                            || entity.getPin().contains(filterPattern)) {
                        filteredList.add(entity);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            creditCardEntities.clear();
            creditCardEntities.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public CreditCardEntity getItem(int itemPosition) {
        return creditCardEntities.get(itemPosition);
    }

    public void deleteItem(int position) {
        creditCardEntities.remove(position);
        creditCardEntitiesSearched = new ArrayList<>(creditCardEntities);
        notifyItemRemoved(position);
    }

    public void insertItem(int position, CreditCardEntity entity) {
        creditCardEntities.add(position, entity);
        creditCardEntitiesSearched = new ArrayList<>(creditCardEntities);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CreditCardItemBinding itemBinding;

        public ViewHolder(@NonNull CreditCardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
