package com.devjob.devjob_app.model;

import java.util.List;

public class Company {

    private String name;

    private String legacy;

    private List<CompanyValue> values;

    public Company(String name, String legacy, List<CompanyValue> values) {
        this.name = name;
        this.legacy = legacy;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegacy() {
        return legacy;
    }

    public void setLegacy(String legacy) {
        this.legacy = legacy;
    }

    public List<CompanyValue> getValues() {
        return values;
    }

    public void setValues(List<CompanyValue> values) {
        this.values = values;
    }
}
