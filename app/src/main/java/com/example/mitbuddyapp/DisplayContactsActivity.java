package com.example.mitbuddyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        // Get category passed from PhoneDirectoryActivity
        String category = getIntent().getStringExtra("category");

        // Initialize contacts list
        ArrayList<String> contactsList = new ArrayList<>();

        // Initialize database helper
        ContactsDatabaseHelper dbHelper = new ContactsDatabaseHelper(this);

        // Get readable database
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define columns to be retrieved
        String[] projection = {"name", "phoneNumber"};

        // Define selection criteria
        String selection = "category = ?";
        String[] selectionArgs = {category};

        // Query the database
        Cursor cursor = db.query("contacts", projection, selection, selectionArgs, null, null, null);

        // Check if cursor is valid and contains data
        if (cursor != null && cursor.getCount() > 0) {
            // Iterate through cursor to retrieve contacts
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                contactsList.add(name + ": " + phoneNumber);
            }
            cursor.close();

            // Display contacts in ListView
            ListView listView = findViewById(R.id.contactsListView);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
            listView.setAdapter(adapter);

            // Set click listener for ListView items
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get phone number from selected item
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    String[] parts = selectedItem.split(": ");
                    String phoneNumber = parts[1].trim();

                    // Open dialer with the phone number
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
            });
        } else {
            // Display toast message if no contacts found for the category
            Toast.makeText(this, "No contacts found for this category", Toast.LENGTH_SHORT).show();
        }

        // Close the database
        db.close();
    }
}
