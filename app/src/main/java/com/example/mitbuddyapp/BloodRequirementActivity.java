package com.example.mitbuddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BloodRequirementActivity extends AppCompatActivity {

    private LinearLayout cardContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_requirement);

        cardContainer = findViewById(R.id.cardContainer);

        // Set up the click listener for the floating action button
        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddBloodRequirementDialog();
            }
        });
    }

    // Method to show the dialog for adding a new blood requirement
    private void showAddBloodRequirementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_blood_requirement, null);
        builder.setView(dialogView);

        EditText headlineEditText = dialogView.findViewById(R.id.headlineEditText);
        EditText bloodTypeEditText = dialogView.findViewById(R.id.bloodTypeEditText);
        EditText dateEditText = dialogView.findViewById(R.id.dateEditText);
        EditText contactEditText = dialogView.findViewById(R.id.contactEditText);
        Button submitButton = dialogView.findViewById(R.id.submitButton);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Handle click event for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String headline = headlineEditText.getText().toString();
                String bloodType = bloodTypeEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String contact = contactEditText.getText().toString();

                addBloodRequirementCard(headline, bloodType, date, contact);

                dialog.dismiss();
            }
        });
    }

    // Method to add a new blood requirement card
    private void addBloodRequirementCard(String headline, String bloodType, String date, String contact) {
        View cardView = LayoutInflater.from(this).inflate(R.layout.blood_requirement_card, null);

        TextView headlineTextView = cardView.findViewById(R.id.headlineTextView);
        TextView bloodTypeTextView = cardView.findViewById(R.id.bloodTypeTextView);
        TextView dateTextView = cardView.findViewById(R.id.dateTextView);
        TextView contactTextView = cardView.findViewById(R.id.contactTextView);

        headlineTextView.setText(headline);
        bloodTypeTextView.setText("BLOOD TYPE: " + bloodType);
        dateTextView.setText("DATE: " + date);
        contactTextView.setText("CONTACT: " + contact);

        cardContainer.addView(cardView);
    }
}

