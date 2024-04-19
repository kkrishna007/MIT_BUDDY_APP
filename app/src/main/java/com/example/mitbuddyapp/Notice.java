// Notice.java
package com.example.mitbuddyapp;

public class Notice {
    private int id;
    private String title;
    private String description;
    private String link;
    private String category;

    // Constructor
    public Notice(int id, String title, String description, String link, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.category = category;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }

    // Setter methods (if needed)
}
