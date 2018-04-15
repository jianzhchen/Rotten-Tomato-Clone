package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TVSeason extends Item
{
    @ElementCollection
    private Set<TVEpisode> episodes;

    @OneToOne(cascade=CascadeType.PERSIST)
    private TVSeasonInfo seasonInfo;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Review> criticReviews;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Review> audienceReviews;

    //Constructor for JPA
    protected TVSeason(){}

    //Constructor for instantiation
    public TVSeason(Set<TVEpisode> episodes, TVSeasonInfo seasonInfo, Set<Review> criticReviews, Set<Review> audienceReviews)
    {
        this.episodes = episodes;
        this.seasonInfo = seasonInfo;
        this.criticReviews = criticReviews;
        this.audienceReviews = audienceReviews;
    }

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