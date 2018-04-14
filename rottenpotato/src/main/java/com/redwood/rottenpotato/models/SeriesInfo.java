package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class SeriesInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Set<Person> creater;
    private Set<Person> starring;

    //Setters
    public void setCreater( Set<Person>  s) {this.creater = s;}
    public void setStarring( Set<Person>  s) {this.starring = s;}

    //Getters
    public Set<Person> getCreater() {return this.creater;}
    public Set<Person> getStarring() {return this.starring;}
}
