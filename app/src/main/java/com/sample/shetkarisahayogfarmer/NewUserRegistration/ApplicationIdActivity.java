package com.sample.shetkarisahayogfarmer.NewUserRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sample.shetkarisahayogfarmer.LoginActivity;
import com.sample.shetkarisahayogfarmer.R;

public class ApplicationIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_id);
    }

    public void onPreviousButton(View view) {
        onBackPressed();
    }

    public void onConfirmButton(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}