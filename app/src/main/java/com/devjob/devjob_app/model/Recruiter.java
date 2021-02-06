package com.devjob.devjob_app.model;

import com.google.gson.annotations.SerializedName;

public class Recruiter {

    @SerializedName("recruiterId")
    private String id;

    private String name;

    public Recruiter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
