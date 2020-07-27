package com.sample.shetkarisahayogfarmer.DashboardActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sample.shetkarisahayogfarmer.DashboardActivity;
import com.sample.shetkarisahayogfarmer.R;
import com.sample.shetkarisahayogfarmer.UserHelperClass;

public class AggregatorActivity extends AppCompatActivity {
    private FirebaseRecyclerOptions<UserHelperClass> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<UserHelperClass, AggregatorViewHolder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregators);
        mobileNumber = getIntent().getStringExtra("mobileNumber");

        recyclerView = findViewById(R.id.recyclerview_aggregator);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<UserHelperClass>().setQuery(FirebaseDatabase.getInstance().getReference("Aggregator"), UserHelperClass.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserHelperClass, AggregatorViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull AggregatorViewHolder holder, int position, @NonNull UserHelperClass model) {
                holder.textViewName.setText("Name : " + model.getName());
                holder.textViewAppId.setText("Id : " + "" + model.getApplicationID());
                holder.textViewMobileNo.setText("Mobile Number : " + model.getMobileNumber());
                holder.textViewAddress.setText("Address :\n" + model.getAddress() + ", " + model.getDistrict() + ", " + model.getCity() + " - " + model.getPincode());
            }

            @NonNull
            @Override
            public AggregatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aggregators_row_item, parent, false);
                return new AggregatorViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        findViewById(R.id.imageView_back_aggregators).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class).putExtra("mobileNumber", mobileNumber));
            }
        });
    }
}