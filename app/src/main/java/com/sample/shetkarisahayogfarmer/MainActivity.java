package com.sample.shetkarisahayogfarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner_language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_language = findViewById(R.id.spinner_language);
        final List<String> languageList = new ArrayList<>();
        languageList.add(0, "Choose Language");
        languageList.add("English");
        languageList.add("मराठी");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, languageList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_language.setAdapter(arrayAdapter);
        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Language")) {
                    //Do nothing
                } else {
                    String Language = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onButtonContinue(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class ));
    }
}