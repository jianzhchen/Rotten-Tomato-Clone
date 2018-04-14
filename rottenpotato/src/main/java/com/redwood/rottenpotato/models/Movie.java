package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Movie extends Item
{

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Set<String> trailer;
    private MovieInfo movieInfo;

    //Setters
    public void setTrailer(Set<String> s) {this.trailer = s;}
    public void setMovieInfo(MovieInfo s) {this.movieInfo = s;}


    //Getters
    public Set<String> getTrailer() {return this.trailer;}
    public MovieInfo getMovieInfo() {return this.movieInfo;}

}
