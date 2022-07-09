package com.example.earnwithskills.Models;

import java.util.ArrayList;

public class Student {
    String name, college, degree, year, mail, password;
    ArrayList<String> skills;
    ArrayList<String> works;

    public ArrayList<String> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<String> works) {
        this.works = works;
    }

    public Student(String name, String college, String degree, String year, String mail, String password) {
        this.name = name;
        this.college = college;
        this.degree = degree;
        this.year = year;
        this.mail = mail;
        this.password = password;
        this.skills=new ArrayList<String>();
        skills.add("empty");
        this.works=new ArrayList<String>();
        works.add("empty");
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public String getName() {
        return name;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public String getCollege() {
        return college;
    }

    public String getDegree() {
        return degree;
    }

    public Student() {
    }

    public String getYear() {
        return year;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
