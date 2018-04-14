package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TV extends Item
{
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private  Set<TVSeason> seasons;

    @OneToOne(cascade=CascadeType.PERSIST)
    private  SeriesInfo seriesInfo;

    private double criticScore;
    private double audienceScore;

    //Constructor for JPA
    protected TV(){}

    //Constructor
    public TV(double criticScore, double audienceScore)
    {
        this.criticScore = criticScore;
        this.audienceScore = audienceScore;
    }

    //Setters
    public void setSeasons( Set<TVSeason>  s) {this.seasons = s;}
    public void setSeriesInfo(SeriesInfo s) {this.seriesInfo = s;}
    public void setCriticScore(double s) {this.criticScore = s;}
    public void setAudienceScore(double s) {this.audienceScore = s;}


    //Getters
    public Set<TVSeason> getSeasons() { return this.seasons;}
    public SeriesInfo getSeriesInfo() {return this.seriesInfo;}
    public double getCriticScore() {return this.criticScore;}
    public double getAudienceScore() {return this.audienceScore;}


}