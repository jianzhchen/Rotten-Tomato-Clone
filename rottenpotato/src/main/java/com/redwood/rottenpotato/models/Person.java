package com.redwood.rottenpotato.models;

import javafx.scene.image.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Person
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String firstName;
    private String lastName;
    private Set<Image> photos;
    private Date birthday;
    private String birthplace;
    private String biography;
    private Set<Movie> movies;
    private Set<TV> tvs;


    //Setters
    public void setFirstName(String s) {this.firstName = s;}
    public void setLastName(String s) {this.lastName = s;}
    public void setPhotos(Set<Image> i) {this.photos = i;}
    public void setBirthday(Date d) {this.birthday = d;}
    public void setBirthplace(String s) {this.birthplace = s;}
    public void setBiography(String s) {this.biography = s;}
    public void setMovies(Set<Movie> m) {this.movies = m;}
    public void setTvs(Set<TV> t) {this.tvs = t;}

    //Getters
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName ;}
    public Set<Image> getPhotos() {return this.photos;}
    public Date getBirthday() {return this.birthday;}
    public String getBirthplace() {return this.birthplace;}
    public String getBiography() {return this.biography;}
    public Set<Movie> getMovies() {return this.movies;}
    public Set<TV> getTvs() {return this.tvs;}

}
