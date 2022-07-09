package com.example.earnwithskills.Models;

public class Hirer {
    String org_name,email,password;

    public Hirer(String org_name, String email, String password) {
        this.org_name = org_name;
        this.email = email;
        this.password = password;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
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
