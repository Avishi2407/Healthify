package com.example.healthify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextLTBFullName);
        ed2 = findViewById(R.id.editTextLTBAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextLTBContact);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonLTBBook);
        btnBack = findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees " + fees + "/-");

        // Set up click listeners for the date and time buttons
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePicker();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });
    }

    private void initDatePicker() {
        // Initialize date set listener for the DatePickerDialog
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2 + "/" + i1 + "/" + i);
            }
        };

        // Get current date
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog with initial values and set minimum date to tomorrow
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                // Your logic to handle the selected time goes here
                // For example, you can update a text view with the selected time
                // timeButton.setText(hourOfDay + ":" + minute);
                timeButton.setText(i + ":" + i1);
            }
        };

        // Get current time
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // Create TimePickerDialog with initial values and 24-hour format
        timePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
}
