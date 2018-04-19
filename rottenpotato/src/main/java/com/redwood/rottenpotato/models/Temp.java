package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Temp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long movieID;
    private String name;
    private String info;
    private String rating;
    private String genre;
    private String director;
    private String writer;
    private String inTheaters;
    private String onDisc;
    private Long boxOffice;
    private String runTime;
    private String studio;

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getInTheaters() {
        return inTheaters;
    }

    public void setInTheaters(String inTheaters) {
        this.inTheaters = inTheaters;
    }

    public String getOnDisc() {
        return onDisc;
    }

    public void setOnDisc(String onDisc) {
        this.onDisc = onDisc;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
}
