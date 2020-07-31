package com.sample.shetkarisahayogfarmer.DashboardActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sample.shetkarisahayogfarmer.ComplaintHelperClass;
import com.sample.shetkarisahayogfarmer.DashboardActivity;
import com.sample.shetkarisahayogfarmer.R;
import com.sample.shetkarisahayogfarmer.UserHelperClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ComplaintActivity extends AppCompatActivity {
    private FirebaseRecyclerOptions<ComplaintHelperClass> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<ComplaintHelperClass, ComplaintViewHolder> firebaseRecyclerAdapter;
    RecyclerView recyclerView;
    EditText addComplaint, complaintAgainst;
    TextView textViewDate;
    SimpleDateFormat formatter;
    Date date;
    String mobileNumber;
    long madeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        mobileNumber = getIntent().getStringExtra("mobileNumber");

        addComplaint = findViewById(R.id.editText_complaint_text);
        complaintAgainst = findViewById(R.id.editText_complaint_aggId);
        textViewDate = findViewById(R.id.textView_date);

        addDate();

        recyclerView = findViewById(R.id.recyclerview_complaint);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<ComplaintHelperClass>().setQuery(FirebaseDatabase.getInstance().getReference("Complaints"), ComplaintHelperClass.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ComplaintHelperClass, ComplaintViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position, @NonNull ComplaintHelperClass model) {
                holder.textViewFarmerId.setText(model.getComplaintMade() + " Id : " + model.getMadeId());
                holder.textViewDate.setText(model.getDate());
                holder.textViewAggregatorId.setText("Complaint against " + model.getComplaintAgainst() + " : " + model.getAgainstId());
                holder.textViewComplaintText.setText("Complaint :\n" + model.getComplaint());
            }

            @NonNull
            @Override
            public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_row_item, parent, false);
                return new ComplaintViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        findViewById(R.id.imageView_back_complaints).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("mobileNumber"+ mobileNumber);
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class).putExtra("mobileNumber", mobileNumber));
            }
        });
    }

    private void addDate() {
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = new Date();
        textViewDate.setText(" " + formatter.format(date));
    }

    private Boolean validateComplaintAggId() {
        String val = complaintAgainst.getText().toString();
        if (val.isEmpty()) {
            complaintAgainst.setError("Please write Aggregator Id against you are writing complaint");
            return false;
        } else {
            complaintAgainst.setError(null);
            return true;
        }
    }

    private Boolean validateComplaint() {
        String val = addComplaint.getText().toString();
        if (val.isEmpty()) {
            addComplaint.setError("Please write your complaint");
            return false;
        } else {
            addComplaint.setError(null);
            return true;
        }
    }

    public void OnAddComplaint(View view) {

        if (!validateComplaint() | !validateComplaintAggId()) {
            //Do nothing
        } else {
            FirebaseDatabase.getInstance().getReference("Farmer").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    madeId = Long.parseLong(dataSnapshot.child(mobileNumber).child("applicationID").getValue().toString());
                    String complaint = addComplaint.getText().toString();
                    String currentDate = formatter.format(date);
                    long againstId = Long.parseLong(complaintAgainst.getText().toString());
                    ComplaintHelperClass helperClass = new ComplaintHelperClass(complaint, currentDate, "Farmer", "Aggregator", madeId, againstId);
                    FirebaseDatabase.getInstance().getReference("Complaints").child(mobileNumber).setValue(helperClass);

                    addComplaint.setText(" ");
                    complaintAgainst.setText(" ");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }
    }
}
