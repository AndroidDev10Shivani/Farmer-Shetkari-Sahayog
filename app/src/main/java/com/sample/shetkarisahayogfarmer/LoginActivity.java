package com.sample.shetkarisahayogfarmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText registeredMobileNumber, verifyOTP;
    String verificationCodeBySystem, mobileNumber, name;
    long applicationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registeredMobileNumber = findViewById(R.id.editText_RegisteredMobileNum);

        findViewById(R.id.textView_NewUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });
    }

    private Boolean validateMobileNumber() {
        String val = registeredMobileNumber.getText().toString();
        String pattern = "(0/91)?[7-9][0-9]{9}";
        if (val.isEmpty()) {
            registeredMobileNumber.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            registeredMobileNumber.setError("Invalid mobile number");
            return false;
        } else {
            registeredMobileNumber.setError(null);
            return true;
        }
    }

    public void buttonGenerateOTP(View view) {
        if (!validateMobileNumber()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        mobileNumber = registeredMobileNumber.getText().toString().trim();

        Query checkUser = FirebaseDatabase.getInstance().getReference("Farmer").orderByChild("mobileNumber").equalTo(mobileNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    registeredMobileNumber.setError(null);

                    String mobileNumberFromDB = dataSnapshot.child(mobileNumber).child("mobileNumber").getValue(String.class);

                    if (mobileNumberFromDB.equals(mobileNumber)) {
                        Toast.makeText(LoginActivity.this, "Validating your mobile number..", Toast.LENGTH_SHORT).show();
                        name = dataSnapshot.child(mobileNumber).child("name").getValue(String.class);
                        applicationID = Long.parseLong(dataSnapshot.child(mobileNumber).child("applicationID").getValue().toString());
                        sendVerificationCodeToUser(mobileNumber);
                    } else {
                        registeredMobileNumber.setError("Invalid Mobile Number");
                        registeredMobileNumber.requestFocus();
                    }
                } else {
                    registeredMobileNumber.setError("Entered Mobile number is not registered");
                    registeredMobileNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sendVerificationCodeToUser(String mobileNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobileNumber, // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,     // Unit of timeout
                TaskExecutors.MAIN_THREAD, // Activity (for callback binding)
                mCallbacks);         // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        //other device
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBySystem = s;
        }

        //same device
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredential(credential);
    }

    private void signInTheUserByCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Successfully verified and Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    intent.putExtra("mobileNumber", mobileNumber);
                    intent.putExtra("name", name);
                    intent.putExtra("applicationID", applicationID);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "signInTheUserByCredential :" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void buttonLogIn(View view) {
        String code = verifyOTP.getText().toString();
        if (code.isEmpty() || code.length() < 6) {
            verifyOTP.setError("Wrong OTP...");
            verifyOTP.requestFocus();
            return;
        }
        verifyCode(code);
    }
}
