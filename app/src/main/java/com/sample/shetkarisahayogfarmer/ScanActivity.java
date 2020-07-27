package com.sample.shetkarisahayogfarmer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.shetkarisahayogfarmer.R;

public class ScanActivity extends AppCompatActivity {
    ImageView imageView;
    String USER, ROOT = "Farmer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        imageView = findViewById(R.id.imageView_camera);
        USER = getIntent().getStringExtra("USER");

        Intent i = getIntent();
        Bitmap bitmap = i.getParcelableExtra("Image");
        imageView.setImageBitmap(bitmap);
    }
}