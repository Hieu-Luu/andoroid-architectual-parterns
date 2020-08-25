package com.android.roombooking.model;

import java.util.List;

public class User {

    public static final String JSON_ARRAY_NAME = "users";

    public String name;
    public List<String> emails;

    public String getName() {
        return name;
    }

    public List<String> getEmails() {
        return emails;
    }
}
