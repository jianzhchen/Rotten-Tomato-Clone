package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Review
{
    //Primary key for the entity Item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private long id;
    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade=CascadeType.PERSIST)
    private Movie movie;

    private Date time;
    private String content;

    //Constructor for JPA
    protected Review(){}

    //Constructor for instantiation
    public Review(Movie movie, Date time, String content)
    {
        this.movie = movie;
        this.time = time;
        this.content = content;
    }


    //Setters
    public void setMovie( Movie  s) {this.movie = s;}
    public void setTime( Date  s) {this.time = s;}
    public void setContent( String  s) {this.content = s;}

    //Getters
    public Movie getMovie() {return this.movie;}
    public Date getTime() {return this.time;}
    public String getContent() {return this.content;}

}