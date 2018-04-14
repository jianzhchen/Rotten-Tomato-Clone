package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class TV extends Item
{

    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Set<TVSeason> seasons;
    private  SeriesInfo seriesInfo;
    private double criticScore;
    private double audienceScore;

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
