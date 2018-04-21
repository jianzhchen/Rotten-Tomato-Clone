package com.redwood.rottenpotato.main.models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Actor
{
    //Primary key for the entity Item
    @Id
    private String actorKey;
    private String actorName;
    private String birthplace;
    private String birthday;
    private String actorInfo;

    //Constructor for JPA
    protected Actor() {
    }

    //Constructor for instantiation
    public Actor(String actorName) {
        this.actorName = actorName;
    }

    public String getActorKey() {
        return actorKey;
    }

    public void setActorKey(String actorKey) {
        this.actorKey = actorKey;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getActorInfo() {
        return actorInfo;
    }

    public void setActorInfo(String actorInfo) {
        this.actorInfo = actorInfo;
    }
}