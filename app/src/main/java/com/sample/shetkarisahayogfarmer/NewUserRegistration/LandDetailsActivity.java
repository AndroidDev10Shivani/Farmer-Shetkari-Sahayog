package com.sample.shetkarisahayogfarmer.NewUserRegistration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.sample.shetkarisahayogfarmer.DashboardActivities.MyDealsActivity;
import com.sample.shetkarisahayogfarmer.R;

public class LandDetailsActivity extends AppCompatActivity {
    TextInputLayout regBankAccNumber, regIFSCCode, regAadharCard, regPanCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_details);

        findViewById(R.id.imageView_landDocsUpload1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,23);
            }
        });

        findViewById(R.id.imageView_landDocsUpload2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,23);
            }
        });

        findViewById(R.id.imageView_landDocsUpload3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,23);
            }
        });

        findViewById(R.id.imageView_landDocsUpload4).setOnClickListener(new View.OnClickListener() {
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

    public void onPreviousButton(View view) {
        onBackPressed();
    }

    public void onNextButton(View view) {
        startActivity(new Intent(getApplicationContext(), BankDetailsActivity.class));
    }
}