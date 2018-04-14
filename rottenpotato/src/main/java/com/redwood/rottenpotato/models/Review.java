package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Movie movie;
    private Date time;
    private String content;

    //Setters
    public void setMovie( Movie  s) {this.movie = s;}
    public void setTime( Date  s) {this.time = s;}
    public void setContent( String  s) {this.content = s;}

    //Getters
    public Movie getMovie() {return this.movie;}
    public Date getTime() {return this.time;}
    public String getContent() {return this.content;}

}
