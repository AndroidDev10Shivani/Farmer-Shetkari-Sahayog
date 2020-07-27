package com.sample.shetkarisahayogfarmer.DashboardActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sample.shetkarisahayogfarmer.DashboardActivity;
import com.sample.shetkarisahayogfarmer.R;

public class IdentityActivity extends AppCompatActivity {
    TextView textName, textAppId, textMobileNo, textGender, textDob, textAddress, textState, textDistrict, textCity, textPincode, textLandHolding, textProposedCrops;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);

        mobileNumber = getIntent().getStringExtra("mobileNumber");

        textName = findViewById(R.id.textView_profileName);
        textAppId = findViewById(R.id.textView_profileAppId);
        textMobileNo = findViewById(R.id.textView_profileMobileNum);
        textGender = findViewById(R.id.textView_profileGender);
        textDob = findViewById(R.id.textView_profileDob);
        textAddress = findViewById(R.id.textView_profileAddress);
        textState = findViewById(R.id.textView_profileState);
        textDistrict = findViewById(R.id.textView_profileDistrict);
        textCity = findViewById(R.id.textView_profileCity);
        textPincode = findViewById(R.id.textView_profilePincode);
        textLandHolding = findViewById(R.id.textView_profileLandHolding);
        textProposedCrops = findViewById(R.id.textView_profileProposedCrop);

        textName.setText(getIntent().getStringExtra("name"));
        textAppId.setText(getIntent().getStringExtra("applicationID"));
        textMobileNo.setText(getIntent().getStringExtra("mobileNumber"));
        textGender.setText(getIntent().getStringExtra("gender"));
        textDob.setText(getIntent().getStringExtra("dob"));
        textAddress.setText(getIntent().getStringExtra("address"));
        textState.setText(getIntent().getStringExtra("state"));
        textDistrict.setText(getIntent().getStringExtra("district"));
        textCity.setText(getIntent().getStringExtra("city"));
        textPincode.setText(getIntent().getStringExtra("pincode"));
        textLandHolding.setText(getIntent().getStringExtra("landHolding"));
        textProposedCrops.setText(getIntent().getStringExtra("proposedCrops"));

        findViewById(R.id.imageView_back_identity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class).putExtra("mobileNumber", mobileNumber));
            }
        });
    }

}