package com.example.mitbuddyapp;

import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindFaculty extends AppCompatActivity {

    private EditText searchBar;
    private Button searchButton;
    private ListView resultsListView;
    private WebView webView;

    private FacultyDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_faculty);

        searchBar = findViewById(R.id.searchbar);
        searchButton = findViewById(R.id.searchButton);
        resultsListView = findViewById(R.id.results);
        webView = findViewById(R.id.webView);

        dbHelper = new FacultyDbHelper(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFaculty();
            }
        });

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showFacultyDetails(position);
            }
        });
    }

    private void searchFaculty() {
        String searchTerm = searchBar.getText().toString().trim();
        if (!searchTerm.isEmpty()) {
            Cursor cursor = dbHelper.searchFaculty(searchTerm);
            if (cursor != null && cursor.getCount() > 0) {
                showSearchResults(cursor);
            } else {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSearchResults(Cursor cursor) {
        String[] fromColumns = {"name", "designation", "chamber"};
        int[] toViews = {R.id.nameTextView, R.id.designationTextView, R.id.chamberTextView};

        // Use a custom ArrayAdapter with the layout resource ID
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.faculty_list_item, // Use the layout resource ID
                cursor,
                fromColumns,
                toViews,
                0
        );
        resultsListView.setAdapter(adapter);
    }


    private void showFacultyDetails(int position) {
        Cursor cursor = (Cursor) resultsListView.getItemAtPosition(position);
        String link = cursor.getString(cursor.getColumnIndex("link"));
        webView.loadUrl(link);
    }
}
