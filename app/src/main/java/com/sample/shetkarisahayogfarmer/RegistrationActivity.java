package com.sample.shetkarisahayogfarmer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.sample.shetkarisahayogfarmer.ApplicationIdActivity;
import com.sample.shetkarisahayogfarmer.R;
import com.sample.shetkarisahayogfarmer.ScanActivity;
import com.sample.shetkarisahayogfarmer.UserHelperClass;

import java.util.Calendar;
import java.util.Date;

public class RegistrationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView regGender;
    EditText regName, regMiddleName, regMobileNumber, regDob, regAddress, regPincode, regLandHolding, regLandType, regBankAccNumber, regIFSCCode;
    EditText doc1, doc2, doc3, doc4, regAadharCard, regPanCard;
    EditText regCountry, regState, regDistrict, regCity, regProposedCrops;
    RadioGroup regGGender;
    RadioButton genderMale, genderFemale;
    TextView applicationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regName = findViewById(R.id.editText_name);
        regMiddleName = findViewById(R.id.editText_middleName);
        regDob = findViewById(R.id.editText_dob);
        regMobileNumber = findViewById(R.id.editText_mobileNumber);
        regGGender = findViewById(R.id.radioGroup_gender);
        regGender = findViewById(R.id.textView_gender);
        genderMale = findViewById(R.id.radioButton_male);
        genderFemale = findViewById(R.id.radioButton_female);

        regAddress = findViewById(R.id.editText_address);
        regPincode = findViewById(R.id.editText_pincode);
        regCountry = findViewById(R.id.editText_country);
        regState = findViewById(R.id.editText_state);
        regDistrict = findViewById(R.id.editText_district);
        regCity = findViewById(R.id.editText_city);

        regLandHolding = findViewById(R.id.editText_landHolding);
        regLandType = findViewById(R.id.editText_landType);
        regProposedCrops = findViewById(R.id.editText_proposedCrops);

        doc1 = findViewById(R.id.editText_landDocs1);
        doc2 = findViewById(R.id.editText_landDocs2);
        doc3 = findViewById(R.id.editText_landDocs3);
        doc4 = findViewById(R.id.editText_landDocs4);

        regBankAccNumber = findViewById(R.id.editText_bankAccountNumber);
        regIFSCCode = findViewById(R.id.editText_ifscCode);
        regAadharCard = findViewById(R.id.editText_aadharCard);
        regPanCard = findViewById(R.id.editText_panCard);

        applicationId = findViewById(R.id.textView_applicationId);

        findViewById(R.id.imageView_calender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        findViewById(R.id.imageView_landDocsUpload1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 23);
            }
        });

        findViewById(R.id.imageView_landDocsUpload2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 23);
            }
        });

        findViewById(R.id.imageView_landDocsUpload3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 23);
            }
        });

        findViewById(R.id.imageView_landDocsUpload4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 23);
            }
        });

        findViewById(R.id.imageView_aadharCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 23);
            }
        });

        findViewById(R.id.imageView_panCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 23);
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
        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        regDob.setText(date);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        startActivity(new Intent(getApplicationContext(), ScanActivity.class).putExtra("Image", bitmap));
    }

    public void onPreviousButton(View view) {
        onBackPressed();
    }

    private Boolean validateName() {
        String val = regName.getText().toString().trim();
        if (val.isEmpty()) {
            regName.setError("Please enter name");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateMiddleName() {
        String val = regMiddleName.getText().toString().trim();
        if (val.isEmpty()) {
            regMiddleName.setError("Please enter middle name");
            return false;
        } else {
            regMiddleName.setError(null);
            return true;
        }
    }

    private Boolean validateGender() {
        if (regGGender.getCheckedRadioButtonId() == -1) {
            genderMale.setError("Please select gender");
            return false;
        } else {
            return true;
        }
    }

    private Boolean validateDob() {
        String val = regDob.getText().toString();
        if (val.isEmpty()) {
            regDob.setError("Please enter date of birth");
            return false;
        } else {
            regDob.setError(null);
            return true;
        }
    }

    private Boolean validateMobileNumber() {
        String val = regMobileNumber.getText().toString();
        String pattern = "(0/91)?[7-9][0-9]{9}";
        if (val.isEmpty()) {
            regMobileNumber.setError("Please enter mobile number");
            return false;
        } else if (!val.matches(pattern)) {
            regMobileNumber.setError("Invalid mobile number");
            return false;
        } else {
            regMobileNumber.setError(null);
            return true;
        }
    }

    private Boolean validateAddress() {
        String val = regAddress.getText().toString();
        if (val.isEmpty()) {
            regAddress.setError("Please enter address");
            return false;
        } else {
            regAddress.setError(null);
            return true;
        }
    }

    private Boolean validateCountry() {
        String val = regCountry.getText().toString();
        if (val.isEmpty()) {
            regCountry.setError("Please enter country");
            return false;
        } else {
            regCountry.setError(null);
            return true;
        }
    }

    private Boolean validateState() {
        String val = regState.getText().toString();
        if (val.isEmpty()) {
            regState.setError("Please enter state");
            return false;
        } else {
            regState.setError(null);
            return true;
        }
    }

    private Boolean validateDistrict() {
        String val = regDistrict.getText().toString();
        if (val.isEmpty()) {
            regDistrict.setError("Please enter district");
            return false;
        } else {
            regDistrict.setError(null);
            return true;
        }
    }

    private Boolean validateCity() {
        String val = regCity.getText().toString();
        if (val.isEmpty()) {
            regCity.setError("Please enter city");
            return false;
        } else {
            regCity.setError(null);
            return true;
        }
    }

    private Boolean validatePincode() {
        String val = regPincode.getText().toString();
        if (val.isEmpty()) {
            regPincode.setError("Please enter pincode");
            return false;
        } else {
            regPincode.setError(null);
            return true;
        }
    }

    private Boolean validateLandHolding() {
        String val = regLandHolding.getText().toString();
        if (val.isEmpty()) {
            regLandHolding.setError("Field cannot be empty");
            return false;
        } else {
            regLandHolding.setError(null);
            return true;
        }
    }

    private Boolean validateLandType() {
        String val = regLandType.getText().toString();
        if (val.isEmpty()) {
            regLandType.setError("Field cannot be empty");
            return false;
        } else {
            regLandType.setError(null);
            return true;
        }
    }

    private Boolean validateProposedCrops() {
        String val = regProposedCrops.getText().toString();
        if (val.isEmpty()) {
            regProposedCrops.setError("Field cannot be empty");
            return false;
        } else {
            regProposedCrops.setError(null);
            return true;
        }
    }

    private Boolean validateDoc1() {
        String val = doc1.getText().toString();
        if (val.isEmpty()) {
            doc1.setError("Field cannot be empty");
            return false;
        } else {
            doc1.setError(null);
            return true;
        }
    }

    private Boolean validateDoc2() {
        String val = doc2.getText().toString();
        if (val.isEmpty()) {
            doc2.setError("Field cannot be empty");
            return false;
        } else {
            doc2.setError(null);
            return true;
        }
    }

    private Boolean validateDoc3() {
        String val = doc3.getText().toString();
        if (val.isEmpty()) {
            doc3.setError("Field cannot be empty");
            return false;
        } else {
            doc3.setError(null);
            return true;
        }
    }

    private Boolean validateDoc4() {
        String val = doc4.getText().toString();
        if (val.isEmpty()) {
            doc4.setError("Field cannot be empty");
            return false;
        } else {
            doc4.setError(null);
            return true;
        }
    }

    private Boolean validateBankAccountNumber() {
        String val = regBankAccNumber.getText().toString();
        String pattern = "[0-9]{9,18}$";
        if (val.isEmpty()) {
            regBankAccNumber.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            regBankAccNumber.setError("Invalid bank account number");
            return false;
        } else {
            regBankAccNumber.setError(null);
            return true;
        }
    }

    private Boolean validateIFSCCode() {
        String val = regIFSCCode.getText().toString();
        String pattern = "[A-Z|a-z]{4}[0][A-Z0-9]{6}$";
        if (val.isEmpty()) {
            regIFSCCode.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            regIFSCCode.setError("Invalid IFSC code");
            return false;
        } else {
            regIFSCCode.setError(null);
            return true;
        }
    }

    private Boolean validateAadharCard() {
        String val = regAadharCard.getText().toString();
        if (val.isEmpty()) {
            regAadharCard.setError("Please scan aadhar card");
            return false;
        } else {
            regAadharCard.setError(null);
            return true;
        }
    }

    private Boolean validatePanCard() {
        String val = regPanCard.getText().toString();
        if (val.isEmpty()) {
            regPanCard.setError("Please scan pan card");
            return false;
        } else {
            regPanCard.setError(null);
            return true;
        }
    }

    public void onNextButton(View view) {
        if (!validateName() | !validateMiddleName() | !validateGender() | !validateDob() | !validateMobileNumber() | !validateAddress() | !validateCountry() | !validateState() | !validateDistrict() | !validateCity() | !validatePincode() | !validateLandHolding() | !validateLandType() | !validateProposedCrops() | !validateBankAccountNumber() | !validateIFSCCode()) {
            //Do nothing
        } else {
            String name = regName.getText().toString();
            String[] nameArray = name.split(" ");
            String firstName = nameArray[0];
            firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);
            String lastName = name.substring(name.indexOf(" ") + 1);
            lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);
            name = firstName + " " + lastName;

            String middleName = regMiddleName.getText().toString();
            middleName = Character.toUpperCase(middleName.charAt(0)) + middleName.substring(1);

            String gender = (genderMale.isChecked()) ? "Male" : "Female";
            String dob = regDob.getText().toString();
            String mobileNumber = regMobileNumber.getText().toString();

            String address = regAddress.getText().toString();
            String country = regCountry.getText().toString();
            String state = regState.getText().toString();
            String district = regDistrict.getText().toString();
            String city = regCity.getText().toString();
            String pincode = regPincode.getText().toString();

            String landHolding = regLandHolding.getText().toString();
            String landType = regLandType.getText().toString();
            String proposedCrops = regProposedCrops.getText().toString();

            String bankAccNumber = regBankAccNumber.getText().toString();
            String ifscCode = regIFSCCode.getText().toString();

            long id = (long)(new Date().getTime() / 1000);

            UserHelperClass helperClass = new UserHelperClass(name, middleName, gender, dob, mobileNumber, address, country, state, district, city, pincode, landHolding, landType, proposedCrops, bankAccNumber, ifscCode, id);
            FirebaseDatabase.getInstance().getReference("Farmer").child(mobileNumber).setValue(helperClass);

            startActivity(new Intent(getApplicationContext(), ApplicationIdActivity.class).putExtra("ID", id));
        }
    }
}