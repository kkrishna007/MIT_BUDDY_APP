package com.example.mitbuddyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.content.DialogInterface;
import android.view.ViewGroup;
import java.util.Locale;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;


public class AttendanceTrackerActivity extends AppCompatActivity {

    private SubjectCardDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_tracker);

        dbHelper = new SubjectCardDbHelper(this);

        FloatingActionButton addButton = findViewById(R.id.addSubjectButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSubjectPopup();
            }
        });

        loadSubjectCardsFromDatabase();
    }

    public ArrayList<SubjectCard> getAllSubjectCards() {
        ArrayList<SubjectCard> subjectCards = new ArrayList<>();
        // Retrieve the subject cards from the database and add them to the ArrayList
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String subjectName = cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NAME));
                int attended = cursor.getInt(cursor.getColumnIndex(COLUMN_ATTENDED));
                int missed = cursor.getInt(cursor.getColumnIndex(COLUMN_MISSED));
                int requirement = cursor.getInt(cursor.getColumnIndex(COLUMN_REQUIREMENT));
                double attendancePercentage = cursor.getDouble(cursor.getColumnIndex(COLUMN_ATTENDANCE_PERCENTAGE));
                SubjectCard subjectCard = new SubjectCard(id, subjectName, attended, missed, requirement, attendancePercentage);
                subjectCards.add(subjectCard);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return subjectCards;
    }

    private void loadSubjectCardsFromDatabase() {
        ArrayList<SubjectCard> subjectCards = dbHelper.getAllSubjectCards();
        for (SubjectCard card : subjectCards) {
            createSubjectCard(card);
        }
    }

    private void showAddSubjectPopup() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_add_subject, null);
        dialogBuilder.setView(dialogView);

        final EditText subjectNameEditText = dialogView.findViewById(R.id.subjectNameEditText);
        final EditText attendedEditText = dialogView.findViewById(R.id.attendedEditText);
        final EditText missedEditText = dialogView.findViewById(R.id.missedEditText);
        final EditText requirementEditText = dialogView.findViewById(R.id.requirementEditText);

        dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String subjectName = subjectNameEditText.getText().toString();
                int attended = Integer.parseInt(attendedEditText.getText().toString());
                int missed = Integer.parseInt(missedEditText.getText().toString());
                int requirement = Integer.parseInt(requirementEditText.getText().toString());

                // Calculate attendance percentage
                double attendancePercentage = (double) attended / (attended + missed) * 100;

                // Save subject card to database
                long id = dbHelper.insertSubjectCard(new SubjectCard(subjectName, attended, missed, requirement, attendancePercentage));

                // Dynamically create subject card
                createSubjectCard(new SubjectCard(id, subjectName, attended, missed, requirement, attendancePercentage));
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void createSubjectCard(SubjectCard card) {
        // Inflate subject card layout
        View subjectCardView = getLayoutInflater().inflate(R.layout.subject_card_layout, null);

        // Find views in subject card layout
        TextView subjectNameTextView = subjectCardView.findViewById(R.id.subjectNameTextView);
        TextView attendedClassesTextView = subjectCardView.findViewById(R.id.attendedClassesTextView);
        TextView missedClassesTextView = subjectCardView.findViewById(R.id.missedClassesTextView);
        TextView totalClassesTextView = subjectCardView.findViewById(R.id.totalClassesTextView);
        TextView attendancePercentageTextView = subjectCardView.findViewById(R.id.attendancePercentageTextView);
        Button addAttendButton = subjectCardView.findViewById(R.id.addAttendButton);
        Button addMissedButton = subjectCardView.findViewById(R.id.addMissedButton);
        Button deleteButton = subjectCardView.findViewById(R.id.deleteButton);
        ProgressBar progressBar = subjectCardView.findViewById(R.id.attendanceProgressBar); // Find the progress bar

        // Set text for subject card views
        subjectNameTextView.setText(card.getSubjectName());
        attendedClassesTextView.setText("Attended: " + card.getAttended());
        missedClassesTextView.setText("Missed: " + card.getMissed());
        totalClassesTextView.setText("Total: " + (card.getAttended() + card.getMissed()));
        attendancePercentageTextView.setText(String.format(Locale.getDefault(), "%.2f%%", card.getAttendancePercentage()));

        // Set progress for the progress bar
        progressBar.setProgress((int) card.getAttendancePercentage());

        // Change progress bar color based on attendance percentage
        if (card.getAttendancePercentage() < 75) {
            progressBar.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(this, R.color.red),
                    android.graphics.PorterDuff.Mode.SRC_IN
            );
        } else {
            progressBar.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(this, R.color.green),
                    android.graphics.PorterDuff.Mode.SRC_IN
            );
        }

        // Set onClickListeners for attend and missed buttons
        addAttendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment attended
                attendedClassesTextView.setText("Attended: " + (card.getAttended() + 1));
                card.setAttended(card.getAttended() + 1);
                updateAttendancePercentage(card, attendancePercentageTextView);
                totalClassesTextView.setText("Total: " + (card.getAttended() + card.getMissed()));
                progressBar.setProgress((int) ((double) card.getAttended() / (card.getAttended() + card.getMissed()) * 100)); // Update progress bar
                dbHelper.updateSubjectCard(card);
            }
        });

        addMissedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment missed
                missedClassesTextView.setText("Missed: " + (card.getMissed() + 1));
                card.setMissed(card.getMissed() + 1);
                updateAttendancePercentage(card, attendancePercentageTextView);
                totalClassesTextView.setText("Total: " + (card.getAttended() + card.getMissed()));
                progressBar.setProgress((int) ((double) card.getAttended() / (card.getAttended() + card.getMissed()) * 100)); // Update progress bar
                dbHelper.updateSubjectCard(card);
            }
        });

        // Set onClickListener for delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove subject card view from its parent
                ((ViewGroup) subjectCardView.getParent()).removeView(subjectCardView);
                dbHelper.deleteSubjectCard(card.getId());
            }
        });

        // Add subject card to subjects layout
        LinearLayout subjectsLayout = findViewById(R.id.subjectsLayout);
        subjectsLayout.addView(subjectCardView);
    }

    private void updateAttendancePercentage(SubjectCard card, TextView attendancePercentageTextView) {
        double attendancePercentage = (double) card.getAttended() / (card.getAttended() + card.getMissed()) * 100;
        attendancePercentageTextView.setText(String.format(Locale.getDefault(), "%.2f%%", attendancePercentage));
        card.setAttendancePercentage(attendancePercentage);
        dbHelper.updateSubjectCard(card);
    }
}
