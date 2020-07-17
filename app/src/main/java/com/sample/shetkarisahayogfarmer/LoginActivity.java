package com.sample.shetkarisahayogfarmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sample.shetkarisahayogfarmer.NewUserRegistration.FarmerDetailsActivity;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout registeredMobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registeredMobileNumber = findViewById(R.id.editText_RegisteredMobileNum);

        findViewById(R.id.textView_NewUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FarmerDetailsActivity.class));
            }
        });
    }

    private Boolean validateMobileNumber() {
        String val = registeredMobileNumber.getEditText().getText().toString();
        String pattern = "(0/91)?[7-9][0-9]{9}";
        if (val.isEmpty()) {
            registeredMobileNumber.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            registeredMobileNumber.setError("Invalid mobile number");
            return false;
        } else {
            registeredMobileNumber.setError(null);
            registeredMobileNumber.setErrorEnabled(false);
            return true;
        }
    }

    public void buttonGenerateOTP(View view) {
        if (!validateMobileNumber()) {
            return;
        }else{

        }
    }

    public void buttonLogIn(View view) {
        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
    }
}
