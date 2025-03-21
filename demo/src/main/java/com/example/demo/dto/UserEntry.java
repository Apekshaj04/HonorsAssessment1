package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class UserEntry {
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Password should be entered")
    private String password;

    // ✅ Getters and Setters (Manually Added)
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
