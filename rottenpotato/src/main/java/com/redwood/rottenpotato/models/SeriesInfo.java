package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class SeriesInfo
{
    //Primary key for the entity Item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SeriesInfo_ID")
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> creater;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> starring;

    //Setters
    public void setCreater( Set<Person>  s) {this.creater = s;}
    public void setStarring( Set<Person>  s) {this.starring = s;}

    //Getters
    public Set<Person> getCreater() {return this.creater;}
    public Set<Person> getStarring() {return this.starring;}
}