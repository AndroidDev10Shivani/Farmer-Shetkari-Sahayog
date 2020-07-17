package com.sample.shetkarisahayogfarmer.NewUserRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sample.shetkarisahayogfarmer.DashboardActivities.MyDealsActivity;
import com.sample.shetkarisahayogfarmer.R;

public class CommunicationDetailsActivity extends AppCompatActivity {

    TextInputLayout regAddress, regPincode;
    Spinner regCountry, regState, regCity;
    String address, country, state, city;
    long pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_details);
        regAddress = findViewById(R.id.editText_address);
        regPincode = findViewById(R.id.editText_pincode);
        
       /* regCountry = findViewById(R.id.editText_pincode);
        regState = findViewById(R.id.editText_pincode);
        regCity = findViewById(R.id.editText_pincode);*/
    }

    public void onPreviousButton(View view) {
        onBackPressed();
    }

    private Boolean validateAddress() {
        String val = regAddress.getEditText().getText().toString();
        if (val.isEmpty()) {
            regAddress.setError("Field cannot be empty");
            return false;
        } else {
            regAddress.setError(null);
            return true;
        }
    }

    private Boolean validatePincode() {
        String val = regPincode.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPincode.setError("Field cannot be empty");
            return false;
        } else {
            regPincode.setError(null);
            return true;
        }
    }

    public void onNextButton(View view) {
        if (!validateAddress() | !validatePincode()) {
            //Do nothing
        } else {
            address = regAddress.getEditText().getText().toString();
           // country = regMiddleName.getEditText().getText().toString();
          //  state = (genderMale.isChecked()) ? "Male" : "Female";
           // city = regDob.getEditText().getText().toString();
            pincode = Long.parseLong(regPincode.getEditText().getText().toString());

            Toast.makeText(this, address+pincode, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), LandDetailsActivity.class));
        }
    }
}