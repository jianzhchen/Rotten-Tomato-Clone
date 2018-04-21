package com.redwood.rottenpotato.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Follow {
    //Primary key for the entity Item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userIdFrom;
    private long userIdTo;

    //Constructor for JPA
    public Follow() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public long getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(long userIdTo) {
        this.userIdTo = userIdTo;
    }
}