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
import java.util.List;

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

    public void loadSubjectCardsFromDatabase() {
        List<SubjectCard> subjectCards = dbHelper.getAllSubjectCards();
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

                double attendancePercentage = (double) attended / (attended + missed) * 100;

                long id = dbHelper.insertSubjectCard(new SubjectCard(subjectName, attended, missed, requirement, attendancePercentage));

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
        View subjectCardView = getLayoutInflater().inflate(R.layout.subject_card_layout, null);

        TextView subjectNameTextView = subjectCardView.findViewById(R.id.subjectNameTextView);
        TextView attendedClassesTextView = subjectCardView.findViewById(R.id.attendedClassesTextView);
        TextView missedClassesTextView = subjectCardView.findViewById(R.id.missedClassesTextView);
        TextView totalClassesTextView = subjectCardView.findViewById(R.id.totalClassesTextView);
        TextView attendancePercentageTextView = subjectCardView.findViewById(R.id.attendancePercentageTextView);
        Button addAttendButton = subjectCardView.findViewById(R.id.addAttendButton);
        Button addMissedButton = subjectCardView.findViewById(R.id.addMissedButton);
        Button deleteButton = subjectCardView.findViewById(R.id.deleteButton);
        ProgressBar progressBar = subjectCardView.findViewById(R.id.attendanceProgressBar);

        subjectNameTextView.setText(card.getSubjectName());
        attendedClassesTextView.setText("Attended: " + card.getAttended());
        missedClassesTextView.setText("Missed: " + card.getMissed());
        totalClassesTextView.setText("Total: " + (card.getAttended() + card.getMissed()));
        attendancePercentageTextView.setText(String.format(Locale.getDefault(), "%.2f%%", card.getAttendancePercentage()));

        progressBar.setProgress((int) card.getAttendancePercentage());

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

        addAttendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendedClassesTextView.setText("Attended: " + (card.getAttended() + 1));
                card.setAttended(card.getAttended() + 1);
                updateAttendancePercentage(card, attendancePercentageTextView);
                totalClassesTextView.setText("Total: " + (card.getAttended() + card.getMissed()));
                progressBar.setProgress((int) ((double) card.getAttended() / (card.getAttended() + card.getMissed()) * 100));
                dbHelper.updateSubjectCard(card);
            }
        });

        addMissedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                missedClassesTextView.setText("Missed: " + (card.getMissed() + 1));
                card.setMissed(card.getMissed() + 1);
                updateAttendancePercentage(card, attendancePercentageTextView);
                totalClassesTextView.setText("Total: " + (card.getAttended() + card.getMissed()));
                progressBar.setProgress((int) ((double) card.getAttended() / (card.getAttended() + card.getMissed()) * 100));
                dbHelper.updateSubjectCard(card);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) subjectCardView.getParent()).removeView(subjectCardView);
                dbHelper.deleteSubjectCard(card.getId());
            }
        });

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
