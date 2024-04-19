package com.example.mitbuddyapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SubjectCardDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SubjectCardDB";

    private static final String TABLE_SUBJECT_CARDS = "subject_cards";
    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT_NAME = "subject_name";
    private static final String KEY_ATTENDED = "attended";
    private static final String KEY_MISSED = "missed";
    private static final String KEY_REQUIREMENT = "requirement";
    private static final String KEY_ATTENDANCE_PERCENTAGE = "attendance_percentage";

    public SubjectCardDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SUBJECT_CARDS_TABLE = "CREATE TABLE " + TABLE_SUBJECT_CARDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SUBJECT_NAME + " TEXT,"
                + KEY_ATTENDED + " INTEGER,"
                + KEY_MISSED + " INTEGER,"
                + KEY_REQUIREMENT + " INTEGER,"
                + KEY_ATTENDANCE_PERCENTAGE + " REAL"
                + ")";
        db.execSQL(CREATE_SUBJECT_CARDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT_CARDS);
        onCreate(db);
    }

    public void addSubjectCard(SubjectCard subjectCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT_NAME, subjectCard.getSubjectName());
        values.put(KEY_ATTENDED, subjectCard.getAttended());
        values.put(KEY_MISSED, subjectCard.getMissed());
        values.put(KEY_REQUIREMENT, subjectCard.getRequirement());
        values.put(KEY_ATTENDANCE_PERCENTAGE, subjectCard.getAttendancePercentage());
        db.insert(TABLE_SUBJECT_CARDS, null, values);
        db.close();
    }

    public List<SubjectCard> getAllSubjectCards() {
        List<SubjectCard> subjectCards = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT_CARDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SubjectCard subjectCard = new SubjectCard();
                subjectCard.setId(cursor.getLong(0));
                subjectCard.setSubjectName(cursor.getString(1));
                subjectCard.setAttended(cursor.getInt(2));
                subjectCard.setMissed(cursor.getInt(3));
                subjectCard.setRequirement(cursor.getInt(4));
                subjectCard.setAttendancePercentage(cursor.getDouble(5));
                subjectCards.add(subjectCard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return subjectCards;
    }

    public void deleteSubjectCard(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECT_CARDS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
