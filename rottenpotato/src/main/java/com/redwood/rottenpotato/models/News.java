package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class News
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

    private String title;
    private String link;

    //Constructor for JPA
    protected News(){}

    //Constructor for instantiation
    public News(String title, String link)
    {
        this.title = title;
        this.link = link;
    }

    //Setters
    public void setTitle( String  s) {this.title = s;}
    public void setLink( String  s) {this.link = s;}

    //Getters
    public String getTitle() {return this.title;}
    public String getLink() {return this.link;}

}