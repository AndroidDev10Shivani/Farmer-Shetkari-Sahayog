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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.sample.shetkarisahayogfarmer.DashboardActivity;
import com.sample.shetkarisahayogfarmer.DemandHelperClass;
import com.sample.shetkarisahayogfarmer.R;

public class MyDealsActivity extends AppCompatActivity {
    private FirebaseRecyclerOptions<DemandHelperClass> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<DemandHelperClass, AggregatorViewHolder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    String mobileNumber;
//AcceptedDeals
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deals);

        mobileNumber = getIntent().getStringExtra("mobileNumber");

        recyclerView = findViewById(R.id.recyclerview_myDeals);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<DemandHelperClass>().setQuery(FirebaseDatabase.getInstance().getReference("AcceptedDeals"), DemandHelperClass.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DemandHelperClass, AggregatorViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull final AggregatorViewHolder holder, int position, @NonNull final DemandHelperClass model) {
                holder.textViewDealDate.setText(model.getDate());
                holder.textViewDealName.setText("Name : " + model.getName());
                holder.textViewDealAggId.setText("Id : " + "" + model.getApplicationID());
                holder.textViewDealFarmProduct.setText("Farm Product : " + model.getFarmProduct());
                holder.textViewDealQuantity.setText("Quantity Requirement : " + model.getQuantityRequirement());
            }

            @NonNull
            @Override
            public AggregatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_deal_row_item, parent, false);
                return new AggregatorViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);


        findViewById(R.id.imageView_back_deals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        });
    }
}



