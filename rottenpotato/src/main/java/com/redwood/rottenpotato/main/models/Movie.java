package com.redwood.rottenpotato.main.models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Movie {
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
    private long boxOffice;
    private String runTime;
    private String studio;
    private String cast;
    private Date inTheatersTime;
    private Date onDiscTime;
    private boolean isOscarBestPicture;
    private long oscarBestPictureYear;

    //Constructor for JPA
    public Movie()
    {
        this.name = "Default Name";
        this.info = "Default Info";
        this.rating = "Default Rating";
        this.genre = "Default Genre";
        this.director = "Default Director";
        this.writer = "Default Writer";
        this.inTheaters = "Default inTheaters";    //In theater time
        this.onDisc = "Default onDisc";
        this.boxOffice = 0;
        this.runTime ="Default runTime";
        this.studio="Default studio";
        this.cast="Default studio";
        this.inTheatersTime= null;
        this.onDiscTime=null;
        this.isOscarBestPicture = false;
        this.oscarBestPictureYear = 0;
    }

    public String getMovieKey() {
        return movieKey;
    }

    public void setMovieKey(String movieKey) {
        this.movieKey = movieKey;
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

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public Date getInTheatersTime() {
        return inTheatersTime;
    }

    public void setInTheatersTime(Date inTheatersTime) {
        this.inTheatersTime = inTheatersTime;
    }

    public Date getOnDiscTime() {
        return onDiscTime;
    }

    public void setOnDiscTime(Date onDiscTime) {
        this.onDiscTime = onDiscTime;
    }

    public boolean getIsOscarBestPicture() {
        return this.isOscarBestPicture;
    }

    public void setIsOscarBestPicture(boolean isOscarBestPicture) {
        this.isOscarBestPicture = isOscarBestPicture;
    }

    public long getOscarBestPictureYear() {
        return this.oscarBestPictureYear;
    }

    public void setOscarBestPictureYear(long oscarBestPictureYear) {
        this.oscarBestPictureYear = oscarBestPictureYear;
    }
}