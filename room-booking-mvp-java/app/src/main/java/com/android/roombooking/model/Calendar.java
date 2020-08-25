package com.android.roombooking.model;

public class Calendar {

    public static final String JSON_ARRAY_NAME = "calendars";

    public int id;
    public String description;
    public String summary;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }
}
