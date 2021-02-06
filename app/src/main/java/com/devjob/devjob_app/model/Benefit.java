package com.devjob.devjob_app.model;

public class Benefit {

    private String description;

    private Float value;

    public Benefit(String description, Float value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
