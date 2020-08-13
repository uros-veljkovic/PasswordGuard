package project.passwordguard.view.fragment;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import project.passwordguard.R;
import project.passwordguard.databinding.CredentialsFragmentBinding;
import project.passwordguard.listener.RecyclerViewSwipeListener;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.adapter.CredentialsAdapter;
import project.passwordguard.repository.Repository;
import project.passwordguard.util.constants.Constants;
import project.passwordguard.util.swipe.SwipeActionManager;
import project.passwordguard.view.activity.CreateOrEditCredentialsInstanceActivity;
import project.passwordguard.viewmodel.FragmentCredentialsViewModel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static project.passwordguard.util.constants.Constants.EXTRA_CREDENTIALS;
import static project.passwordguard.util.constants.Constants.UPDATE_CREDENTIALS_CODE;

public class CredentialsFragment extends Fragment implements RecyclerViewSwipeListener {

    private SearchView searchView;

    private FragmentCredentialsViewModel viewModel;
    private CredentialsFragmentBinding binding;
    private CredentialsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        initRecyclerView(inflater, container);
        initRecyclerViewSwipeHelper();
        return binding.getRoot();
    }

    private void initRecyclerView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = CredentialsFragmentBinding.inflate(inflater, container, false);
        binding.fragmentCredentialsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentCredentialsRecyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(requireActivity()).get(FragmentCredentialsViewModel.class);
        adapter = new CredentialsAdapter(requireContext());
    }

    private void initRecyclerViewSwipeHelper() {
        SwipeActionManager swipeActionManager = new SwipeActionManager(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeActionManager.getCallback());
        itemTouchHelper.attachToRecyclerView(binding.fragmentCredentialsRecyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCredentials().observe(getViewLifecycleOwner(), new Observer<List<CredentialsEntity>>() {
            @Override
            public void onChanged(List<CredentialsEntity> entities) {
                adapter.setCredentialsEntities(entities);
            }
        });
        binding.fragmentCredentialsRecyclerView.setAdapter(adapter);
        binding.executePendingBindings();
    }

    @Override
    public void onSwipeLeft(final int itemPosition) {
        final CredentialsEntity entity = adapter.getItem(itemPosition);

        adapter.deleteItem(itemPosition);
        Repository.getInstance().delete(entity);

        Snackbar.make(binding.fragmentCredentialsRecyclerView, "Credentials for " + entity.getWebsiteUrl() + " deleted.", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.insertItem(itemPosition, entity);
                        Repository.getInstance().insert(entity);
                    }
                }).show();
    }

    @Override
    public void onSwipeRight(int itemPosition) {
        CredentialsEntity entity = adapter.getItem(itemPosition);

        Intent intent = new Intent(getActivity(), CreateOrEditCredentialsInstanceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_CREDENTIALS, entity);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, UPDATE_CREDENTIALS_CODE);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchedString) {
                adapter.getFilter().filter(searchedString);
                return false;
            }
        });

    }
}