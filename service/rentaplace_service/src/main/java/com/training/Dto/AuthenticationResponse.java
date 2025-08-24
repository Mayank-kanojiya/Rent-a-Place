package com.training.Dto;

public class AuthenticationResponse {
    private String token;
    private String role;
    private int id;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, String role, int id) {
        this.token = token;
        this.role = role;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
