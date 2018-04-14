package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class TVSeason extends Item
{

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Set<TVEpisode> episodes;
    private TVSeasonInfo seasonInfo;
    private Set<Review> criticReviews;
    private Set<Review> audienceReviews;

    //Setters
    public void setEpisodes( Set<TVEpisode>  s) {this.episodes = s;}
    public void setSeasonInfo(TVSeasonInfo s) {this.seasonInfo = s;}
    public void setCriticReviews(Set<Review> s) {this.criticReviews = s;}
    public void setAudienceReviews(Set<Review> s) {this.audienceReviews = s;}


    //Getters
    public Set<TVEpisode>  getEpisodes() {return this.episodes;}
    public TVSeasonInfo getSeasonInfo() {return this.seasonInfo;}
    public Set<Review> getCriticReviews() {return this.criticReviews;}
    public Set<Review> getAudienceReviews() {return this.audienceReviews;}



}
