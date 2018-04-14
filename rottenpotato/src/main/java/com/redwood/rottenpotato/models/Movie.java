package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Movie extends Item
{
    @ElementCollection
    private Set<String> trailer;

    @OneToOne(cascade=CascadeType.PERSIST)
    private MovieInfo movieInfo;

    //Constructor for JPA
    protected Movie(){}

    //Constructor for instantiation
    public Movie(Set<String> trailer, MovieInfo movieInfo)
    {
        this.trailer = trailer;
        this.movieInfo = movieInfo;
    }

    //Setters
    public void setTrailer(Set<String> s) {this.trailer = s;}
    public void setMovieInfo(MovieInfo s) {this.movieInfo = s;}


    //Getters
    public Set<String> getTrailer() {return this.trailer;}
    public MovieInfo getMovieInfo() {return this.movieInfo;}
}