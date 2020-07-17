package com.sample.shetkarisahayogfarmer.DashboardActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sample.shetkarisahayogfarmer.DashboardActivity;
import com.sample.shetkarisahayogfarmer.R;

public class CropPricesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prices);

        findViewById(R.id.imageView_back_prices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        });
    }
}