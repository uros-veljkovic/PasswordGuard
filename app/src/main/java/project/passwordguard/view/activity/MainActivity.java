package project.passwordguard.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import project.passwordguard.R;
import project.passwordguard.databinding.ActivityMainBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.util.AnimationProvider;
import project.passwordguard.view.fragment.CredentialsFragment;
import project.passwordguard.view.fragment.CreditCardFragment;
import project.passwordguard.viewmodel.FragmentCredentialsViewModel;
import project.passwordguard.viewmodel.FragmentCreditCardViewModel;

import static project.passwordguard.util.constants.Constants.CREATE_CREDENTIALS_CODE;
import static project.passwordguard.util.constants.Constants.CREATE_CREDIT_CARD_CODE;
import static project.passwordguard.util.constants.Constants.EXTRA_CREDENTIALS;
import static project.passwordguard.util.constants.Constants.UPDATE_CREDENTIALS_CODE;
import static project.passwordguard.util.constants.Constants.UPDATE_CREDIT_CARD_CODE;
import static project.passwordguard.view.activity.CreateOrEditCreditCardInstanceActivity.EXTRA_CREDIT_CARD;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentCredentialsViewModel credentialsViewModel;
    private FragmentCreditCardViewModel creditCardViewModel;
    private ActivityMainBinding binding;
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initViewModel();

        showFragment(new CredentialsFragment());
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.activityMainBottomNavigation.setOnNavigationItemSelectedListener(this);
        clickHandler = new ClickHandler(this);
        binding.setClickHandler(clickHandler);
    }

    private void initViewModel() {
        credentialsViewModel = new ViewModelProvider(this).get(FragmentCredentialsViewModel.class);
        creditCardViewModel = new ViewModelProvider(this).get(FragmentCreditCardViewModel.class);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_credentials:
                backstackFragment();
                showFragment(new CredentialsFragment());
                break;
            case R.id.nav_item_credit_card:
                backstackFragment();
                showFragment(new CreditCardFragment());
                break;
            default:
                break;
        }

        return true;
    }


    public class ClickHandler {

        public Context context;
        private boolean fabIsActive = false;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void onFabNew(View view) {
            changeChildrenFabVisibility();
            startFabAnimations();
            fabIsActive = !fabIsActive;
        }

        public void changeChildrenFabVisibility() {
            if (!fabIsActive) {
                setChildrenFabVisibility(View.VISIBLE);
            } else {
                setChildrenFabVisibility(View.INVISIBLE);
            }
        }

        private void setChildrenFabVisibility(int visibilityId) {
            binding.activityMainFabNewCredentials.setVisibility(visibilityId);
            binding.activityMainFabNewCreditCard.setVisibility(visibilityId);
        }

        public void startFabAnimations() {
            if (!fabIsActive) {
                startOpenFabAnimations();
            } else {
                startCloseFabAnimations();
            }
        }

        private void startCloseFabAnimations() {
            startAnimations(AnimationProvider.animRotateClose, AnimationProvider.animToBottom);
        }

        private void startOpenFabAnimations() {
            startAnimations(AnimationProvider.animRotateOpen, AnimationProvider.animFromBottom);
        }

        private void startAnimations(Animation mainFabAnimation, Animation childrenFabAnimation) {
            binding.activityMainFab.startAnimation(mainFabAnimation);
            binding.activityMainFabNewCreditCard.startAnimation(childrenFabAnimation);
            binding.activityMainFabNewCredentials.startAnimation(childrenFabAnimation);
        }

        public void onFabNewCredentials(View view) {
            Intent intent = new Intent(MainActivity.this, CreateOrEditCredentialsInstanceActivity.class);
            startActivityForResult(intent, CREATE_CREDENTIALS_CODE);
            startCloseFabAnimations();
        }

        public void onFabNewCreditCard(View view) {
            Intent intent = new Intent(MainActivity.this, CreateOrEditCreditCardInstanceActivity.class);
            startActivityForResult(intent, CREATE_CREDIT_CARD_CODE);
            startCloseFabAnimations();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case CREATE_CREDENTIALS_CODE:
                    createCredentialsFrom(Objects.requireNonNull(data));
                    break;
                case CREATE_CREDIT_CARD_CODE:
                    createCreditCardFrom(Objects.requireNonNull(data));
                    break;
                case UPDATE_CREDENTIALS_CODE:
                    updateCredentialsFrom(Objects.requireNonNull(data));
                    break;
                case UPDATE_CREDIT_CARD_CODE:
                    updateCreditCardFrom(Objects.requireNonNull(data));
                    break;
                default:
                    break;
            }
        } else {
            backstackFragment();
            showFragment(new CredentialsFragment());
        }
    }

    private void createCreditCardFrom(Intent data) {
        CreditCardEntity creditCardEntity = data.getParcelableExtra(EXTRA_CREDIT_CARD);
        creditCardViewModel.insert(creditCardEntity);
        backstackFragment();
        showFragment(new CreditCardFragment());
    }

    private void createCredentialsFrom(Intent data) {
        CredentialsEntity credentialsEntity = data.getParcelableExtra(EXTRA_CREDENTIALS);
        credentialsViewModel.insert(credentialsEntity);
        backstackFragment();
        showFragment(new CredentialsFragment());
    }

    private void updateCreditCardFrom(Intent data) {
        CreditCardEntity creditCardEntity = data.getParcelableExtra(EXTRA_CREDIT_CARD);
        creditCardViewModel.insert(creditCardEntity);
        backstackFragment();
        showFragment(new CreditCardFragment());
    }

    private void updateCredentialsFrom(Intent data) {
        CredentialsEntity credentialsEntity = data.getParcelableExtra(EXTRA_CREDENTIALS);
        credentialsViewModel.update(credentialsEntity);
        backstackFragment();
        showFragment(new CredentialsFragment());
    }

    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_container, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

        binding.executePendingBindings();
    }

    public void backstackFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
        getSupportFragmentManager().popBackStack();
        removeCurrentFragment();
    }

    private void removeCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFrag = getSupportFragmentManager()
                .findFragmentById(R.id.activity_main_fragment_container);

        if (currentFrag != null) {
            transaction.remove(currentFrag);
        }

        transaction.commitAllowingStateLoss();
    }

}

