package project.passwordguard.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import project.passwordguard.R;
import project.passwordguard.databinding.ActivityCreateOrEditCreditCardInstanceBinding;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.util.AutocompleteTextViewDataProvider;
import project.passwordguard.util.constants.Constants;
import project.passwordguard.viewmodel.CreditCardInstanceViewModel;

import static project.passwordguard.util.constants.Constants.EXTRA_CREDENTIALS;

public class CreateOrEditCreditCardInstanceActivity extends AppCompatActivity {

    public static final String EXTRA_CREDIT_CARD = "EXTRA_CREDIT_CARD";
    private CreditCardInstanceViewModel viewModel;
    private ActivityCreateOrEditCreditCardInstanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_edit_credit_card_instance);

        initViewModel();
        bind();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(CreditCardInstanceViewModel.class);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null && bundle.getParcelable(EXTRA_CREDIT_CARD) != null){
            CreditCardEntity entity = bundle.getParcelable(EXTRA_CREDIT_CARD);
            viewModel.setEntity(entity);
            binding.activityCreateOrEditCreditCardInstanceBtnSave.setText("Update");
        }
    }

    private void bind() {
        binding.activityMainActvWebsiteName.setAdapter(AutocompleteTextViewDataProvider.getCreditCardAdapter(this));
        binding.setViewModel(viewModel);
        binding.setClickHandler(new ClickHandler());
    }

    public class ClickHandler {

        public ClickHandler(){}

        public void onBntSave() {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_CREDIT_CARD, viewModel.getEntity());
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }

        public void onBtnCancel() {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

}