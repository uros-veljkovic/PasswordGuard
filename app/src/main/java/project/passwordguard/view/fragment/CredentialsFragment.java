package project.passwordguard.view.fragment;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import project.passwordguard.R;
import project.passwordguard.databinding.CredentialsFragmentBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.adapter.CredentialsAdapter;
import project.passwordguard.viewmodel.CredentialsViewModel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

public class CredentialsFragment extends Fragment {

    private CredentialsViewModel credentialsViewModel;
    private CredentialsFragmentBinding binding;
    private CredentialsAdapter adapter;
    private ArrayList<CredentialsEntity> credentialsEntities;

    public static CredentialsFragment newInstance() {
        return new CredentialsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        generateDemoCredentials();
        initRecyclerView(inflater, container);

        return binding.getRoot();
    }

    private void initRecyclerView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = CredentialsFragmentBinding.inflate(inflater, container, false);
        binding.fragmentCredentialsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentCredentialsRecyclerView.setHasFixedSize(true);

        adapter = new CredentialsAdapter(getContext(), credentialsEntities);
        binding.fragmentCredentialsRecyclerView.setAdapter(adapter);
    }


    private void generateDemoCredentials() {
        credentialsEntities = new ArrayList<>();

        credentialsEntities.add(new CredentialsEntity("www.facebook.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.youtube.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.linkedin.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.discord.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.gmail.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.skype.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.instagram.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
        credentialsEntities.add(new CredentialsEntity("www.snapchat.com", "urkeev14", "uros.veljkovic1996@gmail.com", "password"));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        credentialsViewModel = new ViewModelProvider(this).get(CredentialsViewModel.class);
        /*credentialsViewModel.getCredentials().observe(Objects.requireNonNull(getActivity()), new Observer<List<CredentialsEntity>>() {
            @Override
            public void onChanged(List<CredentialsEntity> credentialsEntities) {
                adapter.setCredentialsEntities(credentialsEntities);
            }
        });*/
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }


}