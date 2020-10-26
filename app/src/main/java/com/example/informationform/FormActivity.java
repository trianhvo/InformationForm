package com.example.informationform;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {
    public Button datePickerButton, submit;
    public TextView valtidationNotice;

    DatePickerDialog.OnDateSetListener date;
    Calendar calendar = Calendar.getInstance();
    EditText birthdayEditText, firstNameEditText, lastNameEditText, emailEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        birthdayEditText = findViewById(R.id.birthday);
        firstNameEditText = findViewById(R.id.fistName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        valtidationNotice = findViewById(R.id.validationNotice);

        submit = findViewById(R.id.submit);
        datePickerButton = findViewById(R.id.datePickerButton);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormActivity.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNameEditText.getText().toString().matches("")) {
                    String currtext = valtidationNotice.getText().toString();
                    currtext += "Please Enter Your First Name \n";
                    valtidationNotice.setText(currtext);
                }
                if (lastNameEditText.getText().toString().matches("")) {
                    String currtext = valtidationNotice.getText().toString();
                    currtext += "Please Enter Your Last Name \n";
                    valtidationNotice.setText(currtext);
                }
                if (birthdayEditText.getText().toString().matches("")) {
                    String currtext = valtidationNotice.getText().toString();
                    currtext += "Please Enter Your Birthday \n";
                    valtidationNotice.setText(currtext);
                }
                if (emailEditText.getText().toString().matches("")) {
                    String currtext = valtidationNotice.getText().toString();
                    currtext += "Please Enter Your Email \n";
                    valtidationNotice.setText(currtext);
                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "YYYY-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthdayEditText.setText(sdf.format(calendar.getTime()));
    }
}
