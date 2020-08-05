package project.passwordguard.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

import project.passwordguard.R;
import project.passwordguard.databinding.ActivityMainBindingImpl;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.util.AnimationProvider;
import project.passwordguard.view.fragment.CredentialsFragment;
import project.passwordguard.view.fragment.CreditCardFragment;
import project.passwordguard.viewmodel.FragmentCredentialsViewModel;
import project.passwordguard.viewmodel.FragmentCreditCardViewModel;

import static project.passwordguard.view.activity.CreateOrEditCredentialsInstanceActivity.EXTRA_CREDENTIALS;
import static project.passwordguard.view.activity.CreateOrEditCreditCardInstanceActivity.EXTRA_CREDIT_CARD;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_CREDENTIALS_CODE = 1;
    public static final int NEW_CREDIT_CARD_CODE = 2;

    private FragmentCredentialsViewModel fragmentCredentialsViewModel;
    private FragmentCreditCardViewModel fragmentCreditCardViewModel;
    private ActivityMainBindingImpl binding;
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initBinding();
        startFragment(new CredentialsFragment());
    }

    private void initViewModel() {
        fragmentCredentialsViewModel = new ViewModelProvider(this).get(FragmentCredentialsViewModel.class);
        fragmentCreditCardViewModel = new ViewModelProvider(this).get(FragmentCreditCardViewModel.class);
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new ClickHandler(this);
        binding.setClickHandler(clickHandler);
    }


    public class ClickHandler {

        public Context context;
        private boolean fabClicked = false;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public boolean onNavigationClick(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_item_credentials:
                    selectedFragment = new CredentialsFragment();
                    break;
                case R.id.nav_item_credit_card:
                    selectedFragment = new CreditCardFragment();
                    break;
                default:
                    break;
            }

            assert selectedFragment != null;

            startFragment(selectedFragment);

            return true;
        }

        public void onFabNew(View view) {
            changeChildrenFabVisibility();
            startFabAnimations();
            fabClicked = !fabClicked;
        }

        public void changeChildrenFabVisibility() {
            if (!fabClicked) {
                binding.activityMainFabNewCredentials.setVisibility(View.VISIBLE);
                binding.activityMainFabNewCreditCard.setVisibility(View.VISIBLE);
            } else {
                binding.activityMainFabNewCredentials.setVisibility(View.INVISIBLE);
                binding.activityMainFabNewCreditCard.setVisibility(View.INVISIBLE);
            }
        }

        public void startFabAnimations() {
            if (!fabClicked) {
                binding.activityMainFab.startAnimation(AnimationProvider.animRotateOpen);
                binding.activityMainFabNewCreditCard.startAnimation(AnimationProvider.animFromBottom);
                binding.activityMainFabNewCredentials.startAnimation(AnimationProvider.animFromBottom);
            } else {
                binding.activityMainFab.startAnimation(AnimationProvider.animRotateClose);
                binding.activityMainFabNewCreditCard.startAnimation(AnimationProvider.animToBottom);
                binding.activityMainFabNewCredentials.startAnimation(AnimationProvider.animToBottom);
            }
        }

        public void onFabNewCredentials(View view) {
            Intent intent = new Intent(MainActivity.this, CreateOrEditCredentialsInstanceActivity.class);
            startActivityForResult(intent, NEW_CREDENTIALS_CODE);
        }

        public void onFabNewCreditCard(View view) {
            Intent intent = new Intent(MainActivity.this, CreateOrEditCreditCardInstanceActivity.class);
            startActivityForResult(intent, NEW_CREDIT_CARD_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case NEW_CREDENTIALS_CODE:
                    createCredentialsFrom(Objects.requireNonNull(data));
                    break;
                case NEW_CREDIT_CARD_CODE:
                    createCreditCardFrom(Objects.requireNonNull(data));
                    break;
                default:
                    break;
            }
        }
    }

    private void createCreditCardFrom(Intent data) {
        CreditCardEntity creditCardEntity = data.getParcelableExtra(EXTRA_CREDIT_CARD);
        fragmentCreditCardViewModel.insert(creditCardEntity);
//        startFragment(new CreditCardFragment());
    }

    private void createCredentialsFrom(Intent data) {
        CredentialsEntity credentialsEntity = data.getParcelableExtra(EXTRA_CREDENTIALS);
        fragmentCredentialsViewModel.insert(credentialsEntity);
//        startFragment(new CredentialsFragment());
    }

    private void startFragment(Fragment concreteFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_fragment_container, concreteFragment)
                .commit();
    }
}

