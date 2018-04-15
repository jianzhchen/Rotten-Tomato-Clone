package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class EpisodeInfo
{
    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    private String description;
    private String network;
    private Date airDate;

    //Constructor for JPA
    protected EpisodeInfo(){}

    //Constructor for instantiation
    public EpisodeInfo(String description, String network, Date airDate)
    {
        this.description = description;
        this.network = network;
        this.airDate = airDate;
    }

    //Setters
    public void setDescription( String  s) {this.description = s;}
    public void setNetwork( String  s) {this.network = s;}
    public void setAirDate( Date  s) {this.airDate = s;}

    //Getters
    public String getDescription() {return this.description;}
    public String getNetwork() {return this.network;}
    public Date getAirDate() {return this.airDate;}


}