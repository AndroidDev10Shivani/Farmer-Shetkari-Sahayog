package com.sample.shetkarisahayogfarmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sample.shetkarisahayogfarmer.DashboardActivities.AggregatorActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.AggregatorDemandsActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.ComplaintActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.CropPricesActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.IdentityActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.MyDealsActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.UpdateCropsActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    TextView userName, userAppId;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));

        mobileNumber = getIntent().getStringExtra("mobileNumber");
        userName = findViewById(R.id.textView_userName);
        userAppId = findViewById(R.id.textView_userAppID);

        userName.setText(getIntent().getStringExtra("name"));
        userAppId.setText(getIntent().getStringExtra("applicationID"));

        findViewById(R.id.button_identity).setOnClickListener(this);
        findViewById(R.id.button_logout).setOnClickListener(this);
        findViewById(R.id.aggregators).setOnClickListener(this);
        findViewById(R.id.crop_prices).setOnClickListener(this);
        findViewById(R.id.update_crops).setOnClickListener(this);
        findViewById(R.id.aggregator_demands).setOnClickListener(this);
        findViewById(R.id.my_deals).setOnClickListener(this);
        findViewById(R.id.complaints).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_identity:
                FirebaseDatabase.getInstance().getReference("Farmer").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child(mobileNumber).child("name").getValue(String.class);
                        long applicationID = Long.parseLong(dataSnapshot.child(mobileNumber).child("applicationID").getValue().toString());
                        String gender = dataSnapshot.child(mobileNumber).child("gender").getValue(String.class);
                        String dob = dataSnapshot.child(mobileNumber).child("dob").getValue(String.class);
                        String address = dataSnapshot.child(mobileNumber).child("address").getValue(String.class);
                        String state = dataSnapshot.child(mobileNumber).child("state").getValue(String.class);
                        String district = dataSnapshot.child(mobileNumber).child("district").getValue(String.class);
                        String city = dataSnapshot.child(mobileNumber).child("city").getValue(String.class);
                        String pincode = dataSnapshot.child(mobileNumber).child("pincode").getValue(String.class);
                        String landHolding = dataSnapshot.child(mobileNumber).child("landHolding").getValue(String.class);
                        String proposedCrops = dataSnapshot.child(mobileNumber).child("proposedCrops").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), IdentityActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("mobileNumber", mobileNumber);
                        intent.putExtra("applicationID", applicationID);
                        intent.putExtra("gender", gender);
                        intent.putExtra("dob", dob);
                        intent.putExtra("address", address);
                        intent.putExtra("state", state);
                        intent.putExtra("district", district);
                        intent.putExtra("city", city);
                        intent.putExtra("pincode", pincode);
                        intent.putExtra("landHolding", landHolding);
                        intent.putExtra("proposedCrops", proposedCrops);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(DashboardActivity.this, "Database issue", Toast.LENGTH_SHORT).show();
                    }
                }); break;

            case R.id.button_logout:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;

            case R.id.aggregators:
                startActivity(new Intent(getApplicationContext(), AggregatorActivity.class).putExtra("mobileNumber", mobileNumber));
                break;

            case R.id.crop_prices:
                startActivity(new Intent(getApplicationContext(), CropPricesActivity.class).putExtra("mobileNumber", mobileNumber));
                break;

            case R.id.update_crops:
                startActivity(new Intent(getApplicationContext(), UpdateCropsActivity.class).putExtra("mobileNumber", mobileNumber));
                break;

            case R.id.aggregator_demands:
                startActivity(new Intent(getApplicationContext(), AggregatorDemandsActivity.class).putExtra("mobileNumber", mobileNumber));
                break;

            case R.id.my_deals:
                startActivity(new Intent(getApplicationContext(), MyDealsActivity.class).putExtra("mobileNumber", mobileNumber));
                break;

            case R.id.complaints:
                startActivity(new Intent(getApplicationContext(), ComplaintActivity.class).putExtra("mobileNumber", mobileNumber));
                break;

            default:
                break;
    }
    }
}
