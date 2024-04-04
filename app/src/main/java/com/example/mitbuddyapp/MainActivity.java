package com.example.mitbuddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Method to open Attendance Tracker Activity
    public void openAttendanceTrackerActivity(View view) {
        Intent intent = new Intent(this, AttendanceTrackerActivity.class);
        startActivity(intent);
    }

    // Method to open Important Notices Activity
    public void openImportantNoticesActivity(View view) {
        Intent intent = new Intent(this, ImportantNoticesActivity.class);
        startActivity(intent);
    }

    // Method to open Class Time Table Activity
    public void openClassTimeTableActivity(View view) {
        Intent intent = new Intent(this, ClassTimeTableActivity.class);
        startActivity(intent);
    }

    // Method to open GPA Calculator Activity
    public void openGPACalculatorActivity(View view) {
        Intent intent = new Intent(this, GPACalculatorActivity.class);
        startActivity(intent);
    }

    // Method to open Event Registration Activity
    public void openEventRegistrationActivity(View view) {
        Intent intent = new Intent(this, EventRegistrationActivity.class);
        startActivity(intent);
    }

    // Method to open Blood Requirement Activity
    public void openBloodRequirementActivity(View view) {
        Intent intent = new Intent(this, BloodRequirementActivity.class);
        startActivity(intent);
    }

    // Method to open Phone Directory Activity
    public void openPhoneDirectoryActivity(View view) {
        Intent intent = new Intent(this, PhoneDirectoryActivity.class);
        startActivity(intent);
    }
}
