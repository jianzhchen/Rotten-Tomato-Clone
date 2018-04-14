package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class TVEpisode extends Item
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    private EpisodeInfo episodeInfo;

    //Setters
    public void setEpisodeInfo( EpisodeInfo  s) {this.episodeInfo = s;}

    //Getters
    public EpisodeInfo getEpisodeInfo() {return this.episodeInfo;}
}
