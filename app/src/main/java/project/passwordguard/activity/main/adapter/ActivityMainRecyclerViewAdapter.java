package project.passwordguard.activity.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import project.passwordguard.R;
import project.passwordguard.databinding.CredentialsItemBinding;
import project.passwordguard.entity.CredentialsEntity;

public class ActivityMainRecyclerViewAdapter extends RecyclerView.Adapter<ActivityMainRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<CredentialsEntity> credentialsEntities;

    public ActivityMainRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityMainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CredentialsItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.credentials_item,
                parent,
                false);

        return new ActivityMainRecyclerViewAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityMainRecyclerViewAdapter.ViewHolder holder, int position) {
        CredentialsEntity credentialsEntity = credentialsEntities.get(position);

        holder.itemBinding.setCredentials(credentialsEntity);
        bindImageBasedOnWebsiteUrl(holder, credentialsEntity);

        holder.itemBinding.executePendingBindings();
    }

    //TODO: Proveri da li je bolje da radis bind sličice preko contains (npr uzmemo sve slicice pa radimo contains)
    private void bindImageBasedOnWebsiteUrl(@NonNull ViewHolder holder, CredentialsEntity credentialsEntity) {
/*        Field[] drawablesFields = R.drawable.class.getFields();
        ArrayList<Drawable> drawables = new ArrayList<>();

        for (Field field : drawablesFields) {
            field.getName().contains(credentialsEntity.getWebsiteUrl()){
                //TODO: ako sadrzi, binduj tu slicicu
            }
        }*/

        Context context = holder.itemBinding.credentialsItemIvWebsiteLogo.getContext();
        int drawableID = context.getResources().getIdentifier(credentialsEntity.getWebsiteUrl(), "drawable", context.getPackageName());
        holder.itemBinding.credentialsItemIvWebsiteLogo.setImageResource(drawableID);
    }

    @Override
    public int getItemCount() {
        return credentialsEntities.size();
    }

    public void setCredentialsEntities(List<CredentialsEntity> credentialsEntities) {
        this.credentialsEntities = credentialsEntities;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CredentialsItemBinding itemBinding;

        public ViewHolder(@NonNull CredentialsItemBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }
    }
}
