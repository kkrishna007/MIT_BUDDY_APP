package com.example.mitbuddyapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class FacultyDbHelper extends SQLiteOpenHelper {
    String[][] faculty_data = {
            {"faculty_1", "fc_1", "Dr. Smitha N Pai", "Professor", "https://manipal.edu/mit/department-faculty/faculty-list/smitha-n-pai0.html"},
            {"faculty_2", "fc_1", "Dr. Girija V. Attigeri", "Associate professor", "https://manipal.edu/mit/department-faculty/faculty-list/girija-attegeri.html"},
            {"faculty_3", "fc_1", "Dr. Chandrakala C B", "Additional Professor", "https://manipal.edu/mit/department-faculty/faculty-list/chandrakala-cb.html"},
            {"faculty_4", "fc_1", "Ms Divya S", "Asst Professor- SI grade", "https://manipal.edu/mit/department-faculty/faculty-list/divya-s.html"},
            {"faculty_5", "fc_1", "Dr Radhika M Pai", "Professor", "https://manipal.edu/mit/department-faculty/faculty-list/radhika-m-pai.html"},
            {"faculty_6", "fc_1", "Dr Balachandra", "Professor", "https://manipal.edu/mit/department-faculty/faculty-list/balachandra.html"},
            {"faculty_7", "fc_1", "Dr Krishna Prakasha", "Associate professor", "https://manipal.edu/mit/department-faculty/faculty-list/krishna-prakasha-a.html"},
            {"faculty_8", "fc_1", "Dr Manjula C B", "Asst Professor SI grade", "https://manipal.edu/mit/department-faculty/faculty-list/manjula-c-belavagi.html"},
            {"faculty_9", "fc_1", "Mrs Swathi B P", "Asst Professor SI grade", "https://manipal.edu/mit/department-faculty/faculty-list/swathi-b-p.html"},
            {"faculty_10", "fc_2", "Mr Chethan Sharma", "Asst Professor Sr Scal", "https://manipal.edu/mit/department-faculty/faculty-list/chethan-sharma.html"},
            {"faculty_11", "fc_2", "Dr Adesh N D", "Associate professor", "https://manipal.edu/mit/department-faculty/faculty-list/AdeshND.html"},
            {"faculty_12", "fc_2", "Dr Sucheta V Kolejar", "Associate Professor", "https://manipal.edu/mit/department-faculty/faculty-list/sucheta-kolekar.html"},
            {"faculty_13", "fc_2", "Dr Preetham Kumar", "Professor | Deputy Registrar-Academics(Technical) Manipal Academy of Higher Education", "https://manipal.edu/mit/department-faculty/faculty-list/preetham-kumar.html"},
            {"faculty_14", "fc_2", "Dr Manohar S Pai M M", "Senior Professor", "NULL"},
            {"faculty_15", "fc_2", "Mr Akshay K C", "Asst Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/akshay-kc.html"},
            {"faculty_16", "fc_2", "Dr Kaliraj S", "Assistant professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/dr-kaliraj-s.html"},
            {"faculty_17", "fc_2", "Dr Divya Rao", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/divya-rao.html"},
            {"faculty_18", "fc_2", "Dr Raviraj Holla", "Asst Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/RavirajaHollaM.html"},
            {"faculty_19", "fc_3", "Dr Raghavendra S", "Assistant Professor", "https://manipal.edu/mit/department-faculty/faculty-list/dr-raghavendra-s.html"},
            {"faculty_20", "fc_3", "Jayashree", "Assistant Professor", "https://manipal.edu/mit/department-faculty/faculty-list/Jayashree.html"},
            {"faculty_21", "fc_3", "Dr Raghavendra Achar", "Associate Professor", "https://manipal.edu/mit/department-faculty/faculty-list/RaghavendraAchar.html"},
            {"faculty_22", "fc_3", "Dr Santhosh Rao", "Additional Professor", "NULL"},
            {"faculty_23", "fc_3", "Dr Santhosh Kamath", "Associate professor", "https://manipal.edu/mit/department-faculty/faculty-list/santhosh-kamath.html"},
            {"faculty_24", "fc_4", "Dr Ramakrishna M", "Associate Professor", "https://manipal.edu/mit/department-faculty/faculty-list/ramakrishna-m.html"},
            {"faculty_25", "fc_4", "Dr Veena Mayya", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/veena-mayya.html"},
            {"faculty_26", "fc_4", "Ms Chetana Pujari", "Asst Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/chetana-pujari.html"},
            {"faculty_27", "fc_4", "Dr Diana Olivia", "Assistant Professor", "https://manipal.edu/mit/department-faculty/faculty-list/diana-olivia.html"},
            {"faculty_28", "fc_4", "Mrs Pooja S", "Asst Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/pooja-s.html"},
            {"faculty_29", "fc_4", "Mrs Vibha", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/Vibha.html"},
            {"faculty_30", "fc_4", "Dr Sanjay Singh", "Professor", "https://manipal.edu/mit/department-faculty/faculty-list/sanjay-singh.html"},
            {"faculty_31", "fc_5", "Dr Sumith N", "Associate Professor", "https://manipal.edu/mit/department-faculty/faculty-list/SumitN.html"},
            {"faculty_32", "fc_5", "Mrs Anuradha Rao", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/anuradha-rao.html"},
            {"faculty_33", "fc_5", "Dr Raghavendra Ganiga", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/raghavendra-ganiga.html"},
            {"faculty_34", "fc_5", "Dr Sameena Pathan", "Assistant Professor", "https://manipal.edu/mit/department-faculty/faculty-list/SameenaBP.html"},
            {"faculty_35", "fc_5", "Ms Nisha P Shetty", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/nisha-p-shetty.html"},
            {"faculty_36", "fc_5", "Dr Rashmi Naveen Raj", "Associate Professor", "https://manipal.edu/mit/department-faculty/faculty-list/rashmi-naveen-raj.html"},
            {"faculty_37", "fc_5", "Dr Poornalatha G", "Additional Professor", "https://manipal.edu/mit/department-faculty/faculty-list/poornalatha-g.html"},
            {"faculty_38", "fc_5", "Mrs Veena K M", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/veena-km.html"},
            {"faculty_39", "fc_5", "Mrs Sangeetha T S", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/sangeetha-ts.html"},
            {"faculty_40", "fc_5", "Mr Ghanashyama Prabhu", "Assistant Professor SI Grade", "https://manipal.edu/mit/department-faculty/faculty-list/ghanashyama-prabhu.html"},
            {"faculty_41", "fc_5", "Dr Manjula Shenoy K", "Professor", "https://manipal.edu/mit/department-faculty/faculty-list/manjula-shenoy-k.html"},
            {"faculty_42", "fc_5", "Mr Vinayak Mantoor", "Assistant Professor Sr Scale", "https://manipal.edu/mit/department-faculty/faculty-list/vinayak-m.html"},
            {"faculty_43", "ict-ice", "Dr Manoj Tolani", "Professor", "https://researcher.manipal.edu/en/persons/manoj-tolani"},

    };


    private static final String DATABASE_NAME = "Facultydb";
    private static final int DATABASE_VERSION = 1;

    public FacultyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS faculty (" +
                "_id TEXT PRIMARY KEY," +
                "chamber TEXT," +
                "name TEXT," +
                "designation TEXT," +
                "link TEXT)";
        db.execSQL(createTableQuery);

        // Check if the table is empty before inserting records
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM faculty", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            if (count == 0) {
                insertFacultyRecords(db);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS faculty");
        onCreate(db);
    }

    public Cursor searchFaculty(String searchTerm) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "chamber", "name", "designation", "link"};
        String selection = "name LIKE ?";
        String[] selectionArgs = {"%" + searchTerm + "%"};
        return db.query("faculty", columns, selection, selectionArgs, null, null, null);
    }

    private void insertFacultyRecords(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        for (String[] faculty : faculty_data) {
            // Extracting data from each faculty record
            String id = faculty[0];
            String chamber = faculty[1];
            String name = faculty[2];
            String designation = faculty[3];
            String link = faculty[4];

            // Put data into ContentValues
            values.put("_id", id);
            values.put("chamber", chamber);
            values.put("name", name);
            values.put("designation", designation);
            values.put("link", link);

            // Insert the record into the database
            db.insert("faculty", null, values);
        }
    }
}
