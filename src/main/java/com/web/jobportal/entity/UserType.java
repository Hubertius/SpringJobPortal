package com.web.jobportal.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users_type")
public class UserType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userTypeId;

    private String userTypeName;

    @OneToMany(targetEntity = User.class, mappedBy="userTypeId", cascade = CascadeType.ALL)
    private List<User> users;

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                '}';
    }
}
