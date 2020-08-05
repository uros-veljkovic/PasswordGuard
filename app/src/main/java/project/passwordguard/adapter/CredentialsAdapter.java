package project.passwordguard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.passwordguard.R;
import project.passwordguard.databinding.CredentialsItemBinding;
import project.passwordguard.model.CredentialsEntity;

public class CredentialsAdapter extends RecyclerView.Adapter<CredentialsAdapter.ViewHolder> implements Filterable {

    public static final int VIEW_LIST_EMPTY = 0;
    public static final int VIEW_LIST_POPULATED = 1;
    private Context context;
    private List<CredentialsEntity> credentialsEntities;
    private List<CredentialsEntity> credentialsEntitiesSearched;

    public CredentialsAdapter(Context context) {
        this.context = context;
        this.credentialsEntities = new ArrayList<>();
        this.credentialsEntitiesSearched = new ArrayList<>(credentialsEntities);
    }

    @Override
    public int getItemViewType(int position) {
        if (credentialsEntities == null || credentialsEntities.isEmpty()) {
            return VIEW_LIST_EMPTY;
        } else {
            return VIEW_LIST_POPULATED;
        }
    }

    @NonNull
    @Override
    public CredentialsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int resourceID = 0;
        switch (viewType) {
            case VIEW_LIST_EMPTY:
//                resourceID = R.layout.credentials_item_empty;
                break;
            case VIEW_LIST_POPULATED:
                resourceID = R.layout.credentials_item;
                break;
        }
        CredentialsItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                resourceID,
                parent,
                false);

        return new CredentialsAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CredentialsAdapter.ViewHolder holder, int position) {
        holder.itemBinding.setCredentials(credentialsEntities.get(position));
/*
        Drawable logo = LogoProvider.getLogoResourceFor(credentialsEntity);
        holder.itemBinding.credentialsItemIvWebsiteLogo.setForeground(logo);
        holder.itemBinding.credentialsItemIvWebsiteLogo.refreshDrawableState();
*/
    }

    @Override
    public int getItemCount() {
        return credentialsEntities.size();
    }

    public void setCredentialsEntities(List<CredentialsEntity> credentialsEntities) {
        this.credentialsEntities = credentialsEntities;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return credentialsFilter;
    }

    private Filter credentialsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CredentialsEntity> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(credentialsEntitiesSearched);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CredentialsEntity entity : credentialsEntitiesSearched) {
                    if (entity.getWebsiteUrl().toLowerCase().contains(filterPattern)) {
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
            credentialsEntities.clear();
            credentialsEntities.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CredentialsItemBinding itemBinding;

        public ViewHolder(@NonNull CredentialsItemBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }
    }
}
