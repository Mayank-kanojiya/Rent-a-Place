package com.training.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "rent_a_place")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String role;

    public User() {}

    public User(int userId, String name, String username, String email,
                String password, String phone, String role) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
