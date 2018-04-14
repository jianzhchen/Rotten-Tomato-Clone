package com.redwood.rottenpotato.models;

import javafx.scene.image.Image;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class Item
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

    //Private Fields
    private String name;
    @ElementCollection
    private Set<String> videos;
    @ElementCollection
    private Set<String> photos;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> cast;

    //Constructor for JPA
    protected Item(){}

    //Constructor for instantiation
    public Item(String name, Set<String> videos, Set<String> photos)
    {
        this.name = name;
        this.videos = videos;
        this.photos = photos;
        this.cast = new HashSet<>();
    }

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