package com.example.mitbuddyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class PhoneDirectoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_directory);

        // Find GridLayout
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Set click listeners for each card
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            // Set click listener for each card
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the ID of the clicked card
                    int id = v.getId();
                    // Find view by ID
                    View clickedView = findViewById(id);
                    // Determine which card was clicked and open corresponding activity
                    if (clickedView.getId() == R.id.autoservice) {
                        openDisplayContactsActivity("Auto Service");
                    } else if (clickedView.getId() == R.id.eateries) {
                        openDisplayContactsActivity("Eateries");
                    } else if (clickedView.getId() == R.id.emergency) {
                        openDisplayContactsActivity("Emergency");
                    } else if (clickedView.getId() == R.id.bikerental) {
                        openDisplayContactsActivity("Rent a bike");
                    } else if (clickedView.getId() == R.id.medical) {
                        openDisplayContactsActivity("Medical Services");
                    } else if (clickedView.getId() == R.id.hotels) {
                        openDisplayContactsActivity("Hotels");
                    } else if (clickedView.getId() == R.id.hod) {
                        openDisplayContactsActivity("Head of Departments");
                    } else if (clickedView.getId() == R.id.grocery) {
                        openDisplayContactsActivity("Grocery Store");
                    } else {
                        // Handle unknown card click
                        Toast.makeText(PhoneDirectoryActivity.this, "Unknown category", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // Method to open DisplayContactsActivity and pass category information
    private void openDisplayContactsActivity(String category) {
        Intent intent = new Intent(this, DisplayContactsActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
