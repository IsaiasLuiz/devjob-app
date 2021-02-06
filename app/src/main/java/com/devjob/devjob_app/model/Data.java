package com.devjob.devjob_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @Expose
    @SerializedName("jobs")
    private Jobs jobs;

    @Expose
    @SerializedName("job")
    private Job job;

    public Data(Jobs jobs) {
        this.jobs = jobs;
    }

    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public Data(Job job) {
        this.job = job;
    }

    public Data(Jobs jobs, Job job) {
        this.jobs = jobs;
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
