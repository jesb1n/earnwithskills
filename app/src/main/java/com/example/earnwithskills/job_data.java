package com.example.earnwithskills;

public class job_data {
    private String job_name;
    private String job_skill;

    public job_data(String job_name, String job_skill) {
        this.job_name = job_name;
        this.job_skill = job_skill;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_skill() {
        return job_skill;
    }

    public void setJob_skill(String job_skill) {
        this.job_skill = job_skill;
    }
}
