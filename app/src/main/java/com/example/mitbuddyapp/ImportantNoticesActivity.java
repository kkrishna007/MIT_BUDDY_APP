package com.example.mitbuddyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImportantNoticesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticeAdapter noticeAdapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_notices);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noticeList = new ArrayList<>();
        prepareNoticeData();
        noticeAdapter = new NoticeAdapter(this, noticeList);
        recyclerView.setAdapter(noticeAdapter);
    }

    private void prepareNoticeData() {
        noticeList.add(new Notice(1, "IV SEM ENDSEM SCHEDULE", "Endsem schedule for the students of IV Sem for all branches out now.", "https://drive.google.com/file/d/1KpntsGGfpDd4usujGZqctTZXIQsHREZZ/view", "academics"));
        noticeList.add(new Notice(2, "VI SEM ENDSEM SCHEDULE", "Endsem schedule for the students of VI Sem for all branches out now.", "https://drive.google.com/file/d/1Fo22uY9VDXUqtXVmRNjtYgHN5eDG4V4I/view", "academics"));
        noticeList.add(new Notice(3, "CHANGE IN CLASSROOMS", "Change in classrooms of Aero/Auto/MechX/DSE/CHEMICAL/Biotech/Mechanical due to MIT Research day", "https://drive.google.com/file/d/1siEDgReL6e7Jrp53oxY3JLcHEg-9xzvM/view", "academics"));
        noticeList.add(new Notice(4,"INSEM ASSESMENT PLAN", "In-Semester Assessment Plan and Schedule for V and VII Sem B Tech (AY: 2023-24)","https://www.instagram.com/p/Cv10eZcPBj_/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==","academics"));
    }


}
