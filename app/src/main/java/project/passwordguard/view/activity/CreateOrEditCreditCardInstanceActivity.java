package project.passwordguard.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import project.passwordguard.R;

public class CreateOrEditCreditCardInstanceActivity extends AppCompatActivity {

    public static final String EXTRA_CREDIT_CARD = "EXTRA_CREDIT_CARD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_edit_credit_card_instance);
    }
}