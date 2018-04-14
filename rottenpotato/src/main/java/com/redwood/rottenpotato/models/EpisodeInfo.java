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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String description;
    private String network;
    private Date airDate;

    //Setters
    public void setDescription( String  s) {this.description = s;}
    public void setNetwork( String  s) {this.network = s;}
    public void setAirDate( Date  s) {this.airDate = s;}

    //Getters
    public String getDescription() {return this.description;}
    public String getNetwork() {return this.network;}
    public Date getAirDate() {return this.airDate;}


}
