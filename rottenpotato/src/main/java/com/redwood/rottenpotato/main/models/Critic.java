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

    private String criticPublication;

    //Constructor for JPA
    public Critic() {
    }

}