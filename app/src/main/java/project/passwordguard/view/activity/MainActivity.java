package project.passwordguard.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import project.passwordguard.R;
import project.passwordguard.databinding.ActivityMainBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.util.AnimationProvider;
import project.passwordguard.view.fragment.CredentialsFragment;
import project.passwordguard.view.fragment.CreditCardFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ClickHandler clickHandler;

    private ArrayList<CredentialsEntity> demoCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        startFragment(new CredentialsFragment());
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
            setVisibility(fabClicked);
            setAnimation(fabClicked);
            fabClicked = !fabClicked;
        }

        public void setVisibility(boolean fabClicked) {
            if (!fabClicked) {
                binding.activityMainFabNewCredentials.setVisibility(View.VISIBLE);
                binding.activityMainFabNewCreditCard.setVisibility(View.VISIBLE);
            } else {
                binding.activityMainFabNewCredentials.setVisibility(View.INVISIBLE);
                binding.activityMainFabNewCreditCard.setVisibility(View.INVISIBLE);
            }
        }

        public void setAnimation(boolean fabClicked) {
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
            Toast.makeText(context, "onFabNewCredentials", Toast.LENGTH_SHORT).show();
        }

        public void onFabNewCreditCard(View view) {
            Toast.makeText(context, "onFabNewCreditCard", Toast.LENGTH_SHORT).show();
        }
    }

    private void startFragment(Fragment selectedFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_fragment_container, selectedFragment)
                .commit();
    }
}

