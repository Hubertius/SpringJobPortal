package com.web.jobportal.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="job_seeker_profile")
public class JobSeekerProfile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userAccountId;

    @OneToOne
    @JoinColumn(name="user_account_id")
    @MapsId
    private User userId;

    private String city;
    private String country;
    private String employmentType;
    private String firstName;
    private String lastName;

    @Column(nullable = true, length = 64)
    private String profilePhoto;
    private String seekerResume;
    private String state;
    private String workAuthorization;

    @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
    private List<Skills> skills;

    public JobSeekerProfile() {
    }

    public JobSeekerProfile(User userId) {
        this.userId = userId;
    }

    public JobSeekerProfile(int userAccountId, User userId, String city, String country, String employmentType, String firstName, String lastName, String profilePhoto, String seekerResume, String state, String workAuthorization, List<Skills> skills) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.city = city;
        this.country = country;
        this.employmentType = employmentType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
        this.seekerResume = seekerResume;
        this.state = state;
        this.workAuthorization = workAuthorization;
        this.skills = skills;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getSeekerResume() {
        return seekerResume;
    }

    public void setSeekerResume(String seekerResume) {
        this.seekerResume = seekerResume;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "JobSeekerProfile{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", seekerResume='" + seekerResume + '\'' +
                ", state='" + state + '\'' +
                ", workAuthorization='" + workAuthorization + '\'' +
                ", skills=" + skills +
                '}';
    }
}
