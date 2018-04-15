package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Movie
{
    //Primary key for the entity Item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Movie_ID")
    private long id;
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}


    private String movieName;
    private Date movieDate;
    private double rate;
    private double boxOffice;

    //Constructor for JPA
    protected Movie(){}

    //Constructor for instantiation
    public Movie(String movieName, Date movieDate, double rate, double boxOffice)
    {
        this.movieName = movieName;
        this.movieDate = movieDate;
        this.rate = rate;
        this.boxOffice = boxOffice;
    }

    //Setters
    /*public void setTrailer(Set<String> s) {this.trailer = s;}
    public void setMovieInfo(MovieInfo s) {this.movieInfo = s;}*/
    public void setMovieName(String movieName) {this.movieName = movieName;}
    public void setMovieDate(Date movieDate) {this.movieDate = movieDate;}
    public void setRate(double rate) {this.rate = rate;}
    public void setBoxOffice(double boxOffice) {this.boxOffice = boxOffice;}


    //Getters
    /*public Set<String> getTrailer() {return this.trailer;}
    public MovieInfo getMovieInfo() {return this.movieInfo;}*/
    public String getMovieName() {return this.movieName;}
    public Date getMovieDate() {return this.movieDate;}
    public double getRate() {return this.rate;}
    public double getBoxOffice() {return this.boxOffice;}
}