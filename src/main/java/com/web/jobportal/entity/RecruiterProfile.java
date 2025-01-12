package com.web.jobportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name="recruiter_profile")
public class RecruiterProfile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userAccountId;

    @OneToOne
    @JoinColumn(name="user_account_id")
    @MapsId
    private User userId;

    private String city;
    private String company;
    private String firstName;
    private String lastName;

    @Column(nullable = true, length = 64)
    private String profilePhoto;

    private String state;

    public RecruiterProfile() {
    }

    public RecruiterProfile(User user) {
        this.userId = user;
    }

    public RecruiterProfile(int userAccountId, User userId, String city, String company, String firstName, String lastName, String profilePhoto, String state) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.city = city;
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePhoto = profilePhoto;
        this.state = state;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    @Transient
    public String getPhotosImagePath() {
        if(profilePhoto == null) {
            return null;
        }
        return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RecruiterProfile{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
