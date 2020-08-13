package project.passwordguard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import project.passwordguard.R;
import project.passwordguard.databinding.ActivityCreateOrEditCredentialsInstanceBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.util.AutocompleteTextViewDataProvider;
import project.passwordguard.viewmodel.CredentialsInstanceViewModel;

import static project.passwordguard.util.constants.Constants.CREATE_CREDENTIALS_CODE;
import static project.passwordguard.util.constants.Constants.EXTRA_CREDENTIALS;
import static project.passwordguard.util.constants.Constants.UPDATE_CREDENTIALS_CODE;

public class CreateOrEditCredentialsInstanceActivity extends AppCompatActivity {

    private ActivityCreateOrEditCredentialsInstanceBinding binding;
    private CredentialsInstanceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_edit_credentials_instance);
        viewModel = new ViewModelProvider(this).get(CredentialsInstanceViewModel.class);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null && bundle.getParcelable(EXTRA_CREDENTIALS) != null){
            CredentialsEntity entity = bundle.getParcelable(EXTRA_CREDENTIALS);
            viewModel.setEntity(entity);
            binding.activityCreteOrEdtiCredentialsInstanceBtnSave.setText("Update");
        }

        bind();
    }

    private void bind() {
        binding.activityMainActvWebsiteName.setAdapter(AutocompleteTextViewDataProvider.getWebsiteAdapter(this));
        binding.setViewModel(viewModel);
        binding.setClickHandler(new ClickHander());
    }

    public class ClickHander {

        public ClickHander(){
        }

        public void onBtnSave(View view) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_CREDENTIALS, viewModel.getEntity());
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }

        public void onBtnCancel(View view) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

}