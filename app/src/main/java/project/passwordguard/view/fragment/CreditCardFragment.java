package project.passwordguard.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import project.passwordguard.R;
import project.passwordguard.adapter.CreditCardAdapter;
import project.passwordguard.databinding.CreditCardFragmentBinding;
import project.passwordguard.listener.RecyclerViewSwipeListener;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.repository.Repository;
import project.passwordguard.util.swipe.SwipeActionManager;
import project.passwordguard.view.activity.CreateOrEditCreditCardInstanceActivity;
import project.passwordguard.viewmodel.FragmentCreditCardViewModel;

import static project.passwordguard.util.constants.Constants.EXTRA_CREDIT_CARD;
import static project.passwordguard.util.constants.Constants.UPDATE_CREDIT_CARD_CODE;

public class CreditCardFragment extends Fragment implements RecyclerViewSwipeListener {

    private SearchView searchView;

    private FragmentCreditCardViewModel viewModel;
    private CreditCardFragmentBinding binding;
    private CreditCardAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

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

    private void initRecyclerView(LayoutInflater inflater, ViewGroup container) {
        binding = CreditCardFragmentBinding.inflate(inflater, container, false);
        binding.fragmentCreditCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentCreditCardRecyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(requireActivity()).get(FragmentCreditCardViewModel.class);
        adapter = new CreditCardAdapter(requireContext());
    }

    private void initRecyclerViewSwipeHelper() {
        SwipeActionManager swipeActionManager = new SwipeActionManager(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeActionManager.getCallback());
        itemTouchHelper.attachToRecyclerView(binding.fragmentCreditCardRecyclerView);
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCreditCards().observe(getViewLifecycleOwner(), new Observer<List<CreditCardEntity>>() {
            @Override
            public void onChanged(List<CreditCardEntity> entities) {
                adapter.setCreditCardEntities(entities);
            }
        });
        binding.fragmentCreditCardRecyclerView.setAdapter(adapter);
        binding.executePendingBindings();
    }

    @Override
    public void onSwipeLeft(final int itemPosition) {
        final CreditCardEntity entity = adapter.getItem(itemPosition);

        adapter.deleteItem(itemPosition);
        Repository.getInstance().delete(entity);

        Snackbar.make(binding.fragmentCreditCardRecyclerView, "Credit card from " + entity.getBankName() + " deleted.", Snackbar.LENGTH_LONG)
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
        CreditCardEntity entity = adapter.getItem(itemPosition);

        Intent intent = new Intent(getActivity(), CreateOrEditCreditCardInstanceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_CREDIT_CARD, entity);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, UPDATE_CREDIT_CARD_CODE);

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