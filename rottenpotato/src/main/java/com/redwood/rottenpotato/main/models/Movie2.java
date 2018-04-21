package com.redwood.rottenpotato.main.models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Movie2
{
    //Primary key for the entity Item
    @Id
    private String movieKey;
    private String name;
    private String info;
    private String rating;
    private String genre;
    private String director;
    private String writer;
    private String inTheaters;    //In theater time
    private String onDisc;
    private String boxOffice;
    private String runTime;
    private String studio;
    private String cast;

    //Constructor for JPA
    protected Movie2() {
    }

    //Constructor for instantiation
    public Movie2(String name) {
        this.name = name;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setInTheaters(String inTheaters) {
        this.inTheaters = inTheaters;
    }

    public void setOnDisc(String onDisc) {
        this.onDisc = onDisc;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }


    //Getters
    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    public String getRating() {
        return this.rating;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getDirector() {
        return this.director;
    }

    public String getWriter() {
        return this.writer;
    }

    public String getInTheaters() {
        return this.inTheaters;
    }

    public String getOnDisc() {
        return this.onDisc;
    }

    public String getBoxOffice() {
        return this.boxOffice;
    }

    public String getRunTime() {
        return this.runTime;
    }

    public String getStudio() {
        return this.studio;
    }


}