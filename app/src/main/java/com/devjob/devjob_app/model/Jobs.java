package com.devjob.devjob_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Jobs {

    @Expose
    @SerializedName("jobs")
    private List<Job> jobs;

    @SerializedName("pageable")
    @Expose
    private Pageable pageable;

    public Jobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
