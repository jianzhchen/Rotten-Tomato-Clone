package com.redwood.rottenpotato.main.models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class TV {
    //Primary key for the entity Item
    @Id
    private String TVKey;
    private String TVName;
    private String TVInfo;
    private String TVGenre;
    private String TVNetwork;
    private String TVDate;
    private String TVCast;

    //Constructor for JPA
    protected TV() {
    }

    public String getTVKey() {
        return TVKey;
    }

    public void setTVKey(String TVKey) {
        this.TVKey = TVKey;
    }

    public String getTVName() {
        return TVName;
    }

    public void setTVName(String TVName) {
        this.TVName = TVName;
    }

    public String getTVInfo() {
        return TVInfo;
    }

    public void setTVInfo(String TVInfo) {
        this.TVInfo = TVInfo;
    }

    public String getTVGenre() {
        return TVGenre;
    }

    public void setTVGenre(String TVGenre) {
        this.TVGenre = TVGenre;
    }

    public String getTVNetwork() {
        return TVNetwork;
    }

    public void setTVNetwork(String TVNetwork) {
        this.TVNetwork = TVNetwork;
    }

    public String getTVDate() {
        return TVDate;
    }

    public void setTVDate(String TVDate) {
        this.TVDate = TVDate;
    }

    public String getTVCast() {
        return TVCast;
    }

    public void setTVCast(String TVCast) {
        this.TVCast = TVCast;
    }
}