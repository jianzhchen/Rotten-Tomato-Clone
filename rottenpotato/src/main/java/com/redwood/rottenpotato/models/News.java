package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class News
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String title;
    private String link;

    //Setters
    public void setTitle( String  s) {this.title = s;}
    public void setLink( String  s) {this.link = s;}

    //Getters
    public String getTitle() {return this.title;}
    public String getLink() {return this.link;}

}
