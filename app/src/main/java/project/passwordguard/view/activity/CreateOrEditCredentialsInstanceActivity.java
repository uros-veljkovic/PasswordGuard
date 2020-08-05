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
import project.passwordguard.util.WebsiteDataProvider;
import project.passwordguard.viewmodel.CredentialsInstanceViewModel;

public class CreateOrEditCredentialsInstanceActivity extends AppCompatActivity {

    public static final String EXTRA_CREDENTIALS = "EXTRA_CREDENTIALS";
    private ActivityCreateOrEditCredentialsInstanceBinding binding;
    private CredentialsInstanceViewModel viewModel;
    private ClickHander clickHander = new ClickHander(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_edit_credentials_instance);
        viewModel = new ViewModelProvider(this).get(CredentialsInstanceViewModel.class);

        bind();
    }

    private void bind() {
        binding.activityMainActvWebsiteName.setAdapter(WebsiteDataProvider.getWebsiteAdapter(this));
        binding.setViewModel(viewModel);
        binding.setClickHandler(clickHander);
    }

    public class ClickHander {

        Context context;

        public ClickHander(Context context) {
            this.context = context;
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

        }
    }

}