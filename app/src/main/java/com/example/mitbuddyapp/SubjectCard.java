package com.example.mitbuddyapp;

public class SubjectCard {
    private long id;
    private String subjectName;
    private int attended;
    private int missed;
    private int requirement;
    private double attendancePercentage;

    // Constructor
    public SubjectCard(long id, String subjectName, int attended, int missed, int requirement, double attendancePercentage) {
        this.id = id;
        this.subjectName = subjectName;
        this.attended = attended;
        this.missed = missed;
        this.requirement = requirement;
        this.attendancePercentage = attendancePercentage;
    }
    // Constructors
    public SubjectCard() {
    }

    public SubjectCard(String subjectName, int attended, int missed, int requirement, double attendancePercentage) {
        this.subjectName = subjectName;
        this.attended = attended;
        this.missed = missed;
        this.requirement = requirement;
        this.attendancePercentage = attendancePercentage;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getMissed() {
        return missed;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }

    public int getRequirement() {
        return requirement;
    }

    public void setRequirement(int requirement) {
        this.requirement = requirement;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
