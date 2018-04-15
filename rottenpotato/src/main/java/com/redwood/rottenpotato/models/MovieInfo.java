package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class MovieInfo
{
    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieInfo_ID")
    private long id;
    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> directors;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> writers;
    private Date inTheator;
    private int runTime;
    private String studio;

    //Constructor for JPA
    protected MovieInfo(){}

    //Constructor for instantiation
    public MovieInfo(Set<Person> directors, Set<Person> writers, Date inTheator, int runTime, String studio)
    {
        this.directors = directors;
        this.writers = writers;
        this.inTheator = inTheator;
        this.runTime = runTime;
        this.studio = studio;
    }


    //Setters
    public void setDirectors(Set<Person> s) {this.directors = s;}
    public void setWriters(Set<Person> s) {this.writers = s;}
    public void setInTheator(Date s) {this.inTheator = s;}
    public void setRunTime(int s) {this.runTime = s;}
    public void setStudio(String s) {this.studio = s;}


    //Getters
    public Set<Person> getDirectors() {return this.directors;}
    public Set<Person> getWriters() {return this.writers;}
    public Date getInTheator() {return this.inTheator;}
    public int getRunTime() {return this.runTime;}
    public String getStudio() {return this.studio;}
}