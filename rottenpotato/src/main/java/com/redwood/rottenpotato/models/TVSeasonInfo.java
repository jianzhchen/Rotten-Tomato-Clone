package com.redwood.rottenpotato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class TVSeasonInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String network;
    private Date premiereDate;
    private Set<Person> creater;
    private Set<Person> execProducer;

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
