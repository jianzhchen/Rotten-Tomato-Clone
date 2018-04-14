package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class MovieInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Set<Person> directors;
    private Set<Person> writers;
    private Date inTheator;
    private int runTime;
    private String studio;

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
