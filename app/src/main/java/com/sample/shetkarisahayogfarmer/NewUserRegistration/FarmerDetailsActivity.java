package com.sample.shetkarisahayogfarmer.NewUserRegistration;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.sample.shetkarisahayogfarmer.DashboardActivities.MyDealsActivity;
import com.sample.shetkarisahayogfarmer.R;
import java.util.Calendar;

public class FarmerDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView regGender;
    TextInputLayout regName, regMiddleName, regMobileNumber, regDob;
    RadioGroup regGGender;
    RadioButton genderMale, genderFemale;
    String name, middleName, dob, gender;
    long mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_details);

        regName = findViewById(R.id.editText_name);
        regMiddleName = findViewById(R.id.editText_middleName);
        regDob = findViewById(R.id.editText_dob);
        regMobileNumber = findViewById(R.id.editText_mobileNumber);
        regGGender = findViewById(R.id.radioGroup_gender);
        regGender = findViewById(R.id.textView_gender);
        genderMale = findViewById(R.id.radioButton_male);
        genderFemale = findViewById(R.id.radioButton_female);

        findViewById(R.id.imageView_calender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       String date = dayOfMonth+"/"+month+"/"+year;
       regDob.getEditText().setText(date);
    }

    public void onPreviousButton(View view) {
        onBackPressed();
    }

    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateMiddleName() {
        String val = regMiddleName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regMiddleName.setError("Field cannot be empty");
            return false;
        } else {
            regMiddleName.setError(null);
            return true;
        }
    }

    private Boolean validateGender() {
        if (regGGender.getCheckedRadioButtonId() == -1) {
            regGender.setError("Please select gender");
            return false;
        } else {
            return true;
        }
    }

    private Boolean validateDob() {
        String val = regDob.getEditText().getText().toString();
        if (val.isEmpty()) {
            regDob.setError("Field cannot be empty");
            return false;
        } else {
            regDob.setError(null);
            return true;
        }
    }

    private Boolean validateMobileNumber() {
        String val = regMobileNumber.getEditText().getText().toString();
        String pattern = "(0/91)?[7-9][0-9]{9}";
        if (val.isEmpty()) {
            regMobileNumber.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            regMobileNumber.setError("Invalid mobile number");
            return false;
        } else {
            regMobileNumber.setError(null);
            regMobileNumber.setErrorEnabled(false);
            return true;
        }
    }

    public void onNextButton(View view) {
        if (!validateName() | !validateMiddleName() | !validateGender() | !validateDob() | !validateMobileNumber()) {
            //Do nothing
        } else {
            name = regName.getEditText().getText().toString();
            middleName = regMiddleName.getEditText().getText().toString();
            gender = (genderMale.isChecked()) ? "Male" : "Female";
            dob = regDob.getEditText().getText().toString();
            mobileNumber = Long.parseLong(regMobileNumber.getEditText().getText().toString());

            Toast.makeText(this, name + middleName + gender + dob + mobileNumber, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), CommunicationDetailsActivity.class));
        }
    }
}

