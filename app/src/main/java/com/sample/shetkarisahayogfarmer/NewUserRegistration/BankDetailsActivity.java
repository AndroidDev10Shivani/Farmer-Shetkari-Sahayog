package com.sample.shetkarisahayogfarmer.NewUserRegistration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sample.shetkarisahayogfarmer.DashboardActivities.MyDealsActivity;
import com.sample.shetkarisahayogfarmer.R;

public class BankDetailsActivity extends AppCompatActivity {
    TextInputLayout regBankAccNumber, regIFSCCode, regAadharCard, regPanCard;
    ImageView scanAadharCard, scanPanCard;
    
    Long bankAccountNumber, ifscCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        regBankAccNumber = findViewById(R.id.editText_bankAccountNumber);
        regIFSCCode = findViewById(R.id.editText_ifscCode);
        regAadharCard = findViewById(R.id.editText_aadharCard);
        regPanCard = findViewById(R.id.editText_panCard);

        findViewById(R.id.imageView_aadharCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,23);
            }
        });

        findViewById(R.id.imageView_panCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,23);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap =(Bitmap)data.getExtras().get("data");
        startActivity(new Intent(getApplicationContext(), ScanActivity.class).putExtra("Image",bitmap));
    }
    private Boolean validateBankAccountNumber() {
        String val = regBankAccNumber.getEditText().getText().toString();
        String pattern = "[0-9]{9,18}$";
        if (val.isEmpty()) {
            regBankAccNumber.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            regBankAccNumber.setError("Invalid bank account number");
            return false;
        } else {
            regBankAccNumber.setError(null);
            regBankAccNumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateIFSCCode() {
        String val = regIFSCCode.getEditText().getText().toString();
        String pattern = "[A-Z|a-z]{4}[0][A-Z0-9]{6}$";
        if (val.isEmpty()) {
            regIFSCCode.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            regIFSCCode.setError("Invalid IFSC code");
            return false;
        } else {
            regIFSCCode.setError(null);
            regIFSCCode.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateAadharCard() {
        String val = regAadharCard.getEditText().getText().toString();
        if (val.isEmpty()) {
            regAadharCard.setError("Please scan aadhar card");
            return false;
        } else {
            regAadharCard.setError(null);
            return true;
        }
    }

    private Boolean validatePanCard() {
        String val = regPanCard.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPanCard.setError("Please scan pan card");
            return false;
        } else {
            regPanCard.setError(null);
            return true;
        }
    }

    public void onPreviousButton(View view) {
        onBackPressed();
    }

    public void onNextButton(View view) {
        if (!validateBankAccountNumber() | !validateIFSCCode() | !validateAadharCard() |!validatePanCard()) {
            //Do nothing
        } else {
           
            bankAccountNumber = Long.parseLong(regBankAccNumber.getEditText().getText().toString());
            ifscCode = Long.parseLong(regIFSCCode.getEditText().getText().toString());

            Toast.makeText(this, (int) (bankAccountNumber + ifscCode), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), ApplicationIdActivity.class));
        }
    }
}