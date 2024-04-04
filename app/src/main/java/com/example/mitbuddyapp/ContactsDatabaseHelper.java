package com.example.mitbuddyapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public ContactsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create contacts table
        db.execSQL("CREATE TABLE contacts (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "category TEXT," +
                "name TEXT," +
                "phoneNumber TEXT);");

        // Insert contacts data
        db.execSQL("INSERT INTO contacts (category, name, phoneNumber) VALUES " +
                "('Auto Service', 'Eshwar Nagar', '08202574200')," +
                "('Auto Service', 'Green Park', '08202572006')," +
                "('Auto Service', 'Mandavi', '08202575070')," +
                "('Auto Service', 'Manish', '08202574369')," +
                "('Auto Service', 'Night Auto Santosh', '9986921287')," +
                "('Auto Service', 'RT', '08202574300')," +
                "('Auto Service', 'Syndicate Circle', '08202571454')," +
                "('Auto Service', 'Dasharatha', '08202574202')," +

                "('Eateries', 'Anupam', '08202572635')," +
                "('Eateries', 'Apoorva Mess', '9535130111')," +
                "('Eateries', 'Apoorva Mess 2', '9591719555')," +
                "('Eateries', 'Attil', '08204293399')," +
                "('Eateries', 'Basil Cafe', '08204293284')," +
                "('Eateries', 'Binge Yard', '9164108303')," +
                "('Eateries', 'Blue Waters', '08202573765')," +
                "('Eateries', 'Campus Grill', '9739940608')," +
                "('Eateries', 'Charcoal', '08202570123')," +
                "('Eateries', 'Chef Inn', '08204290973')," +
                "('Eateries', 'CoastAsia', '7353040333')," +
                "('Eateries', 'Dishes', '08204294094')," +
                "('Eateries', 'Dollops', '8982394234')," +
                "('Eateries', 'Domino''s', '08202574352')," +
                "('Eateries', 'Dum Biryani Adda', '9152646557')," +
                "('Eateries', 'Egg Factory', '08204294455')," +
                "('Eateries', 'Extreme Arabian Treat', '08204291155')," +
                "('Eateries', 'Eye of The Tiger', '7899039139')," +
                "('Eateries', 'Ginger Garlic', '7619574318')," +
                "('Eateries', 'Guzzler''s Inn', '08204292602')," +
                "('Eateries', 'Hangout', '08204296016')," +
                "('Eateries', 'Hot Chix', '8792831601')," +
                "('Eateries', 'Hotspot', '9845254395')," +
                "('Eateries', 'Just Bake', '08204296611')," +
                "('Eateries', 'KFC', '8033994444')," +
                "('Eateries', 'McDonalds', '7349673521')," +
                "('Eateries', 'Pangala', '08202570237')," +
                "('Eateries', 'Planet Café', '08202572454')," +
                "('Eateries', 'Poornima Kitchen', '9741745715')," +
                "('Eateries', 'Prax', '9901722607')," +
                "('Eateries', 'Red Kitchen', '8073811048')," +
                "('Eateries', 'Sai''s', '08202570177')," +
                "('Eateries', 'Saiba(1)', '9152540278')," +
                "('Eateries', 'Saiba(2)', '8277534185')," +
                "('Eateries', 'Scirocco Coffee Valley', '9008232259')," +
                "('Eateries', 'Sheela Sagar', '08202575473')," +
                "('Eateries', 'Shubham''s', '9731542673')," +
                "('Eateries', 'Sizzler Ranch', '08202574001')," +
                "('Eateries', 'Snack Shack', '08202575129')," +
                "('Eateries', 'Spice and Ice', '9663309214')," +
                "('Eateries', 'Spicy Village', '9035639458')," +
                "('Eateries', 'Subway', '08202574144')," +
                "('Eateries', 'The J', '9967278708')," +
                "('Eateries', 'Timmy''s', '9886902553')," +
                "('Eateries', 'Yummy''s Kitchen', '9844547414')," +
                "('Eateries', 'Zebra Spot', '9740008183')," +

                "('Emergency', 'Ambulance', '23423432')," +
                "('Emergency', 'Fire Helpline', '08202520333')," +
                "('Emergency', 'KMC Ambulance(1)', '08202922761')," +
                "('Emergency', 'KMC Ambulance(2)', '08202923153')," +
                "('Emergency', 'KMC Ambulance(3)', '08202922404')," +
                "('Emergency', 'MAHE Campus Patrol', '9945670912')," +
                "('Emergency', 'MIT Campus Patrol', '9632101004')," +
                "('Emergency', 'Police Station', '0820257038')," +

                "('Rent a bike', 'Bhoom Riders', '8150025955')," +
                "('Rent a bike', 'IndiaRides', '9686325168')," +
                "('Rent a bike', 'Royal Brothers', '7306747474')," +
                "('Rent a bike', 'Wicked Ride', '8880299299')," +

                "('Medical Services', 'Blood Bank KMC Manipal', '08202922331')," +
                "('Medical Services', 'Dr. Suhas Bhat (Manipal Dental Solutions)', '9880041652')," +
                "('Medical Services', 'KMC Hospital Enquiry (1)', '08202571967')," +
                "('Medical Services', 'KMC Hospital Enquiry (2)', '08202922761')," +
                "('Medical Services', 'Madhwaraj Animal Care Trust', '9901639192')," +
                "('Medical Services', 'Sonia Clinic and Nursing Home (OB-GYN)', '08202570334')," +

                "('Hotels', 'Country Inn', '08202701600')," +
                "('Hotels', 'Fortune Inn', '08202571101')," +
                "('Hotels', 'Hotel Ashlesh', '08202572824')," +
                "('Hotels', 'Hotel Green Park Suites', '08204295701')," +
                "('Hotels', 'Hotel Hill View', '08204292771')," +
                "('Hotels', 'Hotel Madhuvan Serai', '7829901250')," +
                "('Hotels', 'Hotel Tranquil', '08202571111')," +

                "('Head of Departments', 'Aeronautical & Automobile', '8202925481')," +
                "('Head of Departments', 'Biomedical', '8202924211')," +
                "('Head of Departments', 'Biotechnology', '8202924321')," +
                "('Head of Departments', 'Chemical', '8202924311')," +
                "('Head of Departments', 'Chemistry', '8202924411')," +
                "('Head of Departments', 'Civil', '8202924711')," +
                "('Head of Departments', 'Computer Science', '8202924511')," +
                "('Head of Departments', 'Department of Computer Applications', '8202925381')," +
                "('Head of Departments', 'Electrical & Electronics', '8202925121')," +
                "('Head of Departments', 'Electronics & Communication', '8202924811')," +
                "('Head of Departments', 'Humanities & Management', '8202924033')," +
                "('Head of Departments', 'Information & Communication Technology', '8202925361')," +
                "('Head of Departments', 'Instrumentation & Control', '8202925151')," +
                "('Head of Departments', 'Mathematics', '8202925261')," +
                "('Head of Departments', 'Mechanical & Manufacturing', '8202925461')," +
                "('Head of Departments', 'Mechatronics', '8202925441')," +
                "('Head of Departments', 'Physics', '8202925621')," +
                "('Head of Departments', 'Printing & Media', '8202925661')," +

                "('Grocery Store', 'Laxmi''s Super Market', '9901307682')," +
                "('Grocery Store', 'Manipal Corner', '8197123460')," +
                "('Grocery Store', 'Manipal Grocer', '9964691530')," +
                "('Grocery Store', 'More Supermarket', '8652906676')," +
                "('Grocery Store', 'Queens Supermarket', '9901996124')," +
                "('Grocery Store', 'Yas Mart', '08202575234')" +
                ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic for database schema changes
        // This method will be invoked if the database version changes
        // You can implement database migration logic here
    }
}

