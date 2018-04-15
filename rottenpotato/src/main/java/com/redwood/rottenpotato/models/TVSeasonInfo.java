package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class TVSeasonInfo
{
    //Primary key for the entity Item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TVSeasonInfo_ID")
    private long id;
    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    private String network;
    private Date premiereDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> creater;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Person> execProducer;

    //Constructor for JPA
    protected TVSeasonInfo(){}

    //Constructor for instantiation
    public TVSeasonInfo(String network, Date premiereDate, Set<Person> creater, Set<Person> execProducer)
    {
        this.network = network;
        this.premiereDate = premiereDate;
        this.creater = creater;
        this.execProducer = execProducer;
    }

    //Setters
    public void setNetwork( String  s) {this.network = s;}
    public void setPremiereDate( Date  s) {this.premiereDate = s;}
    public void setCreater( Set<Person>  s) {this.creater = s;}
    public void setExecProducer( Set<Person>  s) {this.execProducer = s;}

    //Getters
    public String getNetwork() {return this.network;}
    public Date getPremiereDate() {return this.premiereDate;}
    public Set<Person> getCreater() {return this.creater;}
    public Set<Person> getExecProducer() {return this.execProducer;}

}