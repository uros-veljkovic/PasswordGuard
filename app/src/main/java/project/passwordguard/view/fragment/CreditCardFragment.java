package project.passwordguard.view.fragment;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.app.SearchManager;
import android.content.Context;
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

import project.passwordguard.adapter.CredentialsAdapter;
import project.passwordguard.adapter.CreditCardAdapter;
import project.passwordguard.application.MyApplication;
import project.passwordguard.databinding.CredentialsFragmentBinding;
import project.passwordguard.databinding.CreditCardFragmentBinding;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.viewmodel.CreditCardViewModel;
import project.passwordguard.R;

public class CreditCardFragment extends Fragment {

    private SearchView searchView;

    private CreditCardViewModel creditCardViewModel;
    private CreditCardFragmentBinding binding;
    private CreditCardAdapter adapter;
    private ArrayList<CreditCardEntity> creditCardEntities;

    public static CreditCardFragment newInstance() {
        return new CreditCardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        generateDemoCreditCards();
        initRecyclerView(inflater, container);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void generateDemoCreditCards() {
        creditCardEntities = new ArrayList<>();

        creditCardEntities.add(new CreditCardEntity("Banca Intesa",
                "MasterCard",
                "5555 4444 3333 2222",
                "3/21",
                "529",
                "1012"));
        creditCardEntities.add(new CreditCardEntity("Adico bank",
                "MasterCard",
                "5555 4444 3333 2222",
                "3/21",
                "529",
                "1012"));
        creditCardEntities.add(new CreditCardEntity("OTP bank",
                "VISA",
                "5555 4444 3333 2222",
                "3/21",
                "529",
                "1012"));
    }

    private void initRecyclerView(LayoutInflater inflater, ViewGroup container) {
        binding = CreditCardFragmentBinding.inflate(inflater, container, false);
        binding.fragmentCreditCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentCreditCardRecyclerView.setHasFixedSize(true);

        adapter = new CreditCardAdapter(getContext(), creditCardEntities);
        binding.fragmentCreditCardRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        creditCardViewModel = new ViewModelProvider(this).get(CreditCardViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        searchView = (SearchView) searchItem.getActionView();
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