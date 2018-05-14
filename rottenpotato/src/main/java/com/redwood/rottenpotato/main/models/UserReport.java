package com.redwood.rottenpotato.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class UserReport {
    //Primary key for the entity Item
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long reportFromId;
    private long reportToId;
    private String content;

    //Constructor for JPA
    public UserReport() {
    }

    public long getReportFromId() {
        return reportFromId;
    }

    public void setReportFromId(long reportFromId) {
        this.reportFromId = reportFromId;
    }

    public long getReportToId() {
        return reportToId;
    }

    public void setReportToId(long reportToId) {
        this.reportToId = reportToId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}