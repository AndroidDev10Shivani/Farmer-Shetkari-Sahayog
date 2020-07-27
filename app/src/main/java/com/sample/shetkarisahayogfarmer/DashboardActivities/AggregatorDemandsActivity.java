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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sample.shetkarisahayogfarmer.DashboardActivity;
import com.sample.shetkarisahayogfarmer.DemandHelperClass;
import com.sample.shetkarisahayogfarmer.R;
import com.sample.shetkarisahayogfarmer.DemandHelperClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AggregatorDemandsActivity extends AppCompatActivity {
    private FirebaseRecyclerOptions<DemandHelperClass> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<DemandHelperClass, AggregatorViewHolder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregator_demand);
        mobileNumber = getIntent().getStringExtra("mobileNumber");

        recyclerView = findViewById(R.id.recyclerview_aggDemand);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<DemandHelperClass>().setQuery(FirebaseDatabase.getInstance().getReference("AggregatorDemands"), DemandHelperClass.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DemandHelperClass, AggregatorViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull final AggregatorViewHolder holder, int position, @NonNull final DemandHelperClass model) {
                holder.textViewDemandDate.setText(model.getDate());
                holder.textViewDemandName.setText("Name : " + model.getName());
                holder.textViewDemandAggId.setText("Id : " + "" + model.getApplicationID());
                holder.textViewDemandFarmProduct.setText("Farm Product : " + model.getFarmProduct());
                holder.textViewDemandQuantity.setText("Quantity Requirement : " + model.getQuantityRequirement());
                holder.textViewDemandAddress.setText("Address :\n" + model.getAddress());

                holder.buttonAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DemandHelperClass helperClass = new DemandHelperClass(model.getName(), model.getAddress(), model.getDate(), model.getFarmProduct(), model.getQuantityRequirement(), model.getApplicationID());
                        FirebaseDatabase.getInstance().getReference("AcceptedDeals").child(mobileNumber).setValue(helperClass);
                        Toast.makeText(AggregatorDemandsActivity.this, "Successfully accepted the demand request", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public AggregatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aggregator_demand_row_item, parent, false);
                return new AggregatorViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        findViewById(R.id.imageView_back_aggregator_demand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class).putExtra("mobileNumber", mobileNumber));
            }
        });
    }
}