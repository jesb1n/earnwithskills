package com.example.earnwithskills.Models;

import java.util.ArrayList;

public class Work {
    String topic, duration, skill, org;
    ArrayList<String> requests;

    public Work(String topic, String duration, String skill, String org) {
        this.topic = topic;
        this.duration = duration;
        this.skill = skill;
        this.org = org;
        this.requests=new ArrayList<String>();
        requests.add("empty");
    }



    public Work() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }
}
