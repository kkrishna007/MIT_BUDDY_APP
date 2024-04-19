package com.example.mitbuddyapp;

public class BloodRequirement {
    private String headline;
    private String bloodType;
    private String date;
    private String contact;

    public BloodRequirement(String headline, String bloodType, String date, String contact) {
        this.headline = headline;
        this.bloodType = bloodType;
        this.date = date;
        this.contact = contact;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getDate() {
        return date;
    }

    public String getContact() {
        return contact;
    }
}
