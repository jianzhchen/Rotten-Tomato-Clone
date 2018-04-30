package com.redwood.rottenpotato.main.models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Critic
{
    //Primary key for the entity Item
    @Id
    private String criticKey;

    private String criticName;
    private String criticInfo;


    //Constructor for JPA
    public Critic() {
    }

    public String getCriticKey() {
        return criticKey;
    }

    public void setCriticKey(String criticKey) {
        this.criticKey = criticKey;
    }

    public String getCriticName() {
        return criticName;
    }

    public void setCriticName(String criticName) {
        this.criticName = criticName;
    }

    public String getCriticInfo() {
        return criticInfo;
    }

    public void setCriticInfo(String criticInfo) {
        this.criticInfo = criticInfo;
    }
}