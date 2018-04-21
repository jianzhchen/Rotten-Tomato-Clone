package com.redwood.rottenpotato.main.models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Review2 {
    //Primary key for the entity Item
    @Id

    private String reviewKey;
    private String movieKey;
    private String criticKey;

    private String reviewContent;

    //Constructor for JPA
    public Review2() {
    }

}