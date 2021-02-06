package com.devjob.devjob_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Job implements Serializable {

    public static final String JOB_ID_KEY = "JOB_ID_KEY";

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName(("title"))
    private String title;

    @Expose
    @SerializedName("description")
    private String description;

    @SerializedName("remuneration")
    private Float remuneration;

    @Expose
    @SerializedName("seniority")
    private String seniority;

    @Expose
    @SerializedName("isRemoteWork")
    private boolean isRemoteWork;

    @SerializedName("vacancyType")
    private VacancyType vacancyType;

    @SerializedName("qualifications")
    private List<Qualification> qualifications;

    @SerializedName("technologies")
    private List<Technology> technologies;

    @SerializedName("responsibilities")
    private List<Responsibility> responsibilities;

    @SerializedName("company")
    private Company company;

    @SerializedName("benefits")
    private List<Benefit> benefits;

    @SerializedName("location")
    private Location location;

    @SerializedName("contact")
    private Contact contact;

    @SerializedName("recruiter")
    private Recruiter recruiter;

    public Job(String id, String title, String description, Float remuneration, String seniority, boolean isRemoteWork, VacancyType vacancyType, List<Qualification> qualifications, List<Technology> technologies, List<Responsibility> responsibilities, Company company, List<Benefit> benefits, Location location, Contact contact, Recruiter recruiter) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.remuneration = remuneration;
        this.seniority = seniority;
        this.isRemoteWork = isRemoteWork;
        this.vacancyType = vacancyType;
        this.qualifications = qualifications;
        this.technologies = technologies;
        this.responsibilities = responsibilities;
        this.company = company;
        this.benefits = benefits;
        this.location = location;
        this.contact = contact;
        this.recruiter = recruiter;
    }

    public boolean isRemoteWork() {
        return isRemoteWork;
    }

    public void setRemoteWork(boolean remoteWork) {
        isRemoteWork = remoteWork;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Float remuneration) {
        this.remuneration = remuneration;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public VacancyType getVacancyType() {
        return vacancyType;
    }

    public void setVacancyType(VacancyType vacancyType) {
        this.vacancyType = vacancyType;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public List<Responsibility> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<Responsibility> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
}
