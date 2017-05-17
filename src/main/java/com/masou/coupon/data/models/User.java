package com.masou.coupon.data.models;

import java.util.Date;

public class User {
    private Long id;

    private String username;

    private String password;

    private Date createAt;

    private Integer role;

    private String phone;

    private UserProfile profile;

    public User() {
    }

    public User(Long id, String username, String password, Date createAt, Integer role, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
        this.role = role;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}