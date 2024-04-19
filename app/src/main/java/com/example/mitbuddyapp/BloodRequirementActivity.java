package com.example.mitbuddyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.widget.TextView;



import java.util.List;

public class BloodRequirementActivity extends AppCompatActivity {

    private LinearLayout cardContainer;
    private BloodDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_requirement);

        dbHelper = new BloodDBHelper(this);
        cardContainer = findViewById(R.id.cardContainer);

        // Set up the click listener for the floating action button
        FloatingActionButton fab = findViewById(R.id.addButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddBloodRequirementDialog();
            }
        });

        // Load existing blood requirements from the database
        loadBloodRequirementsFromDatabase();
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

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String headline = headlineEditText.getText().toString();
                String bloodType = bloodTypeEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String contact = contactEditText.getText().toString();

                // Add the new blood requirement to the database
                dbHelper.addBloodRequirement(headline, bloodType, date, contact);

                // Reload blood requirements from the database and update UI
                loadBloodRequirementsFromDatabase();
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to load existing blood requirements from the database and display them
    private void loadBloodRequirementsFromDatabase() {
        cardContainer.removeAllViews();

        List<BloodRequirement> bloodRequirements = dbHelper.getAllBloodRequirements();

        for (BloodRequirement requirement : bloodRequirements) {
            View cardView = LayoutInflater.from(this).inflate(R.layout.blood_requirement_card, null);

            TextView headlineTextView = cardView.findViewById(R.id.headlineTextView);
            TextView bloodTypeTextView = cardView.findViewById(R.id.bloodTypeTextView);
            TextView dateTextView = cardView.findViewById(R.id.dateTextView);
            TextView contactTextView = cardView.findViewById(R.id.contactTextView);

            headlineTextView.setText(requirement.getHeadline());
            bloodTypeTextView.setText("BLOOD TYPE: " + requirement.getBloodType());
            dateTextView.setText("DATE: " + requirement.getDate());
            contactTextView.setText("CONTACT: " + requirement.getContact());

            cardContainer.addView(cardView);
        }
    }
}
