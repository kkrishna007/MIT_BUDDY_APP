package com.example.mitbuddyapp;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class ClassTimeTableActivity extends AppCompatActivity {

    private Spinner branchSpinner;
    private Spinner sectionSpinner;
    private Button showButton;
    private ImageView timetableImageView;
    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time_table);

        branchSpinner = findViewById(R.id.spinner_branch);
        sectionSpinner = findViewById(R.id.spinner_section);
        showButton = findViewById(R.id.btn_show);
        timetableImageView = findViewById(R.id.timetableImageView);
        assetManager = getAssets();

        // ArrayAdapter for branch dropdown
        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(
                this, R.array.branch_options, android.R.layout.simple_spinner_item);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        // ArrayAdapter for section dropdown
        ArrayAdapter<CharSequence> sectionAdapter = ArrayAdapter.createFromResource(
                this, R.array.section_options, android.R.layout.simple_spinner_item);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(sectionAdapter);

        // Show button click listener
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branch = branchSpinner.getSelectedItem().toString();
                String section = sectionSpinner.getSelectedItem().toString();
                String imageName = branch.toLowerCase() + "_" + section.toLowerCase() + ".jpg";
                try {
                    InputStream inputStream = assetManager.open(imageName);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    timetableImageView.setImageBitmap(bitmap);
                    timetableImageView.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Image not found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
