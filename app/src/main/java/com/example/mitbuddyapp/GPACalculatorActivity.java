package com.example.mitbuddyapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GPACalculatorActivity extends AppCompatActivity {

    private List<String> branchesList;
    private List<String> semestersList;
    private Spinner branchSpinner;
    private Spinner semesterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);

        branchSpinner = findViewById(R.id.branchSpinner);
        semesterSpinner = findViewById(R.id.semesterSpinner);

        // Load JSON data
        loadJSONData();

        // Setup branch spinner
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchesList);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        // Setup semester spinner
        setupSemesterSpinner(0);
    }

    private void loadJSONData() {
        branchesList = new ArrayList<>();
        semestersList = new ArrayList<>();

        try {
            InputStream is = getAssets().open("subject_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);

            // Get branches
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                branchesList.add(keys.next());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupSemesterSpinner(int position) {
        semestersList.clear();

        try {
            InputStream is = getAssets().open("subject_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);

            // Get semesters for the selected branch
            String selectedBranch = branchesList.get(position);
            JSONArray semestersArray = jsonObject.getJSONObject(selectedBranch).getJSONArray("semesters");
            for (int i = 0; i < semestersArray.length(); i++) {
                JSONObject semesterObject = semestersArray.getJSONObject(i);
                int semNumber = semesterObject.getInt("sem_number");
                semestersList.add("Semester " + semNumber);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        // Setup adapter for semester spinner
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semestersList);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);
    }
}
