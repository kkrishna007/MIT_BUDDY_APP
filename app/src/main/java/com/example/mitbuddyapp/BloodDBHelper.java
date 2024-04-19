package com.example.mitbuddyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BloodDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BloodRequirementsDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "BloodRequirements";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HEADLINE = "headline";
    private static final String COLUMN_BLOOD_TYPE = "blood_type";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_CONTACT = "contact";

    public BloodDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_HEADLINE + " TEXT," +
                COLUMN_BLOOD_TYPE + " TEXT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_CONTACT + " TEXT" + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBloodRequirement(String headline, String bloodType, String date, String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HEADLINE, headline);
        values.put(COLUMN_BLOOD_TYPE, bloodType);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_CONTACT, contact);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<BloodRequirement> getAllBloodRequirements() {
        List<BloodRequirement> bloodRequirements = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                BloodRequirement requirement = new BloodRequirement(
                        cursor.getString(cursor.getColumnIndex(COLUMN_HEADLINE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_BLOOD_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT))
                );
                bloodRequirements.add(requirement);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bloodRequirements;
    }
}
