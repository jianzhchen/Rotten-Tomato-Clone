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


}