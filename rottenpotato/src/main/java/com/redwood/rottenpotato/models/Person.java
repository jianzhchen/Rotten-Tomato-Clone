package com.redwood.rottenpotato.models;

import javafx.scene.image.Image;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Person
{
    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //Constructor for JPA
    protected Person(){}

    //Constructor
    public Person(String firstName, String lastName, Set<String> photos
                  ,Date birthday, String birthplace, String biography)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photos = photos;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.biography = biography;
    }

    //Private Fields
    private String firstName;
    private String lastName;
    @ElementCollection
    private Set<String> photos;
    private Date birthday;
    private String birthplace;
    private String biography;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Movie> movies;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TV> tvs;


    //Setters
    public void setFirstName(String s) {this.firstName = s;}
    public void setLastName(String s) {this.lastName = s;}
    public void setPhotos(Set<String> i) {this.photos = i;}
    public void setBirthday(Date d) {this.birthday = d;}
    public void setBirthplace(String s) {this.birthplace = s;}
    public void setBiography(String s) {this.biography = s;}
    public void setMovies(Set<Movie> m) {this.movies = m;}
    public void setTvs(Set<TV> t) {this.tvs = t;}

    //Getters
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName ;}
    public Set<String> getPhotos() {return this.photos;}
    public Date getBirthday() {return this.birthday;}
    public String getBirthplace() {return this.birthplace;}
    public String getBiography() {return this.biography;}
    public Set<Movie> getMovies() {return this.movies;}
    public Set<TV> getTvs() {return this.tvs;}

}