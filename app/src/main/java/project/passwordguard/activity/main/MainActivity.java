package project.passwordguard.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.List;

import project.passwordguard.R;
import project.passwordguard.activity.main.adapter.ActivityMainRecyclerViewAdapter;
import project.passwordguard.databinding.ActivityMainBinding;
import project.passwordguard.entity.CredentialsEntity;
import project.passwordguard.activity.main.viewmodel.CredentialsViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CredentialsViewModel credentialsViewModel;
    private ActivityMainRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initBinding();
    }

    private void initViewModel() {
        credentialsViewModel = new ViewModelProvider(this).get(CredentialsViewModel.class);
        credentialsViewModel.getCredentials().observe(this, new Observer<List<CredentialsEntity>>() {
            @Override
            public void onChanged(List<CredentialsEntity> credentialsEntities) {
                updateUI(credentialsEntities);
            }
        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.activityMainRv.setLayoutManager(new LinearLayoutManager(this));
        binding.activityMainRv.setHasFixedSize(true);

        adapter = new ActivityMainRecyclerViewAdapter(this);
        binding.activityMainRv.setAdapter(adapter);
    }

    //TODO: Proslediti parametar ove metode u adapter i refreshovati recylerview
    private void updateUI(List<CredentialsEntity> credentialsEntities) {
        adapter.setCredentialsEntities(credentialsEntities);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}