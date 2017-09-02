package com.example.demo.Domain;

public class AuthInfo {
    private String email;
    private String password;

    public AuthInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthInfo() {
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
}
