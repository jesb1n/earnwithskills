package com.example.earnwithskills.Models;

import java.util.ArrayList;

import java.io.Serializable;

public class Work implements Serializable
{
    String id, topic, skill, org, amount;
    ArrayList<String> requests;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Work(String id, String topic, String skill, String org, String amount) {
        this.id=id;
        this.topic = topic;
        this.skill = skill;
        this.org = org;
        this.amount = amount;
        this.requests=new ArrayList<String>();
        requests.add("empty");
    }




    public Work() {
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
