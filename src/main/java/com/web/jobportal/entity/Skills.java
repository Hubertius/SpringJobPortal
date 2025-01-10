package com.web.jobportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name="skills")
public class Skills {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;

    private String experienceLevel;
    private String nameOfSeeker;
    private String yearsOfExperience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="job_seeker_profile")
    private JobSeekerProfile jobSeekerProfile;

    public Skills() {
    }

    public Skills(int userId, String experienceLevel, String nameOfSeeker, String yearsOfExperience, JobSeekerProfile jobSeekerProfile) {
        this.userId = userId;
        this.experienceLevel = experienceLevel;
        this.nameOfSeeker = nameOfSeeker;
        this.yearsOfExperience = yearsOfExperience;
        this.jobSeekerProfile = jobSeekerProfile;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getNameOfSeeker() {
        return nameOfSeeker;
    }

    public void setNameOfSeeker(String nameOfSeeker) {
        this.nameOfSeeker = nameOfSeeker;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public JobSeekerProfile getJobSeekerProfile() {
        return jobSeekerProfile;
    }

    public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
        this.jobSeekerProfile = jobSeekerProfile;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "userId=" + userId +
                ", experienceLevel='" + experienceLevel + '\'' +
                ", nameOfSeeker='" + nameOfSeeker + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", jobSeekerProfile=" + jobSeekerProfile +
                '}';
    }
}
