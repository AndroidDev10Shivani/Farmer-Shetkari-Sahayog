package com.sample.shetkarisahayogfarmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sample.shetkarisahayogfarmer.DashboardActivities.AggregatorActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.AggregatorDealsActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.ComplaintActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.CropPricesActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.IdentityActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.MyDealsActivity;
import com.sample.shetkarisahayogfarmer.DashboardActivities.UpdateCropsActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));

        findViewById(R.id.button_identity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IdentityActivity.class));
            }
        });
        findViewById(R.id.button_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        findViewById(R.id.aggregators).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AggregatorActivity.class));
            }
        });

        findViewById(R.id.crop_prices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CropPricesActivity.class));
            }
        });

        findViewById(R.id.update_crops).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UpdateCropsActivity.class));
            }
        });

        findViewById(R.id.aggregators_deals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AggregatorDealsActivity.class));
            }
        });

        findViewById(R.id.my_deals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyDealsActivity.class));
            }
        });

        findViewById(R.id.complaints).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ComplaintActivity.class));
            }
        });

    }
}
