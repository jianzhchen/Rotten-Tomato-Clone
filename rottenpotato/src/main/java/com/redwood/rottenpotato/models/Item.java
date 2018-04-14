package com.redwood.rottenpotato.models;

import javafx.scene.image.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Item
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private Set<String> videos;
    private Set<String> photos;
    private Set<Person> cast;

    //Setters
    public void setName(String s) {this.name = s;}
    public void setVideos(Set<String> s) {this.videos = s;}
    public void setPhotos(Set<String> s) {this.photos = s;}
    public void setCastPhotos(Set<Person> p) {this.cast = p;}

    //Getters
    public String getName() {return this.name;}
    public Set<String> getVideos() {return this.videos;}
    public Set<String> getPhotos() {return this.photos;}
    public Set<Person> getCastPhotos() {return this.cast;}


}
