package com.redwood.rottenpotato.main.models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class TV {
    //Primary key for the entity Item
    @Id
    private String TVKey;
    private String TVName;
    private String TVInfo;
    private String TVGenre;
    private String TVNetwork;
    private String TVDate;
    private String TVCast;

    //Constructor for JPA
    protected TV() {
    }

}