package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TVEpisode extends Item
{
    @OneToOne(cascade=CascadeType.PERSIST)
    private EpisodeInfo episodeInfo;

    //Constructor for JPA
    protected TVEpisode(){}

    //Constructor for instantiation
    public TVEpisode(EpisodeInfo episodeInfo)
    {
        this.episodeInfo = episodeInfo;
    }

    //Setters
    public void setEpisodeInfo( EpisodeInfo  s) {this.episodeInfo = s;}

    //Getters
    public EpisodeInfo getEpisodeInfo() {return this.episodeInfo;}
}