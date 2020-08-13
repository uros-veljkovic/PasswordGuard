package project.passwordguard.adapter;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.passwordguard.R;
import project.passwordguard.databinding.CredentialsItemBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.util.PasswordIconProvider;

public class CredentialsAdapter extends RecyclerView.Adapter<CredentialsAdapter.ViewHolder> implements Filterable {

    public static final int VIEW_LIST_EMPTY = 0;
    public static final int VIEW_LIST_POPULATED = 1;
    private Context context;
    private List<CredentialsEntity> credentialsEntities;
    private List<CredentialsEntity> credentialsEntitiesSearched;

    public CredentialsAdapter(Context context) {
        this.context = context;
        this.credentialsEntities = new ArrayList<>();
        this.credentialsEntitiesSearched = new ArrayList<>(this.credentialsEntities);
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
                resourceID = R.layout.credentials_item;
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
    }

    @Override
    public int getItemCount() {
        return credentialsEntities.size();
    }

    public void setCredentialsEntities(List<CredentialsEntity> credentialsEntities) {
        this.credentialsEntities = credentialsEntities;
        this.credentialsEntitiesSearched = new ArrayList<>(this.credentialsEntities);
        notifyDataSetChanged();
    }

    public CredentialsEntity getItem(int position){
        return credentialsEntities.get(position);
    }

    public void deleteItem(int position){
        credentialsEntities.remove(position);
        credentialsEntitiesSearched = new ArrayList<>(credentialsEntities);
        notifyItemRemoved(position);
    }

    public void insertItem(int position, CredentialsEntity entity){
        credentialsEntities.add(position, entity);
        credentialsEntitiesSearched = new ArrayList<>(credentialsEntities);
        notifyItemInserted(position);
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

        private boolean passwordLocked = true;
        private CredentialsItemBinding itemBinding;

        public ViewHolder(@NonNull CredentialsItemBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;

            onIconPasswordClick();
        }

        private void onIconPasswordClick() {
            itemBinding.icPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (passwordLocked) {
                        itemBinding.icPassword.setImageDrawable(PasswordIconProvider.unlocked);
                        itemBinding.credentialsItemTvPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        itemBinding.icPassword.setImageDrawable(PasswordIconProvider.locked);
                        itemBinding.credentialsItemTvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    itemBinding.executePendingBindings();
                    passwordLocked = !passwordLocked;
                }
            });
        }
    }
}
